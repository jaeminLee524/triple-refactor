package com.jaemin.triple.homework.Point.service;

import com.jaemin.triple.homework.Point.dto.response.ResPointDto;
import com.jaemin.triple.homework.Point.entity.Point;
import com.jaemin.triple.homework.Point.repository.PointRepositoryCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PointServiceImpl implements PointService {

    private final PointRepositoryCustom pointRepositoryCustom;

    /**
     * 포인트 히스토리 내역, 포인트 총점 조회
     * @author jaemin
     * @version 1.0.0
     * 작성일 2022/07/07
    **/
    @Override
    public ResPointDto.ResponsePointHistory getPointHistory(Pageable pageable, String userId) {

        // 사용자 별 포인트 내역 조회
        Page<Point> getPointHistory = pointRepositoryCustom.getPointHistory(pageable, userId);

        List<ResPointDto.PointHistory> pointHistories = getPointHistory.getContent().stream()
                .map(point -> ResPointDto.PointHistory.of(point))
                .collect(Collectors.toList());

        // 사용자의 총 포인트 점수 조회
        long pointSum = pointRepositoryCustom.getPointSum(userId);

        ResPointDto.ResponsePointHistory responsePointHistory =
                new ResPointDto.ResponsePointHistory(pointSum, pointHistories, getPointHistory.getNumber(), getPointHistory.getTotalPages());

        return responsePointHistory;
    }

}
