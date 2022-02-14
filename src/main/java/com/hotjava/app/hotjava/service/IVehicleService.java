package com.hotjava.app.hotjava.service;

import com.hotjava.app.hotjava.dto.Photo;
import com.hotjava.app.hotjava.dto.Vehicle;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IVehicleService {
    /**
     * Fetch a vehicle with a given ID.
     * @param id a unique identifier for a vehicle.
     * @return the matching vehicle, or null if no matches found.
     */
    Vehicle fetchById(int id);

    /**
     * delete a vehicle with a given ID.
     * @param id a unique identifier for a vehicle.
     * @return void, throws exception.
     */
    void delete(int id) throws Exception;

    /**
     * save a Vehicle with a valid vehicle object.
     * @param vehicle is a valid vehicle intended to save.
     * @return the vehicle being saved, throws exception.
     */
    Vehicle save(Vehicle vehicle) throws Exception;

    /**
     * returns all Vehicles.
     * @param
     * @return a List of Vehicles.
     */
    List<Vehicle> fetchAll();

    /**
     * returns all Vehicles matching a passed string value argument.
     * @param name String to match vehicle names to.
     * @return a List of Vehicles with name matching combined name param.
     * TODO specify which attribute of Vehicle we want to filter on
     */
    List<Vehicle> fetchVehicles(String name) throws IOException;

    /**
     * saves an image file along with associated photo data
     * @param imageFile file containing image.
     * @param photo Photo object with associated data
     * @return a List of Vehicles with name matching combined name param.
     */

     void saveImage(MultipartFile imageFile, Photo photo) throws IOException;

}