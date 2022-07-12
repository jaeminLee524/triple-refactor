package com.jaemin.triple.homework.Place.service;

import com.jaemin.triple.homework.Place.dto.request.ReqPlaceDto;

public interface PlaceService {

    String createPlace(ReqPlaceDto.CreateReqDto createReqDto);
}
