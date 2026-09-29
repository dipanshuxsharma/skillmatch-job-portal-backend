package com.skillmatch.controller;

import com.skillmatch.dto.RecommendedJobResponse;
import com.skillmatch.service.JobRecommendationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
@SecurityRequirement(name = "bearerAuth")
public class JobRecommendationController {

    private final JobRecommendationService jobRecommendationService;

    public JobRecommendationController(
            JobRecommendationService jobRecommendationService) {
        this.jobRecommendationService = jobRecommendationService;
    }

    @GetMapping
    public List<RecommendedJobResponse> getRecommendedJobs(
            Authentication authentication) {

        String email = authentication.getName();

        return jobRecommendationService.getRecommendedJobs(email);
    }
}