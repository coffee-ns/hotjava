package com.hotjava.app.hotjava.dto;

import lombok.Data;

public @Data
class Photo {
    private int photoId;
    private String path;
    private String fileName;
    private String comments;
}
