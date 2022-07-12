package com.jaemin.triple.homework.Place.entity;

import com.jaemin.triple.homework.global.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Place extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(nullable = false, unique = true)
    private String placeId;

    public Place(String name, String placeId) {
        this.name = name;
        this.placeId = placeId;
    }

    public static Place of(String name, String placeId) {
        return new Place(name, placeId);
    }
}
