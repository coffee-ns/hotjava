package com.hotjava.app.hotjava.dao;

import com.hotjava.app.hotjava.dto.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("vehicleDAO")
public class VehicleSQLDAO implements IVehicleDAO{

    @Autowired
    VehicleRepository vehicleRepository;

    @Override
    public Vehicle save(Vehicle vehicle) throws Exception {
        Vehicle createdVehicle = vehicleRepository.save(vehicle);
        return createdVehicle;
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
        Vehicle retirevedVehicle = vehicleRepository.findById(id).get();
        return retirevedVehicle;
    }

    @Override
    public void delete(int id) {
            vehicleRepository.deleteById(id);

    }
}
