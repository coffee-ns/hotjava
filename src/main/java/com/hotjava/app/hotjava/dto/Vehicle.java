package com.hotjava.app.hotjava.dto;

import javax.persistence.*;
import lombok.Data;

import java.io.Serializable;


@Entity
public @Data
class Vehicle implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int submissionID;
    private String ownerName;
    private String description;
    private String year;
    private String make;
    private String model;
    private int score;
    private int photoID;
}