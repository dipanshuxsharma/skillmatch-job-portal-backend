package com.skillmatch.dto;

import java.time.LocalDateTime;

public class ApplicationResponse {

    private Long applicationId;
    private Long jobId;
    private String jobTitle;
    private String company;
    private String status;
    private LocalDateTime appliedAt;

    public ApplicationResponse() {
    }

    public ApplicationResponse(
            Long applicationId,
            Long jobId,
            String jobTitle,
            String company,
            String status,
            LocalDateTime appliedAt) {

        this.applicationId = applicationId;
        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.company = company;
        this.status = status;
        this.appliedAt = appliedAt;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getAppliedAt() {
        return appliedAt;
    }

    public void setAppliedAt(LocalDateTime appliedAt) {
        this.appliedAt = appliedAt;
    }
}
