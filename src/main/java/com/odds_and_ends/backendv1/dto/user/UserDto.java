package com.odds_and_ends.backendv1.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.odds_and_ends.backendv1.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserDto {

    @JsonProperty(namespace = "username")
    private String username;

    @JsonProperty(namespace = "password")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Setter
    private String password;
    @JsonProperty(namespace = "name")
    private String name;
    @JsonProperty(namespace = "phoneNumber")
    private String phoneNumber;

    private String userImage;

    public User toEntity() {
        return User.builder()
                .username(username)
                .password(password)
                .name(name)
                .phoneNumber(phoneNumber)
                .profileImage(userImage)
                .build();
    }

}
