package com.hotjava.app.hotjava.dao;

import com.hotjava.app.hotjava.dto.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Repository
public class PhotoDAO implements IPhotoDAO {

    //@Autowired
   // private PhotoRepository photoRepository;

   // @Autowired
    //private KafkaTemplate<String, String> kafkaTemplate;




    @Override
    public void save(Photo photo) {
    //TODO photo repo
    }

    @Override
    public void saveImage(MultipartFile imageFile, Photo photo) throws IOException {
       /* Path currentPath = Paths.get(".");
        Path absolutePath = currentPath.toAbsolutePath();
        photo.setPath(absolutePath + "/src/main/resources/static/photos/");
        byte[] bytes = imageFile.getBytes();
        Path path = Paths.get(photo.getPath() + imageFile.getOriginalFilename());
        Files.write(path, bytes);
        kafkaTemplate.send("photoIn", path.normalize().toString());*/
        System.out.println("saveImage" + Integer.toString(photo.getPhotoId()));
    }

}
