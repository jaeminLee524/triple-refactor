package com.jaemin.triple.homework.Point.controller;

import com.jaemin.triple.homework.Point.dto.response.ResPointDto;
import com.jaemin.triple.homework.global.dto.Result;
import com.jaemin.triple.homework.Point.service.PointService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/point")
@RestController
public class PointController {

    private final PointService pointService;

    /**
     * 누적 포인트 내역 조회
     * @author jaemin
     * @version 1.0.0
     * 작성일 2022/07/03
    **/
    @ApiOperation(value = "사용자 별 누적 포인트 내역 조회, 총점 조회")
    @GetMapping("/v1/points/history/{userId}")
    public ResponseEntity history(@PathVariable("userId") String userId,
                                  @PageableDefault(page = 0, size = 10) Pageable pageable) {
        ResPointDto.ResponsePointHistory pointHistory = pointService.getPointHistory(pageable, userId);

        return ResponseEntity.ok(Result.createSuccessResult(pointHistory));
    }

}
