package com.skillmatch.dto;

import java.util.List;

public class MatchResponse {

    private Long jobId;
    private String jobTitle;
    private String company;
    private double matchPercentage;

    private List<String> matchedSkills;
    private List<String> missingSkills;

    public MatchResponse() {
    }

    public MatchResponse(Long jobId,
                         String jobTitle,
                         String company,
                         double matchPercentage,
                         List<String> matchedSkills,
                         List<String> missingSkills) {

        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.company = company;
        this.matchPercentage = matchPercentage;
        this.matchedSkills = matchedSkills;
        this.missingSkills = missingSkills;
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

    public double getMatchPercentage() {
        return matchPercentage;
    }

    public void setMatchPercentage(double matchPercentage) {
        this.matchPercentage = matchPercentage;
    }

    public List<String> getMatchedSkills() {
        return matchedSkills;
    }

    public void setMatchedSkills(List<String> matchedSkills) {
        this.matchedSkills = matchedSkills;
    }

    public List<String> getMissingSkills() {
        return missingSkills;
    }

    public void setMissingSkills(List<String> missingSkills) {
        this.missingSkills = missingSkills;
    }
}
