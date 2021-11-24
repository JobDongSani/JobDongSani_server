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

    @JsonProperty(namespace = "username")
    private String username;
    @JsonProperty(namespace = "password")
    private String password;
    @JsonProperty(namespace = "name")
    private String name;
    @JsonProperty(namespace = "phoneNumber")
    private String phoneNumber;

//    private MultipartFile userImage;
//    private UserInfo userInfo;

    public User toEntity(){
        return User.builder()
                .username(username)
                .password(password)
                .name(name)
                .phoneNumber(phoneNumber)
                .build();
    }

}
