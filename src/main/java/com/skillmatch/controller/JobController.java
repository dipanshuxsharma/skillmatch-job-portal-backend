package com.skillmatch.controller;

import com.skillmatch.entity.Job;
import com.skillmatch.service.JobService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@SecurityRequirement(name = "bearerAuth")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }


    // =========================================
    // CREATE SINGLE JOB
    // =========================================

    @PostMapping
    public Job createJob(
            @RequestBody Job job) {

        return jobService.createJob(job);
    }


    // =========================================
    // CREATE MULTIPLE JOBS
    // =========================================

    @PostMapping("/bulk")
    public List<Job> createJobs(
            @RequestBody List<Job> jobs) {

        return jobService.createJobs(jobs);
    }


    // =========================================
    // GET ALL JOBS
    // =========================================

    @GetMapping
    public List<Job> getAllJobs() {

        return jobService.getAllJobs();
    }


    // =========================================
    // GET JOB BY ID
    // =========================================

    @GetMapping("/{id}")
    public Job getJobById(
            @PathVariable Long id) {

        return jobService.getJobById(id);
    }


    // =========================================
    // UPDATE JOB
    // =========================================

    @PutMapping("/{id}")
    public Job updateJob(
            @PathVariable Long id,
            @RequestBody Job job) {

        return jobService.updateJob(
                id,
                job
        );
    }


    // =========================================
    // DELETE JOB
    // =========================================

    @DeleteMapping("/{id}")
    public String deleteJob(
            @PathVariable Long id) {

        jobService.deleteJob(id);

        return "Job deleted successfully";
    }


    // =========================================
    // SEARCH + PAGINATION + SORTING
    // =========================================

    @GetMapping("/search")
    public Page<Job> searchJobs(
            @RequestParam(required = false)
            String keyword,

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "5")
            int size,

            @RequestParam(defaultValue = "id")
            String sortBy,

            @RequestParam(defaultValue = "asc")
            String direction) {

        return jobService.searchJobs(
                keyword,
                page,
                size,
                sortBy,
                direction
        );
    }
}