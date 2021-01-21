package com.odazie.imagerepository.data.repository;

import com.odazie.imagerepository.data.entity.Image;
import com.odazie.imagerepository.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    Image findByImageIdAndUser(Long imageId, User user);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM image WHERE user_user_id = :userId",

            nativeQuery = true

    )
    void deleteImagesOfUser(@Param("userId") Long userId);



}
