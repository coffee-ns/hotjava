package com.hotjava.app.hotjava.dao;

import com.hotjava.app.hotjava.dto.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Repository("vehicleDAO")
@Repository
@Profile({"dev", "default"})
public class VehicleSQLDAO implements IVehicleDAO{

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    CacheManager cacheManager;


    @Override
    public Vehicle save(Vehicle vehicle) throws Exception {
        Vehicle veh = vehicleRepository.save(vehicle);
        cacheManager.getCache("vehicles").clear();
        return veh;
    }

    @Override
    public List<Vehicle> fetchAll() {
        List<Vehicle> allVehicles = new ArrayList<>();
        Iterable<Vehicle> retrievedVehicles = vehicleRepository.findAll();
        for (Vehicle v : retrievedVehicles) {
            allVehicles.add(v);
        }
        return allVehicles;
    }

    @Override
    public Vehicle fetch(int id) {
        return vehicleRepository.findById(id).get();
    }

    @Override
    public void delete(int id) {
            vehicleRepository.deleteById(id);
    }
}
