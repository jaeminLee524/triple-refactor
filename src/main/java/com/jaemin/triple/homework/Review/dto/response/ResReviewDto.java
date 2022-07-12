package com.jaemin.triple.homework.Review.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ResReviewDto {

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class createResDto {
        private String reviewId;
    }

}
