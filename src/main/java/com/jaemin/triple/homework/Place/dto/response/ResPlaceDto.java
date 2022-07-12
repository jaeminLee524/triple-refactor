package com.jaemin.triple.homework.Place.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ResPlaceDto {

    /**
     * 장소 생성 응답 DTO
     * @author jaemin
     * @version 1.0.0
     * 작성일 2022/07/04
    **/
    @Data @Builder @AllArgsConstructor @NoArgsConstructor
    public static class CreateResDto {
        private String placeId;
    }

}
