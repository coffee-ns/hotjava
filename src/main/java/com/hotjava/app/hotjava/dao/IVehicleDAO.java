package com.hotjava.app.hotjava.dao;

import com.hotjava.app.hotjava.dto.Vehicle;

import java.util.List;

public interface IVehicleDAO {
    Vehicle save(Vehicle vehicle) throws Exception;

    List<Vehicle> fetchAll();

    Vehicle fetch(int id);

    void delete(int id);
}