package com.hotjava.app.hotjava.service;

import com.hotjava.app.hotjava.dto.Photo;
import com.hotjava.app.hotjava.dto.Vehicle;
import org.springframework.cache.annotation.Cacheable;
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
     * Returns a vehicle from the DAO with a different ID than the user is currently viewing
     * Additionally if the dao has only 1 vehicle it will return the same vehicle
     * @param  currentId the current vehicle id of which we will be looking for a different one in the dao
     * @return Vehicle returns a different vehicle than the one who's id was passed in
     */
    @Cacheable("vehicles")
    Vehicle fetchDifferentVehicle(int currentId);

    /**
     * returns all Vehicles matching a passed string value argument.
     * @param name String to match vehicle names to.
     * @return a List of Vehicles with name matching combined name param.
     * TODO specify which attribute of Vehicle we want to filter on
     */
    List<Vehicle> fetchVehiclesByMake(String name) throws IOException;

    /**
     * saves an image file along with associated photo data
     * @param imageFile file containing image.
     * @param photo Photo object with associated data
     * @return a List of Vehicles with name matching combined name param.
     */

     void saveImage(MultipartFile imageFile, Photo photo) throws IOException;

    /**
     * Returns a vehicles score value after updating the value based on arguments.
     * @param  vehicle   the vehicle object passed in for its attributes to be evaluated
     * @param  upVote   boolean value for whether the user Upvoted or Downvoted the vehicle
     * @return vehicles updated score
     */
    int updateVehicleScore(Vehicle vehicle, boolean upVote) throws Exception;
}