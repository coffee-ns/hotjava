package com.hotjava.app.hotjava.dao;

import com.hotjava.app.hotjava.dto.Vehicle;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository

public class VehicleDAOStub implements IVehicleDAO {

    Map<Integer, Vehicle> allVehicles = new HashMap<>();

    @Override
    public Vehicle save(Vehicle vehicle) throws Exception {
        allVehicles.put(vehicle.getVehicleSubmissionID(), vehicle);
        return vehicle;
    }

    @Override
    public List<Vehicle> fetchAll() {
        return  new ArrayList(allVehicles.values());
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