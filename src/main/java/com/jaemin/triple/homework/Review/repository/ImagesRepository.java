package com.jaemin.triple.homework.Review.repository;

import com.jaemin.triple.homework.Review.entity.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ImagesRepository extends JpaRepository<Images, Long> {
    @Query("select i from Images i where i.attachedPhotoId in :deleteIds")
    Optional<List<Images>> findAllByAttachedPhotoIds(@Param("deleteIds") List<String> deleteIds);
}
