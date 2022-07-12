package com.jaemin.triple.homework.User.controller;

import com.jaemin.triple.homework.User.dto.request.ReqUserDto;
import com.jaemin.triple.homework.User.dto.response.ResUserDto;
import com.jaemin.triple.homework.global.dto.Result;
import com.jaemin.triple.homework.User.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    /**
     * 유저 회원가입
     * @author jaemin
     * @version 1.0.0
     * 작성일 2022/07/03
     **/
    @ApiOperation(value = "유저 회원 가입")
    @PostMapping("/v1/join")
    public ResponseEntity createUser(@RequestBody ReqUserDto.RequestJoin requestJoin) {

        String userId = userService.createUser(requestJoin);

        ResUserDto.ResponseJoin responseJoin = ResUserDto.ResponseJoin.builder().userId(userId).build();

        return ResponseEntity.ok(Result.createSuccessResult(responseJoin));
    }
}