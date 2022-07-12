package com.jaemin.triple.homework.Place.controller;

import com.jaemin.triple.homework.Place.dto.request.ReqPlaceDto;
import com.jaemin.triple.homework.Place.dto.response.ResPlaceDto;
import com.jaemin.triple.homework.global.dto.Result;
import com.jaemin.triple.homework.Place.service.PlaceService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/place")
@RequiredArgsConstructor
@RestController
public class PlaceController {

    private final PlaceService placeService;

    /**
     * 장소 등록
     * @author jaemin
     * @version 1.0.0
     * 작성일 2022/07/03
     **/
    @ApiOperation(value = "장소 등록")
    @PostMapping("/v1/places")
    public ResponseEntity createPlace(@RequestBody ReqPlaceDto.CreateReqDto createReqDto) {

        String placeId = placeService.createPlace(createReqDto);

        ResPlaceDto.CreateResDto createResDto = ResPlaceDto.CreateResDto.of(placeId);

        return ResponseEntity.ok(Result.createSuccessResult(createResDto));
    }
}
