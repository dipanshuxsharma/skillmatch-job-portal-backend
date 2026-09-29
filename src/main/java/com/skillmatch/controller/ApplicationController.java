package com.skillmatch.controller;

import com.skillmatch.dto.ApplicationResponse;
import com.skillmatch.service.ApplicationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
@SecurityRequirement(name = "bearerAuth")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    // Apply for a job
    @PostMapping("/apply/{jobId}")
    public ApplicationResponse applyForJob(
            @PathVariable Long jobId,
            Authentication authentication) {

        String email = authentication.getName();

        return applicationService.applyForJob(jobId, email);
    }

    // Get logged-in user's applications
    @GetMapping("/my")
    public List<ApplicationResponse> getMyApplications(
            Authentication authentication) {

        String email = authentication.getName();

        return applicationService.getMyApplications(email);
    }
}