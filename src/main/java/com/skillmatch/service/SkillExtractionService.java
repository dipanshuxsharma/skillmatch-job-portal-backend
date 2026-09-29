package com.skillmatch.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkillExtractionService {

    private final List<String> skills = List.of(
            "Java",
            "Spring",
            "Spring Boot",
            "Spring MVC",
            "Spring Security",
            "Hibernate",
            "JPA",
            "MySQL",
            "SQL",
            "REST API",
            "JWT",
            "Git",
            "Docker",
            "HTML",
            "CSS",
            "JavaScript"
    );

    public List<String> extractSkills(String resumeText) {

        List<String> extractedSkills = new ArrayList<>();

        String text = resumeText.toLowerCase();

        for (String skill : skills) {

            if (text.contains(skill.toLowerCase())) {
                extractedSkills.add(skill);
            }
        }

        return extractedSkills;
    }
}
