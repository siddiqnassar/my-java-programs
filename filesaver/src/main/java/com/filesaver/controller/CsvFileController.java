package com.filesaver.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CsvFileController {

    @PostMapping("/csv/read")
    public List<String> readCSV(@RequestParam("file") MultipartFile file) throws IOException {

        BufferedReader br;
        List<String> result = new ArrayList<>();
        try(InputStream is = file.getInputStream()) {

            String line;

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                result.add(line);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

}
