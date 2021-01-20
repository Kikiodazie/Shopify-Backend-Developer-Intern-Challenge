package com.odazie.imagerepository.business.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.odazie.imagerepository.data.entity.Image;
import com.odazie.imagerepository.data.entity.User;
import com.odazie.imagerepository.data.repository.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

@Service
public class ImageService {

    private final ImageRepository imageRepository;
    private final Cloudinary cloudinaryConfig;

    public ImageService(ImageRepository imageRepository, Cloudinary cloudinaryConfig) {
        this.imageRepository = imageRepository;
        this.cloudinaryConfig = cloudinaryConfig;
    }

    public String uploadSingleFile(MultipartFile image) {
        try {
            File uploadedFile = convertMultiPartToFile(image);
            Map uploadResult = cloudinaryConfig.uploader().upload(uploadedFile, ObjectUtils.asMap("public_id", image.getOriginalFilename()));
            boolean isDeleted = uploadedFile.delete();

            if (isDeleted){
                System.out.println("File successfully deleted");
            }else
                System.out.println("File doesn't exist");
            return  uploadResult.get("url").toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String uploadBulkImages(MultipartFile image, User currentUser) {


        LocalDate date = LocalDate.now();
        String currentDate = String.valueOf(date);
        try {
            File uploadedFile = convertMultiPartToFile(image);

            // This bulk upload saves the images in a particular folder by date
            Map uploadResult = cloudinaryConfig.uploader().upload(uploadedFile, ObjectUtils.asMap("folder","user_"+currentUser.getEmail()+"_Bulk_Uploaded_At_"+currentDate, "public_id", image.getOriginalFilename()));
            boolean isDeleted = uploadedFile.delete();

            if (isDeleted){
                System.out.println("File successfully deleted");
            }else
                System.out.println("File doesn't exist");
            return  uploadResult.get("url").toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }




    public ImageRepository getImageRepository() {
        return imageRepository;
    }

    public void saveGifToDB(String url, String title, Boolean isPublic, User currentUser) {
        Image image = new Image();

        image.setImageUrl(url);
        image.setPublic(isPublic);
        image.setTitle(title);
        image.setUser(currentUser);

        getImageRepository().save(image);

    }
}
