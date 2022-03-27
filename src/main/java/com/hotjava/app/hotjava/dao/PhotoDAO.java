package com.hotjava.app.hotjava.dao;

import com.hotjava.app.hotjava.dto.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Repository
public class PhotoDAO implements IPhotoDAO {

    @Override
    public void save(Photo photo) {
    //TODO photo repo
    }

    @Override
    public void saveImage(MultipartFile imageFile, Photo photo) throws IOException {
        try {
            System.out.println("saveImage " + photo.getPhotoId());
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }

}
