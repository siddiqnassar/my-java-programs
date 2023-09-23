package com.filesaver.pdfService;

import com.filesaver.entity.PdfDocument;
import com.filesaver.repository.PdfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class PdfService {

    @Autowired
    PdfRepository pdfRepo;
    public String savePdf(MultipartFile file) {

        try {
            PdfDocument pdfDoc = new PdfDocument();
            pdfDoc.setName(file.getOriginalFilename());
            pdfDoc.setContentType(file.getContentType());
            pdfDoc.setData(file.getBytes());

            pdfRepo.save(pdfDoc);
        } catch (Exception ex) {
            return "some exception while saving the pdf";
        }
        return "saved";
    }

    public byte[] getFile(Long id) {
        Optional<PdfDocument> pdfDoc = pdfRepo.findById(id);

        if (pdfDoc.isPresent()) {
            PdfDocument pdfDocument = pdfDoc.get();
            return pdfDocument.getData();
        } else {
            return null;
        }
    }
}
