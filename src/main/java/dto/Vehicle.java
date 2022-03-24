package com.hotjava.app.hotjava.dto;

import lombok.Data;

public @Data
class Vehicle {
    private int vehicleSubmissionID;
    private String vehicleOwnerName;
    private String vehicleDescription;
    private String vehicleYear;
    private String vehicleMake;
    private String vehicleModel;
    private int vehicleScore;

    private Photo photo;
}