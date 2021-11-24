package com.odds_and_ends.backendv1.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.odds_and_ends.backendv1.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor @AllArgsConstructor
@Getter
public class SignupVo {

    private MultipartFile userImage;
    private UserInfo userInfo;

    @JsonIgnore
    @Setter
    private String userImageFilePath;

    public User toEntity(){
        return User.builder()
                .username(userInfo.username)
                .password(userInfo.password)
                .name(userInfo.name)
                .phoneNumber(userInfo.phoneNumber)
                .profileImage(userImageFilePath)
                .build();
    }

    @NoArgsConstructor @AllArgsConstructor
    @Getter
    static class UserInfo {
        @JsonProperty(namespace = "username")
        private String username;
        @JsonProperty(namespace = "password")
        private String password;
        @JsonProperty(namespace = "name")
        private String name;
        @JsonProperty(namespace = "phoneNumber")
        private String phoneNumber;
    }
}
