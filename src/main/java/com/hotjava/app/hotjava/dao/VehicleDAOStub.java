package com.hotjava.app.hotjava.dao;

import com.hotjava.app.hotjava.dto.Vehicle;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Profile("test")
public class VehicleDAOStub implements IVehicleDAO {

    Map<Long, Vehicle> allVehicles = new HashMap<>(); // changed to long.

    @Override
    public Vehicle save(Vehicle vehicle) throws Exception {
        allVehicles.put(vehicle.getSubmissionID(), vehicle);
        return vehicle;
    }

    @Override
    public List<Vehicle> fetchAll() {
        return (List<Vehicle>) new ArrayList(allVehicles.values());
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