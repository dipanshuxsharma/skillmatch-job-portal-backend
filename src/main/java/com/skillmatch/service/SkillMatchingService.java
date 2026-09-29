package com.skillmatch.service;

import com.skillmatch.entity.Job;
import com.skillmatch.entity.Resume;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class SkillMatchingService {

    public List<String> getMatchedSkills(Resume resume, Job job) {

        List<String> resumeSkills = Arrays.stream(resume.getSkills().split(","))
                .map(String::trim)
                .map(String::toLowerCase)
                .toList();

        List<String> requiredSkills = Arrays.stream(job.getRequiredSkills().split(","))
                .map(String::trim)
                .map(String::toLowerCase)
                .toList();

        return requiredSkills.stream()
                .filter(resumeSkills::contains)
                .toList();
    }

    public List<String> getMissingSkills(Resume resume, Job job) {

        List<String> resumeSkills = Arrays.stream(resume.getSkills().split(","))
                .map(String::trim)
                .map(String::toLowerCase)
                .toList();

        List<String> requiredSkills = Arrays.stream(job.getRequiredSkills().split(","))
                .map(String::trim)
                .map(String::toLowerCase)
                .toList();

        return requiredSkills.stream()
                .filter(skill -> !resumeSkills.contains(skill))
                .toList();
    }

    public double calculateMatchPercentage(Resume resume, Job job) {

        List<String> requiredSkills = Arrays.stream(job.getRequiredSkills().split(","))
                .map(String::trim)
                .map(String::toLowerCase)
                .toList();

        if (requiredSkills.isEmpty()) {
            return 0;
        }

        long matchedSkills = getMatchedSkills(resume, job).size();

        return (matchedSkills * 100.0) / requiredSkills.size();
    }
}