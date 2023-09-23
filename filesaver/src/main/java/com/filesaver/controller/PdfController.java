package com.filesaver.controller;

import com.filesaver.pdfService.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
public class PdfController {

    @Autowired
    PdfService pdfService;
    @PostMapping("/pdf/save")
    public String savePDF(@RequestParam("file") MultipartFile file) {
        return pdfService.savePdf(file);
    }

    @GetMapping("/pdf/download/{id}")
    public ResponseEntity<byte[]> downloadPdf(@PathVariable Long id) throws IOException {

        byte[] res = pdfService.getFile(id);
        if(res != null) {
            OutputStream out = Files.newOutputStream(Paths.get("save.pdf"));
            out.write(res);
            out.close();
            HttpHeaders headers = new HttpHeaders();
            //headers.setContentType(MediaType.parseMediaType(pdfDocum.getContentType()));
            //headers.setContentDispositionFormData(pdfDocument.getName(), pdfDocument.getName());
            //ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(pdfDocument.getData(), headers, HttpStatus.OK);
            //return responseEntity;
            return new ResponseEntity<>(res, headers, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
