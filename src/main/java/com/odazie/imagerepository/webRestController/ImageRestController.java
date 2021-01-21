package com.odazie.imagerepository.webRestController;

import com.odazie.imagerepository.business.model.ResponseMessage;
import com.odazie.imagerepository.business.service.ImageService;
import com.odazie.imagerepository.business.service.UserService;
import com.odazie.imagerepository.data.entity.Image;
import com.odazie.imagerepository.data.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Api(description = "Endpoints Implementing the ADD & DELETE images idea of the challenge")
@ApiOperation(value = "", authorizations = { @Authorization(value="Bearer ") })
public class ImageRestController {


    private final ImageService imageService;
    private final UserService userService;

    public ImageRestController(ImageService imageService, UserService userService) {
        this.imageService = imageService;
        this.userService = userService;
    }

    @PostMapping("/single-upload")
    public ResponseEntity<ResponseMessage> uploadSingleImage(@RequestParam("image") MultipartFile image, Authentication authentication, @RequestParam("isPublic") Boolean isPublic) {

        User currentUser = userService.findUserByEmail(authentication.getName());
        String url = imageService.uploadSingleFile(image);

        imageService.saveGifToDB(url , image.getOriginalFilename() , isPublic,currentUser);

        ResponseMessage responseMessage = new ResponseMessage("Successfully uploaded");

        return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);
    }

    @PostMapping("/bulk-upload")
    public ResponseEntity<ResponseMessage> uploadingBulkImages(@RequestParam("images") MultipartFile[] images, Authentication authentication, @RequestParam("isPublic") Boolean isPublic  ){
        User currentUser = userService.findUserByEmail(authentication.getName());

        Arrays.stream(images).forEach(image -> {
            String url = imageService.uploadBulkImages(image, currentUser);
            imageService.saveGifToDB(url, image.getOriginalFilename(), isPublic, currentUser);
        });
        ResponseMessage responseMessage = new ResponseMessage("Successfully uploaded");

        return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);
    }

    @DeleteMapping("/images/{imageId}")
    public ResponseEntity<ResponseMessage> deleteSingleImage(@PathVariable Long imageId, Authentication authentication ){
        User currentUser = userService.findUserByEmail(authentication.getName());

        Image image = imageService.findByIdAndUser(imageId, currentUser);

        if(image == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        imageService.deleteSingleImage(image, currentUser);

        ResponseMessage responseMessage = new ResponseMessage("Successfully Deleted");

        return new ResponseEntity<>(responseMessage, HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/images")
    public ResponseEntity<ResponseMessage> deleteSelectedImages(@RequestParam List<Long> imageIds, Authentication authentication ){
        User currentUser = userService.findUserByEmail(authentication.getName());


        for (Long imageId: imageIds) {
            Image image = imageService.findByIdAndUser(imageId, currentUser);
            if (image == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            imageService.deleteSingleImage(image, currentUser);
        }

        ResponseMessage responseMessage = new ResponseMessage("All selected images of " + currentUser.getEmail() + " deleted");
        return new ResponseEntity<>(responseMessage, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/images/delete-all")
    public ResponseEntity<ResponseMessage> deleteAllUserImages(Authentication authentication){
        User currentUser = userService.findUserByEmail(authentication.getName());

        imageService.deleteAllImagesOfUser(currentUser);

        ResponseMessage responseMessage = new ResponseMessage("All images of " + currentUser.getEmail() + " has been deleted");
        return new ResponseEntity<>(responseMessage, HttpStatus.ACCEPTED);
    }





}
