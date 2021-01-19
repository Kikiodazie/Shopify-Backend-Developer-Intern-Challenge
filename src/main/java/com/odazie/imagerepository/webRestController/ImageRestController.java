package com.odazie.imagerepository.webRestController;

import com.odazie.imagerepository.business.model.ResponseSpec;
import com.odazie.imagerepository.business.service.ImageService;
import com.odazie.imagerepository.business.service.UserService;
import com.odazie.imagerepository.data.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ImageRestController {


    private final ImageService imageService;
    private final UserService userService;

    public ImageRestController(ImageService imageService, UserService userService) {
        this.imageService = imageService;
        this.userService = userService;
    }

    @PostMapping("/single-upload")
    public ResponseEntity<ResponseSpec> uploadSingleImage(@RequestParam("image") MultipartFile image, Authentication authentication, @RequestParam("title") String title, @RequestParam("isPublic") Boolean isPublic) {

        User currentUser = userService.findUserByEmail(authentication.getName());
        String url = imageService.uploadFile(image);
        imageService.saveGifToDB(url, title , isPublic,currentUser);

        ResponseSpec responseSpec = new ResponseSpec("Successfully uploaded");

        return new ResponseEntity<>(responseSpec, HttpStatus.CREATED);
    }

}
