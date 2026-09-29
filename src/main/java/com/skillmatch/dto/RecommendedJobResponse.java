package com.skillmatch.dto;

public class RecommendedJobResponse {

    private Long jobId;
    private String title;
    private String company;
    private String location;
    private String description;
    private String requiredSkills;
    private Double salary;
    private double matchPercentage;

    public RecommendedJobResponse() {
    }

    public RecommendedJobResponse(Long jobId,
                                  String title,
                                  String company,
                                  String location,
                                  String description,
                                  String requiredSkills,
                                  Double salary,
                                  double matchPercentage) {
        this.jobId = jobId;
        this.title = title;
        this.company = company;
        this.location = location;
        this.description = description;
        this.requiredSkills = requiredSkills;
        this.salary = salary;
        this.matchPercentage = matchPercentage;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(String requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public double getMatchPercentage() {
        return matchPercentage;
    }

    public void setMatchPercentage(double matchPercentage) {
        this.matchPercentage = matchPercentage;
    }
}
