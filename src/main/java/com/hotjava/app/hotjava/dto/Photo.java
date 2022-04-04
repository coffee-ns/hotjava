package com.hotjava.app.hotjava.dto;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="photos")
public @Data class Photo {

    @Id
    @GeneratedValue
    private int photoId;
    private String path;
    private String fileName;
    private String comments;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name="vehicle_submissionid" )
    private Vehicle vehicle;
}
