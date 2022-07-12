package com.jaemin.triple.homework.domain.place.repository;

import com.jaemin.triple.homework.Place.entity.Place;
import com.jaemin.triple.homework.Place.repository.PlaceRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

@ActiveProfiles(profiles = "test-container")
@SpringBootTest
public class PlaceRepositoryTest {

    @Autowired
    PlaceRepository placeRepository;

    @DisplayName("호텔 저장 테스트")
    @Test
    void saveHotel() {
        //given
        Place 제주_신라_호텔 = Place.of("제주 신라 호텔", UUID.randomUUID().toString());

        //when
        Place savedPlace = placeRepository.save(제주_신라_호텔);

        //then
        Assertions.assertThat(savedPlace.getName()).isEqualTo("제주 신라 호텔");
        Assertions.assertThat(placeRepository.findAll().size()).isEqualTo(1);

    }
}
