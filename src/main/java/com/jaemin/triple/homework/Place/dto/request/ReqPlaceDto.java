package com.jaemin.triple.homework.Place.dto.request;

import lombok.Data;

public class ReqPlaceDto {

    /**
     * 장소 등록 요청 DTO
     * @author jaemin
     * @version 1.0.0
     * 작성일 2022/07/04
     **/
    @Data
    public static class CreateReqDto {
        private String name;
    }
}
