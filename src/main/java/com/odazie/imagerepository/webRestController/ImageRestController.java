package com.odazie.imagerepository.webRestController;

import com.odazie.imagerepository.business.model.ResponseSpec;
import com.odazie.imagerepository.business.service.ImageService;
import com.odazie.imagerepository.business.service.UserService;
import com.odazie.imagerepository.data.entity.Image;
import com.odazie.imagerepository.data.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

@RestController
@RequestMapping("/api/v1")
public class ImageRestController {


    private final ImageService imageService;
    private final UserService userService;

    public ImageRestController(ImageService imageService, UserService userService) {
        this.imageService = imageService;
        this.userService = userService;
    }

    @PostMapping("/single-upload")
    public ResponseEntity<ResponseSpec> uploadSingleImage(@RequestParam("image") MultipartFile image, Authentication authentication, @RequestParam("isPublic") Boolean isPublic) {

        User currentUser = userService.findUserByEmail(authentication.getName());
        String url = imageService.uploadSingleFile(image);

        imageService.saveGifToDB(url , image.getOriginalFilename() , isPublic,currentUser);

        ResponseSpec responseSpec = new ResponseSpec("Successfully uploaded");

        return new ResponseEntity<>(responseSpec, HttpStatus.CREATED);
    }

    @PostMapping("/bulk-upload")
    public ResponseEntity<ResponseSpec> uploadingBulkImages(@RequestParam("images") MultipartFile[] images, Authentication authentication, @RequestParam("isPublic") Boolean isPublic  ){
        User currentUser = userService.findUserByEmail(authentication.getName());

        Arrays.stream(images).forEach(image -> {
            String url = imageService.uploadBulkImages(image, currentUser);
            imageService.saveGifToDB(url, image.getOriginalFilename(), isPublic, currentUser);
        });
        ResponseSpec responseSpec = new ResponseSpec("Successfully uploaded");

        return new ResponseEntity<>(responseSpec, HttpStatus.CREATED);
    }

    @DeleteMapping("/images/{imageId}")
    public ResponseEntity<ResponseSpec> deleteSingleImage(@PathVariable Long imageId, Authentication authentication ){
        User currentUser = userService.findUserByEmail(authentication.getName());

        Image image = imageService.findByIdAndUser(imageId, currentUser);

        if(image == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        imageService.deleteSingleImage(image, currentUser);

        ResponseSpec responseSpec = new ResponseSpec("Successfully Deleted");

        return new ResponseEntity<>(responseSpec, HttpStatus.CREATED);
    }


}
