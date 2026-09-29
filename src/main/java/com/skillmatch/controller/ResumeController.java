package com.skillmatch.controller;

import com.skillmatch.entity.Resume;
import com.skillmatch.service.ResumeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/resumes")
@SecurityRequirement(name = "bearerAuth")
public class ResumeController {

    private final ResumeService resumeService;

    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @PostMapping(value = "/process",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Resume processResume(
            @RequestParam("file") MultipartFile file,
            Authentication authentication) throws Exception {

        if (file.isEmpty()) {
            throw new RuntimeException("Please select a resume file");
        }

        String email = authentication.getName();

        return resumeService.processResume(file, email);
    }
}