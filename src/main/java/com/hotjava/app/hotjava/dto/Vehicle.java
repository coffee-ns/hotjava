package com.hotjava.app.hotjava.dto;

import javax.persistence.*;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.util.List;


@Entity
public @Data
class Vehicle implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int submissionID;
    private String ownerName;
    @Column(length = 9999)
    private String description;
    private String year;
    private String make;
    private String model;
    private int score;

    @OneToMany(mappedBy = "vehicle")
    private List<Photo> photos;
}