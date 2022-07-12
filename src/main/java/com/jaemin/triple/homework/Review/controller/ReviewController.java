package com.jaemin.triple.homework.Review.controller;

import com.jaemin.triple.homework.Review.dto.request.ReqReviewDto;
import com.jaemin.triple.homework.Review.dto.response.ResReviewDto;
import com.jaemin.triple.homework.Review.exception.ReviewException;
import com.jaemin.triple.homework.global.dto.Result;
import com.jaemin.triple.homework.Review.service.ReviewService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ReviewController {

    private final ReviewService reviewService;

    /**
     * 특정 장소에 대한 리뷰 생성, 수정, 삭제
     * @author jaemin
     * @version 1.0.0
     * 작성일 2022/07/03
     **/
    @ApiOperation(value = "리뷰 생성, 수정, 삭제")
    @PostMapping("/events")
    public ResponseEntity createReview(@RequestBody ReqReviewDto.CreateReview createReview) {
        switch (createReview.getAction()) {
            case "ADD":
                String reviewId = reviewService.createReview(createReview);
                return ResponseEntity.ok(Result.createSuccessResult(new ResReviewDto.createResDto(reviewId)));
            case "MOD":
                reviewService.modifyReview(createReview);
                return ResponseEntity.ok(null);
            case "DELETE":
                reviewService.deleteReview(createReview);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            default:
                throw new ReviewException(HttpStatus.NOT_FOUND, "해당 요청을 찾을 수 없습니다.");
        }
    }
}
