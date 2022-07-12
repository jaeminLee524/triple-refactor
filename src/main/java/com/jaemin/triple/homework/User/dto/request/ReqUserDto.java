package com.jaemin.triple.homework.User.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class ReqUserDto {

    /**
     * 회원가입 DTO
     * @author jaemin
     * @version 1.0.0
     * 작성일 2022/07/04
    **/
    @Data @AllArgsConstructor @NoArgsConstructor
    public static class RequestJoin {
        @NotEmpty(message = "name cannot be null")
        @Size(min = 2, message = "Name not be less than two characters")
        private String name;

        @Email(message = "email 형식이 아닙니다.")
        private String email;

        @NotEmpty(message = "pwd cannot be null")
        @Size(min = 8, message = "Password must be equal or grater than 8 characters")
        private String password;
    }

    public static class RequestLogin {
    }

}
