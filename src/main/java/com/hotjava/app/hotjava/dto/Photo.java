package com.hotjava.app.hotjava.dto;

import lombok.Data;
import lombok.ToString;
import java.io.Serializable;


public @Data
class Photo implements Serializable {


    private int photoId;
    private String path;
    private String fileName;
    private String comments;
    private int vehicleID;
}
