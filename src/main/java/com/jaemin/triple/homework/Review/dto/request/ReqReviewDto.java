package com.jaemin.triple.homework.Review.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class ReqReviewDto {

    /**
     * 리뷰 생성 DTO
     * @author jaemin
     * @version 1.0.0
     * 작성일 2022/07/04
    **/
    @Data @Builder @AllArgsConstructor @NoArgsConstructor
    public static class CreateReview {
        private String type;
        private String action;
        private String reviewId;
        private String content;
        private List<String> attachedPhotoIds;
        private String userId;
        private String placeId;
    }

}
