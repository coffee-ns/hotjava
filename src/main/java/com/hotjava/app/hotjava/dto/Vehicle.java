package com.hotjava.app.hotjava.dto;

import javax.persistence.*;
import lombok.Data;

import java.io.Serializable;


@Entity
public @Data
class Vehicle implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int vehicleSubmissionID;
    private String vehicleOwnerName;
    private String vehicleDescription;
    private String vehicleYear;
    private String vehicleMake;
    private String vehicleModel;
    private int vehicleScore;

   // @OneToOne(mappedBy = "vehicle")
    private int photoID;
}