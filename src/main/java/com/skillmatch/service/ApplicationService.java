package com.skillmatch.service;

import com.skillmatch.dto.ApplicationResponse;
import com.skillmatch.entity.Application;
import com.skillmatch.entity.Job;
import com.skillmatch.entity.User;
import com.skillmatch.exception.DuplicateApplicationException;
import com.skillmatch.exception.ResourceNotFoundException;
import com.skillmatch.repository.ApplicationRepository;
import com.skillmatch.repository.JobRepository;
import com.skillmatch.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final JobRepository jobRepository;

    public ApplicationService(
            ApplicationRepository applicationRepository,
            UserRepository userRepository,
            JobRepository jobRepository) {

        this.applicationRepository = applicationRepository;
        this.userRepository = userRepository;
        this.jobRepository = jobRepository;
    }

    // Apply for a job
    public ApplicationResponse applyForJob(Long jobId, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Job not found"));

        // Check duplicate application
        if (applicationRepository.existsByUserIdAndJobId(
                user.getId(), job.getId())) {

            throw new DuplicateApplicationException(
                    "You have already applied for this job");
        }

        Application application = new Application();

        application.setUser(user);
        application.setJob(job);
        application.setStatus("APPLIED");
        application.setAppliedAt(LocalDateTime.now());

        Application savedApplication =
                applicationRepository.save(application);

        return new ApplicationResponse(
                savedApplication.getId(),
                job.getId(),
                job.getTitle(),
                job.getCompany(),
                savedApplication.getStatus(),
                savedApplication.getAppliedAt()
        );
    }

    // Get logged-in user's applications
    public List<ApplicationResponse> getMyApplications(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        List<Application> applications =
                applicationRepository.findByUserId(user.getId());

        return applications.stream()
                .map(application ->
                        new ApplicationResponse(
                                application.getId(),
                                application.getJob().getId(),
                                application.getJob().getTitle(),
                                application.getJob().getCompany(),
                                application.getStatus(),
                                application.getAppliedAt()
                        )
                )
                .toList();
    }
}

