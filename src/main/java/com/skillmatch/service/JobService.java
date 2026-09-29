package com.skillmatch.service;

import com.skillmatch.entity.Job;
import com.skillmatch.repository.ApplicationRepository;
import com.skillmatch.repository.JobRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final ApplicationRepository applicationRepository;

    public JobService(
            JobRepository jobRepository,
            ApplicationRepository applicationRepository) {

        this.jobRepository = jobRepository;
        this.applicationRepository = applicationRepository;
    }

    // =========================================
    // CREATE SINGLE JOB
    // =========================================

    public Job createJob(Job job) {
        return jobRepository.save(job);
    }


    // =========================================
    // CREATE MULTIPLE JOBS
    // =========================================

    public List<Job> createJobs(List<Job> jobs) {
        return jobRepository.saveAll(jobs);
    }


    // =========================================
    // GET ALL JOBS
    // =========================================

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }


    // =========================================
    // GET JOB BY ID
    // =========================================

    public Job getJobById(Long id) {

        return jobRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Job not found"));
    }


    // =========================================
    // UPDATE JOB
    // =========================================

    public Job updateJob(
            Long id,
            Job updatedJob) {

        Job existingJob = getJobById(id);

        existingJob.setTitle(
                updatedJob.getTitle()
        );

        existingJob.setCompany(
                updatedJob.getCompany()
        );

        existingJob.setLocation(
                updatedJob.getLocation()
        );

        existingJob.setDescription(
                updatedJob.getDescription()
        );

        existingJob.setRequiredSkills(
                updatedJob.getRequiredSkills()
        );

        existingJob.setSalary(
                updatedJob.getSalary()
        );

        return jobRepository.save(existingJob);
    }


    // =========================================
    // DELETE JOB
    // =========================================

    @Transactional
    public void deleteJob(Long id) {

        Job job = getJobById(id);

        // Delete applications linked to this job first
        applicationRepository.deleteByJobId(id);

        // Delete job after applications are removed
        jobRepository.delete(job);
    }


    // =========================================
    // SEARCH + PAGINATION + SORTING
    // =========================================

    public Page<Job> searchJobs(
            String keyword,
            int page,
            int size,
            String sortBy,
            String direction) {

        Sort sort =
                direction.equalsIgnoreCase("desc")
                        ? Sort.by(sortBy).descending()
                        : Sort.by(sortBy).ascending();

        Pageable pageable =
                PageRequest.of(
                        page,
                        size,
                        sort
                );

        if (keyword == null ||
                keyword.isBlank()) {

            return jobRepository.findAll(pageable);
        }

        return jobRepository.findByTitleContainingIgnoreCase(
                keyword,
                pageable
        );
    }
}