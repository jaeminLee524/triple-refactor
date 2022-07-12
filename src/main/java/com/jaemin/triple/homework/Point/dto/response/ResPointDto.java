package com.jaemin.triple.homework.Point.dto.response;

import com.jaemin.triple.homework.Point.entity.Point;
import com.jaemin.triple.homework.global.dto._Page;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ResPointDto {

    /**
     * 포인트 누적 히스토리 조회 Res Dto
     * @author jaemin
     * @version 1.0.0
     * 작성일 2022/07/07
     **/
    @Data
    public static class ResponsePointHistory {
        private long pointSumDto;
        private List<ResPointDto.PointHistory> pointHistories;
        private _Page page;

        public ResponsePointHistory(long pointSumDto, List<PointHistory> pointHistories, int startPage, int endPage) {
            this.pointSumDto = pointSumDto;
            this.pointHistories = pointHistories;
            this.page = new _Page(startPage, endPage);
        }
    }

    /**
     * 포인트 누적 히스토리 조회 가공 DTO
     * @author jaemin
     * @version 1.0.0
     * 작성일 2022/07/07
     **/
    @Data
    public static class PointHistory {
        private long point; // 포인트(+, -)
        private String pointType; // 내용점수, 보너스점수, 취소점수
        private String pointAccTime; // 포인트 적립 시간

        public static PointHistory of(Point point) {
            PointHistory pointHistory = new PointHistory();
            pointHistory.point = point.getPoint(); // 포인트
            pointHistory.changePointAccTime(point.getCreatedAt()); // 포인트 적립 시간

            if (point.getPointType().name().equals("CONTENT")) {
                pointHistory.pointType = "내용 점수";
            } else if (point.getPointType().name().equals("BONUS")) {
                pointHistory.pointType = "보너스 점수";
            } else {
                pointHistory.pointType = "적립 취소 점수";
            }

            return pointHistory;
        }

        /** LocalDateTime -> String(yyy.dd.mm) **/
        public void changePointAccTime(LocalDateTime createdAt) {
            this.pointAccTime = createdAt.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        }
    }

}
