package com.skillmatch.controller;

import com.skillmatch.dto.MatchResponse;
import com.skillmatch.entity.Job;
import com.skillmatch.entity.Resume;
import com.skillmatch.entity.User;
import com.skillmatch.repository.JobRepository;
import com.skillmatch.repository.ResumeRepository;
import com.skillmatch.repository.UserRepository;
import com.skillmatch.service.SkillMatchingService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/match")
@SecurityRequirement(name = "bearerAuth")
public class MatchController {

    private final JobRepository jobRepository;
    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;
    private final SkillMatchingService skillMatchingService;

    public MatchController(
            JobRepository jobRepository,
            ResumeRepository resumeRepository,
            UserRepository userRepository,
            SkillMatchingService skillMatchingService) {

        this.jobRepository = jobRepository;
        this.resumeRepository = resumeRepository;
        this.userRepository = userRepository;
        this.skillMatchingService = skillMatchingService;
    }

    @GetMapping("/{jobId}")
    public MatchResponse matchJob(
            @PathVariable Long jobId,
            Authentication authentication) {

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Resume resume = resumeRepository.findByUserId(user.getId())
                .orElseThrow(() ->
                        new RuntimeException("Resume not found"));

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new RuntimeException("Job not found"));

        double percentage =
                skillMatchingService.calculateMatchPercentage(
                        resume, job);

        var matchedSkills =
                skillMatchingService.getMatchedSkills(
                        resume, job);

        var missingSkills =
                skillMatchingService.getMissingSkills(
                        resume, job);

        return new MatchResponse(
                job.getId(),
                job.getTitle(),
                job.getCompany(),
                percentage,
                matchedSkills,
                missingSkills
        );
    }
}

