package com.hotjava.enterprise.dao;

import com.hotjava.enterprise.dto.Vehicle;

import java.util.List;

public interface IVehicleDAO {
    Vehicle save(Vehicle vehicle) throws Exception;

    List<Vehicle> fetchAll();

    Vehicle fetch(int id);

    void delete(int id);
}
