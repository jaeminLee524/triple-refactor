package com.jaemin.triple.homework.Review.service;

import com.jaemin.triple.homework.Place.repository.PlaceRepository;
import com.jaemin.triple.homework.Place.entity.Place;
import com.jaemin.triple.homework.Point.entity.PointType;
import com.jaemin.triple.homework.Point.repository.PointRepository;
import com.jaemin.triple.homework.Point.service.PointEventFacade;
import com.jaemin.triple.homework.Review.entity.Images;
import com.jaemin.triple.homework.Review.entity.Review;
import com.jaemin.triple.homework.Review.repository.ImagesRepository;
import com.jaemin.triple.homework.Review.repository.ReviewRepository;
import com.jaemin.triple.homework.Review.repository.ReviewRepositoryCustom;
import com.jaemin.triple.homework.User.entity.User;
import com.jaemin.triple.homework.Review.dto.request.ReqReviewDto;
import com.jaemin.triple.homework.Point.entity.Point;
import com.jaemin.triple.homework.Review.exception.ExistsReviewHistory;
import com.jaemin.triple.homework.Review.exception.ReviewException;
import com.jaemin.triple.homework.User.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import static com.jaemin.triple.homework.Point.entity.Point.EventType.*;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewRepositoryCustom reviewRepositoryCustom;
    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;
    private final PointRepository pointRepository;
    private final ImagesRepository imagesRepository;
    private final PointEventFacade pointEventFacade;

    /**
     * 리뷰 생성
     * @author jaemin
     * @version 1.0.0
     * 작성일 2022/07/04
     **/
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public String createReview(ReqReviewDto.CreateReview createReview) {

        User findUser = userRepository.findByUserId(createReview.getUserId()).orElseThrow(
                () -> new ReviewException(HttpStatus.BAD_REQUEST, createReview.getUserId() + "는 존재하지 않는 사용자입니다.")
        );

        Place findPlace = placeRepository.findByPlaceId(createReview.getPlaceId()).orElseThrow(
                () -> new ReviewException(HttpStatus.BAD_REQUEST, createReview.getPlaceId() + "는 존재하지 않는 장소입니다.")
        );

        // 사용자가 이미 해당 장소에 리뷰를 작성했는지 검증
        if (createReview.getAction().equals("ADD")) {
            if (reviewRepositoryCustom.existsReview(createReview.getUserId(), createReview.getPlaceId())) {
                throw new ExistsReviewHistory("이미 작성한 리뷰가 존재합니다.");
            }
        }

        String reviewId = UUID.randomUUID().toString();

        List<Images> imagesList = createReview.getAttachedPhotoIds().stream()
                .map(attachedPhotoId -> Images.of(attachedPhotoId))
                .collect(Collectors.toList());

        Review review = Review.of(createReview.getContent(), reviewId, findUser, findPlace, imagesList);

        // Review 저장
        Review savedReview = reviewRepository.save(review);

        // 리뷰 생성 서브 클래스 핸들링
        pointEventFacade.event(REVIEW_ADD, savedReview);

//        // 보너스 점수를 획득할 자격 검증
//        boolean flag = reviewRepositoryCustom.existsPlaceExcludeMe(createReview.getPlaceId(), createReview.getUserId());
//
//        if (StringUtils.hasText(createReview.getContent())) {
//            if (createReview.getAttachedPhotoIds().size() > 0) {
//                Point contentPoint = Point.of(2, PointType.CONTENT, UUID.randomUUID().toString(), findUser, savedReview);
//                if (!flag) { // 첫 리뷰인지 검증
//                    Point bonusPoint = Point.of(1, PointType.BONUS, UUID.randomUUID().toString(), findUser, savedReview);
//                    pointRepository.saveAll(Arrays.asList(contentPoint, bonusPoint));
//                } else {
//                    pointRepository.save(contentPoint);
//                }
//            }else {
//                Point contentPoint = Point.of(1, PointType.CONTENT, UUID.randomUUID().toString(), findUser, savedReview);
//                // 첫 리뷰인지 검증
//                if (!flag) {
//                    Point bonusPoint = Point.of(1, PointType.BONUS, UUID.randomUUID().toString(), findUser, savedReview);
//                    pointRepository.saveAll(Arrays.asList(contentPoint,bonusPoint));
//                }
//                pointRepository.save(contentPoint);
//            }
//        }
        return savedReview.getReviewId();
    }

    /**
     * 리뷰 수정
     * @author jaemin
     * @version 1.0.0
     * 작성일 2022/07/05
     **/
    @Transactional
    @Override
    public void modifyReview(ReqReviewDto.CreateReview modifyReview) {


        Review findReview = reviewRepository.findByReviewId(modifyReview.getReviewId()).orElseThrow(
                () -> new ReviewException(HttpStatus.BAD_REQUEST, modifyReview.getReviewId() + "는 존재하지 않는 리뷰입니다.")
        );

        List<String> reqPhotoIdList = new ArrayList<>(); // Request Source
        List<String> savedPhotoIdList = new ArrayList<>(); // DB Source

        // 리뷰 내용 수정
        findReview.updateContent(modifyReview.getContent());

        findReview.getImages().stream().forEach(image -> savedPhotoIdList.add(image.getAttachedPhotoId()));

        modifyReview.getAttachedPhotoIds().stream().forEach(id -> reqPhotoIdList.add(id));

        if (reqPhotoIdList.size() > 0) {
            List<String> needTobeSave = extractData(reqPhotoIdList, savedPhotoIdList);
            List<Images> saveImages = needTobeSave.stream().map(id -> Images.of(id, findReview)).collect(Collectors.toList());

            // DB에 저장된 사진 정보가 있었는지
            if (savedPhotoIdList.size() < 1) {
                Point plusPoint = Point.of(1, PointType.CONTENT, UUID.randomUUID().toString(), findReview.getUserId(), findReview);
                pointRepository.save(plusPoint);
            }

            imagesRepository.saveAll(saveImages);
        }

        // 삭제할 Id가 있고, 삭제 후 DB source가 없으면 포인트 -1
        if (savedPhotoIdList.size() > 0) {
            // 사진 데이터 생성
            List<String> needTobeDelete = extractData(savedPhotoIdList, reqPhotoIdList);
            List<Images> deleteImages = imagesRepository.findAllByAttachedPhotoIds(needTobeDelete).orElseThrow(
                    () -> new ReviewException(HttpStatus.BAD_REQUEST, modifyReview.getReviewId() + "는 존재하지 않는 사진입니다.")
            );

            // 사진 삭제
            findReview.getImages().removeAll(deleteImages);
            imagesRepository.deleteAll(deleteImages);

            // 삭제 후 사진 데이터가 없으면 포인트 -1
            Review review = reviewRepository.findByReviewId(modifyReview.getReviewId()).get();
            if (review.getImages().size() < 1) {
                Point minusPoint = Point.of(-1, PointType.CONTENT, UUID.randomUUID().toString(), review.getUserId(), review);
                pointRepository.save(minusPoint);
            }
        }
    }

    /**
     * 요청 데이터와 DB 데이터 값 비교
     * target 객체 기준으로 삭제 or 저장 데이터 리스트를 반환
     * **/
    public List<String> extractData(List<String> target, List<String> source) {
        return target.stream().filter(id -> !source.contains(id)).collect(Collectors.toList());
    }


    /**
     * 리뷰 삭제
     * @author jaemin
     * @version 1.0.0
     * 작성일 2022/07/07
    **/
    @Transactional
    @Override
    public void deleteReview(ReqReviewDto.CreateReview deleteReview) {

        String reqDelReviewId = deleteReview.getReviewId();
        Review findReview = reviewRepository.findByReviewId(reqDelReviewId).orElseThrow(
                () -> new ReviewException(HttpStatus.BAD_REQUEST, reqDelReviewId + "는 존재하지 않는 리뷰입니다.")
        );

        pointEventFacade.event(REVIEW_DELETE, findReview);

//        // Point 삭제 처리
//        long totalPoint = pointRepository.selectTotals(findReview.getId());
//        Point deletePoint = Point.of(-totalPoint, PointType.DELETE, UUID.randomUUID().toString(), findReview.getUserId(), findReview);
//        pointRepository.save(deletePoint);
//
        // Review, Images 삭제
        reviewRepository.delete(findReview);
    }

}