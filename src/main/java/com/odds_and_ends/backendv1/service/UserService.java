package com.odds_and_ends.backendv1.service;

import com.odds_and_ends.backendv1.dto.user.SignupVo;
import com.odds_and_ends.backendv1.entity.user.User;
import com.odds_and_ends.backendv1.entity.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;

    public User signup(SignupVo signupVo) throws IOException {
//        String savedFilePath = saveFile(signupVo.getUserImage());
        if(userRepository.existsByUsername(signupVo.getUsername()))
            throw new RuntimeException(); //TODO username중복 Exception

        return userRepository.save(signupVo.toEntity());
    }

    /**
     * 파일 저장된 위치 반환 (현제 버그 발생)
     * @return file이 저장된 위치
     */
//    private String saveFile(MultipartFile multipartFile) throws IOException {
//        String rootPath = FileSystemView.getFileSystemView().getHomeDirectory().toString();
//        String basePath = rootPath + "/profileImage";
//
//        String filePath = basePath + "/" + UUID.randomUUID();
//        File dest = new File(filePath);
//        multipartFile.transferTo(dest);
//
//        return filePath;
//    }
}
