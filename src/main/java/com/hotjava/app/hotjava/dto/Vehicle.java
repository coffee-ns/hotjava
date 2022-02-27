package com.hotjava.app.hotjava.dto;

import lombok.Data;

public @Data
class Vehicle {
    private int SubmissionID;
    private String OwnerName;
    private String Description;
    private String Year;
    private String Make;
    private String Model;
    private String Score;
    private Photo photo;
}