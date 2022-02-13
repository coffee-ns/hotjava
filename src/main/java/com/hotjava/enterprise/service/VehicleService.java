package com.hotjava.enterprise.service;

import com.hotjava.enterprise.dao.IPhotoDAO;
import com.hotjava.enterprise.dao.IVehicleDAO;
import com.hotjava.enterprise.dto.Photo;
import com.hotjava.enterprise.dto.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class VehicleService implements IVehicleService {

    @Autowired
    private IVehicleDAO vehicleDAO;

    @Autowired
    private IPhotoDAO photoDAO;

    public VehicleService() {

    }

    public VehicleService(IVehicleDAO vehicleDAO) {

        this.vehiclenDAO = vehicleDAO;
    }

    @Override
    @Cacheable(value="vehicle", key="#id")
    public Vehicle fetchById(int id) {
        Vehicle foundVehicle = vehicleDAO.fetch(id);
        return foundSpecimen;
    }

    @Override
    @CacheEvict(value="vehicle", key="#id")
    public void delete(int id) throws Exception {
        vehicleDAO.delete(id);
    }

    @Override
    public Vehicle save(Vehicle vehicle) throws Exception {
        return vehicleDAO.save(vehicle);
    }

    @Override
    @Cacheable("vehicles")
    public List<Vehicle> fetchAll() {
        return vehicleDAO.fetchAll();
    }

    @Override
    @Cacheable("vehicles")
    public List<Vehicle> fetchVehicles(String name) throws IOException {
        return vehicleDAO.fetchVehicles(name);
    }

    @Override
    public void saveImage(MultipartFile imageFile, Photo photo) throws IOException {
        photoDAO.save(photo);
        photoDAO.saveImage(imageFile, photo);
    }

}
