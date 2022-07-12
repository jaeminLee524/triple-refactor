package com.jaemin.triple.homework.Review.service;

import com.jaemin.triple.homework.Review.dto.request.ReqReviewDto;

public interface ReviewService {

    String createReview(ReqReviewDto.CreateReview createReview);

    void modifyReview(ReqReviewDto.CreateReview modifyReview);

    void deleteReview(ReqReviewDto.CreateReview deleteReview);
}
