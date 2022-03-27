package com.hotjava.app.hotjava.dto;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;


public @Data
//@Table(name="photos")
class Photo implements Serializable {


    private int photoId;
    private String path;
    private String fileName;
    private String comments;

   // @ToString.Exclude
   // @OneToOne
   // @JoinColumn(name="vehicle_id",referencedColumnName = "id" )
    private int vehicleID;
}
