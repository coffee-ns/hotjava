package com.hotjava.app.hotjava.dto;

import javax.persistence.*;
import lombok.Data;



@Entity
public @Data
class Vehicle {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private int vehicleSubmissionID;
    private String vehicleOwnerName;
    private String vehicleDescription;
    private String vehicleYear;
    private String vehicleMake;
    private String vehicleModel;
    private int vehicleScore;

    @OneToOne(mappedBy = "vehicle")
    private Photo photo;
}