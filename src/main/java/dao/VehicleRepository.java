package com.hotjava.app.hotjava.dao;

import com.hotjava.app.hotjava.dto.Vehicle;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;

@Profile("!test")
public interface VehicleRepository extends CrudRepository<Vehicle, Integer> {
}