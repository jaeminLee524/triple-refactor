package com.jaemin.triple.homework.User.service;

import com.jaemin.triple.homework.User.dto.request.ReqUserDto;

public interface UserService {
    String createUser(ReqUserDto.RequestJoin requestJoin);
}
