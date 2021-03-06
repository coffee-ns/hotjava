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
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class VehicleService implements IVehicleService {

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
        vehicleDAO.delete(id);
    }

    @Override
    public Vehicle save(Vehicle vehicle) throws Exception {
       if(vehicleIsComplete(vehicle)) {
            return vehicleDAO.save(vehicle);
        }
        else{
            return vehicle;
        }
    }

    @Override
    @Cacheable("vehicles")
    public List<Vehicle> fetchAll() {
        return vehicleDAO.fetchAll();
    }


    @Override
    public Vehicle fetchDifferentVehicle(int currentId) {
        List<Vehicle> vehicleList = vehicleDAO.fetchAll();
        if(vehicleList.size() > 1) {
            int rnd = new Random().nextInt(vehicleList.size());
            var vehicle = vehicleList.get(rnd);
            if (vehicle.getSubmissionID() != currentId) {
                return vehicle;
            } else {
                return this.fetchDifferentVehicle(currentId);
            }
        }
        return vehicleList.get(0);
    }

    @Override
    public List<Vehicle> fetchVehiclesByMake(String searchString) {
        List<Vehicle> filteredVehicleList = vehicleDAO.fetchAll();
        filteredVehicleList = filteredVehicleList.stream()
                .filter(vehicle -> vehicle.getMake().contains(searchString))
                .collect(Collectors.toList());
        return filteredVehicleList;
    }

    @Override
    public void saveImage(MultipartFile imageFile, Photo photo) throws IOException {
        photoDAO.save(photo);
        photoDAO.saveImage(imageFile, photo);
    }


    @Override
    public int updateVehicleScore(Vehicle vehicle, boolean upVote) throws Exception {
        if(upVote){
           vehicle.setScore(vehicle.getScore() + 1);
           save(vehicle);
        }
        else if(vehicle.getScore() > 0){
            vehicle.setScore(vehicle.getScore() - 1);
            save(vehicle);
        }
        return vehicle.getScore();
    }

    /**
     * Returns a boolean value indicating whether any of the vehicles attributes are null or default exlusing the score.
     * Additionally, checks the attributes of the photo object attached to the vehicle for any ull or default attributes
     * (Photo comments allowed to be null or empty for now).
     * @param  vehicle   the vehicle object passed in for its attributes to be evaluated
     * @return boolean value for if the vehicle's is considered to be completely filled out
     */
    private boolean vehicleIsComplete(Vehicle vehicle) {

        if(vehicle.getMake().equals(null) || vehicle.getMake().isEmpty()){
            return false;
        }
        if(vehicle.getModel().equals(null) || vehicle.getModel().isEmpty()){
            return false;
        }

        return true;
    }
}