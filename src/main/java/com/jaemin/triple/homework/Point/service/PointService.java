package com.jaemin.triple.homework.Point.service;

import com.jaemin.triple.homework.Point.dto.response.ResPointDto;
import org.springframework.data.domain.Pageable;

public interface PointService {
    ResPointDto.ResponsePointHistory getPointHistory(Pageable pageable, String userId);
}
