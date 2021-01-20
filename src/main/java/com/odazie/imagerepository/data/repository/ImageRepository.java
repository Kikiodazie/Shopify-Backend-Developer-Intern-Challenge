package com.odazie.imagerepository.data.repository;

import com.odazie.imagerepository.data.entity.Image;
import com.odazie.imagerepository.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    Image findByImageIdAndUser(Long imageId, User user);

}
