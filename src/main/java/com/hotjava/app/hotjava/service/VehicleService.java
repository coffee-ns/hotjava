package com.hotjava.app.hotjava.service;

import com.hotjava.app.hotjava.dao.IPhotoDAO;
import com.hotjava.app.hotjava.dao.IVehicleDAO;
import com.hotjava.app.hotjava.dto.Photo;
import com.hotjava.app.hotjava.dto.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleService implements IVehicleService {

    private ArrayList<Vehicle> vehicleArrayForTests; //Temporary list until dao setup

    @Autowired
    private IVehicleDAO vehicleDAO;

    @Autowired
    private IPhotoDAO photoDAO;

    public VehicleService() {

    }

    public VehicleService(IVehicleDAO vehicleDAO) {

        this.vehicleDAO = vehicleDAO;
    }

    @Override
    @Cacheable(value="vehicle", key="#id")
    public Vehicle fetchById(int id) {
       return vehicleDAO.fetch(id);
    }

    @Override
    @CacheEvict(value="vehicle", key="#id")
    public void delete(int id) throws Exception {
        //vehicleDAO.delete(id);
    }

    @Override
    public Vehicle save(Vehicle vehicle) throws Exception {

        if(vehicleIsComplete(vehicle) ){
            return vehicleDAO.save(vehicle);
        }

        //TODO set rejection message and return vehicle
        return vehicle;
    }

    @Override
    @Cacheable("vehicles")
    public List<Vehicle> fetchAll() {
        return vehicleDAO.fetchAll();
    }

    @Override
    @Cacheable("vehicles")
    public List<Vehicle> fetchVehicles(String name) {
        return vehicleDAO.fetchAll();
        //TODO change to get filtered vehicles
    }

    @Override
    public void saveImage(MultipartFile imageFile, Photo photo) throws IOException {
        photoDAO.save(photo);
        photoDAO.saveImage(imageFile, photo);
    }

    /**
     * Returns a boolean value indicating whether any of the vehicles attributes are null or default.
     * Additionally, checks the attributes of the photo object attached to the vehicle for any ull or default attributes
     * (Photo comments allowed to be null or empty for now).
     * @param  vehicle   the vehicle object passed in for its attributes to be evaluated
     * @return boolean value for if the vehicle's is considered to be completely filled out
     */
    private boolean vehicleIsComplete(Vehicle vehicle) {
        if(vehicle.getVehicleSubmissionID() == 0){
            return false;
        }
        if(vehicle.getVehicleMake().equals(null) || vehicle.getVehicleMake().isEmpty()){
            return false;
        }
        if(vehicle.getVehicleModel().equals(null) || vehicle.getVehicleModel().isEmpty()){
            return false;
        }
        if(vehicle.getVehicleScore().equals(null) || vehicle.getVehicleScore().isEmpty()){
            return false;
        }
        if(vehicle.getVehicleYear().equals(null) || vehicle.getVehicleYear().isEmpty()){
            return false;
        }
        if(vehicle.getVehicleDescription().equals(null) || vehicle.getVehicleDescription().isEmpty()){
            return false;
        }
        if(vehicle.getVehicleOwnerName().equals(null) || vehicle.getVehicleOwnerName().isEmpty()){
            return false;
        }
        Photo vehiclesPhoto = vehicle.getPhoto();
        return vehiclesPhoto.getPhotoId() != 0
                && !vehiclesPhoto.getPath().equals(null) && !vehiclesPhoto.getPath().isEmpty()
                && !vehiclesPhoto.getFileName().equals(null) && !vehiclesPhoto.getFileName().isEmpty();
    }
}