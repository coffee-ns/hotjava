package com.hotjava.app.hotjava;

import com.hotjava.app.hotjava.dto.Photo;
import com.hotjava.app.hotjava.dto.Vehicle;
import com.hotjava.app.hotjava.service.IVehicleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class HotJavaApplicationIntegrationTests {

    @Autowired
    private IVehicleService vehicleService;

    private Vehicle vehicle = new Vehicle();
    private Photo photo = new Photo();

    @Test
    void contextLoads() {
    }

    /**
     * Validate that the DAOStub and service can add vehicles.
     */
    @Test
    void verifyServiceCanAddVehicles() throws Exception{
        givenVehicleAndItsPhotoWerePopulated();
        whenVehicleIsStored();
        List<Vehicle> vehicles= whenVehiclesAreRetrieved();
        thenVehiclesContainStored(vehicles);
    }

    void givenVehicleAndItsPhotoWerePopulated(){
        vehicle.setVehicleSubmissionID(8675309);
        vehicle.setVehicleOwnerName("test name");
        vehicle.setVehicleDescription("test desc");
        vehicle.setVehicleYear("1999");
        vehicle.setVehicleMake("test make");
        vehicle.setVehicleModel("test model");
        vehicle.setVehicleScore("test score");
        vehicle.setPhoto(photo);
        photo.setPhotoId(111);
        photo.setFileName("civic-type-r.jpg");
        photo.setPath("src/main/resources/img/civic-type-r.jpg");
    }

    private void whenVehicleIsStored() throws Exception{
        vehicleService.save(vehicle);
    }

    private List<Vehicle> whenVehiclesAreRetrieved() {
        return vehicleService.fetchAll();
    }

    private void thenVehiclesContainStored(List<Vehicle> vehicles) throws Exception{
        assertTrue(vehicles.contains(vehicle));
    }
}
