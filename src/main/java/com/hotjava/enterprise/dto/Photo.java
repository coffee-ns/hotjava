package com.hotjava.enterprise.dto;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;


public @Data
class Photo {
    private int photoId;
    private String path;
    private String fileName;
    private String comments;
}
