package com.jaemin.triple.homework.Place.service;

import com.jaemin.triple.homework.Place.dto.request.ReqPlaceDto;
import com.jaemin.triple.homework.Place.entity.Place;
import com.jaemin.triple.homework.Place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PlaceServiceImpl implements PlaceService{

    private final PlaceRepository placeRepository;

    /**
     * 장소 등록
     * @author jaemin
     * @version 1.0.0
     * 작성일 2022/07/04
    **/
    @Transactional
    @Override
    public String createPlace(ReqPlaceDto.CreateReqDto createReqDto) {
        Place createPlace = Place.builder()
                .name(createReqDto.getName())
                .placeId(UUID.randomUUID().toString())
                .build();

        Place savedPlace = placeRepository.save(createPlace);

        return savedPlace.getPlaceId();
    }
}
