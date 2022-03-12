package com.hotjava.app.hotjava.dao;

import com.hotjava.app.hotjava.dto.Vehicle;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Profile("test")
public class VehicleDAOStub implements IVehicleDAO {

    Map<Integer, Vehicle> allVehicles = new HashMap<>();

    @Override
    public Vehicle save(Vehicle vehicle) throws Exception {
        allVehicles.put(vehicle.getVehicleSubmissionID(), vehicle);
        return vehicle;
    }

    @Override
    public List<Vehicle> fetchAll() {
        List<Vehicle> returnVehicles = new ArrayList(allVehicles.values());
        return returnVehicles;
    }

    @Override
    public Vehicle fetch(int id) {
        return allVehicles.get(id);
    }

    @Override
    public void delete(int id) {
        allVehicles.remove(id);

    }

}