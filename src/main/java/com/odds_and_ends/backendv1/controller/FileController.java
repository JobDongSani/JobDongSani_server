package com.odds_and_ends.backendv1.controller;

import com.odds_and_ends.backendv1.payload.response.FileResponse;
import com.odds_and_ends.backendv1.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class FileController {

    private final FileService fileService;

    @PostMapping("/file")
    public FileResponse uploadFile(@RequestPart MultipartFile file) throws IOException {
        return fileService.saveFile(file);
    }
}
