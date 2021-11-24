package com.odds_and_ends.backendv1.service;

import com.odds_and_ends.backendv1.payload.response.CommonResponse;
import com.odds_and_ends.backendv1.payload.response.FileResponse;
import com.odds_and_ends.backendv1.util.S3Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class FileService {

    private final S3Util s3Util;

    public CommonResponse<FileResponse> saveFile(MultipartFile file) throws IOException {
        return new CommonResponse<>(200, "파일 업로드 성공", new FileResponse(s3Util.upload(file, "images/")));
    }

}
