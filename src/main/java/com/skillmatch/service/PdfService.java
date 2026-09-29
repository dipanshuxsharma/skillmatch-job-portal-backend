package com.skillmatch.service;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PdfService {

    public String extractText(MultipartFile file) throws Exception {

        byte[] pdfBytes = file.getBytes();

        try (var document = Loader.loadPDF(pdfBytes)) {

            PDFTextStripper pdfTextStripper = new PDFTextStripper();

            return pdfTextStripper.getText(document);
        }
    }
}