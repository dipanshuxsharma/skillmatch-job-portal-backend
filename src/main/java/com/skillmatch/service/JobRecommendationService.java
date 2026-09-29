package com.skillmatch.service;

import com.skillmatch.dto.RecommendedJobResponse;
import com.skillmatch.entity.Job;
import com.skillmatch.entity.Resume;
import com.skillmatch.entity.User;
import com.skillmatch.repository.JobRepository;
import com.skillmatch.repository.ResumeRepository;
import com.skillmatch.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class JobRecommendationService {

    private final JobRepository jobRepository;
    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;
    private final SkillMatchingService skillMatchingService;

    public JobRecommendationService(
            JobRepository jobRepository,
            ResumeRepository resumeRepository,
            UserRepository userRepository,
            SkillMatchingService skillMatchingService) {

        this.jobRepository = jobRepository;
        this.resumeRepository = resumeRepository;
        this.userRepository = userRepository;
        this.skillMatchingService = skillMatchingService;
    }

    public List<RecommendedJobResponse> getRecommendedJobs(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Resume resume = resumeRepository.findByUserId(user.getId())
                .orElseThrow(() ->
                        new RuntimeException("Resume not found"));

        List<Job> jobs = jobRepository.findAll();

        return jobs.stream()
                .map(job -> {

                    double matchPercentage =
                            skillMatchingService.calculateMatchPercentage(
                                    resume, job);

                    return new RecommendedJobResponse(
                            job.getId(),
                            job.getTitle(),
                            job.getCompany(),
                            job.getLocation(),
                            job.getDescription(),
                            job.getRequiredSkills(),
                            job.getSalary(),
                            matchPercentage
                    );
                })
                .sorted(Comparator.comparing(
                        RecommendedJobResponse::getMatchPercentage
                ).reversed())
                .toList();
    }
}
