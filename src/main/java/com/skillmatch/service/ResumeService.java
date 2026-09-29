package com.skillmatch.service;

import com.skillmatch.entity.Resume;
import com.skillmatch.entity.User;
import com.skillmatch.repository.ResumeRepository;
import com.skillmatch.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ResumeService {

    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;
    private final PdfService pdfService;
    private final SkillExtractionService skillExtractionService;

    public ResumeService(ResumeRepository resumeRepository,
                         UserRepository userRepository,
                         PdfService pdfService,
                         SkillExtractionService skillExtractionService) {
        this.resumeRepository = resumeRepository;
        this.userRepository = userRepository;
        this.pdfService = pdfService;
        this.skillExtractionService = skillExtractionService;
    }

    public Resume processResume(MultipartFile file, String email) throws Exception {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String extractedText = pdfService.extractText(file);

        List<String> extractedSkills =
                skillExtractionService.extractSkills(extractedText);

        Resume resume = resumeRepository.findByUserId(user.getId())
                .orElse(new Resume());

        resume.setFileName(file.getOriginalFilename());
        resume.setExtractedText(extractedText);
        resume.setSkills(String.join(", ", extractedSkills));
        resume.setUser(user);

        return resumeRepository.save(resume);
    }
}
