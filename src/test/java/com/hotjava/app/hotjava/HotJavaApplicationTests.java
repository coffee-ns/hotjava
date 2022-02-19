package com.hotjava.app.hotjava;

import com.hotjava.app.hotjava.dto.Photo;
import com.hotjava.app.hotjava.dto.Vehicle;
import com.hotjava.app.hotjava.service.IVehicleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class HotJavaApplicationTests {

	@Autowired
	IVehicleService vehicleService;

	@Test
	void contextLoads() {
	}

	/**
	 * Validate that the DTO properties for Vehicle can be set and retrieved.
	 */
	@Test
	void verifyVehicleProperties(){
		int vehicleSubmissionID = 8675309;
		 String vehicleOwnerName = "test name";
		 String vehicleDescription ="test desc";
		 String vehicleYear = "1999";
	     String vehicleMake = "test make";
		 String vehicleModel = "test model";
		 String vehicleScore = "test score";
		 Photo vehiclePhoto = new Photo();

		Vehicle testVehicle = new Vehicle();
		testVehicle.setVehicleSubmissionID(vehicleSubmissionID);
		testVehicle.setVehicleOwnerName(vehicleOwnerName);
		testVehicle.setVehicleDescription(vehicleDescription);
		testVehicle.setVehicleYear(vehicleYear);
		testVehicle.setVehicleMake(vehicleMake);
		testVehicle.setVehicleModel(vehicleModel);
		testVehicle.setVehicleScore(vehicleScore);
		testVehicle.setPhoto(vehiclePhoto);

		assertEquals(vehicleSubmissionID,testVehicle.getVehicleSubmissionID());
		assertEquals(vehicleOwnerName,testVehicle.getVehicleOwnerName());
		assertEquals(vehicleDescription,testVehicle.getVehicleDescription());
		assertEquals(vehicleYear,testVehicle.getVehicleYear());
		assertEquals(vehicleMake,testVehicle.getVehicleMake());
		assertEquals(vehicleModel,testVehicle.getVehicleModel());
		assertEquals(vehicleScore,testVehicle.getVehicleScore());
		assertEquals(vehiclePhoto, testVehicle.getPhoto());

	}

	/**
	 * Validate that the DAOStub and service can add vehicles.
	 */
	@Test
	void verifyServiceCanAddVehicles() throws Exception{
		int vehicleSubmissionID = 8675309;
		String vehicleOwnerName = "test name";
		String vehicleDescription ="test desc";
		String vehicleYear = "1999";
		String vehicleMake = "test make";
		String vehicleModel = "test model";
		String vehicleScore = "test score";
		Photo vehiclePhoto = new Photo();

		Vehicle testVehicle = new Vehicle();
		testVehicle.setVehicleSubmissionID(vehicleSubmissionID);
		testVehicle.setVehicleOwnerName(vehicleOwnerName);
		testVehicle.setVehicleDescription(vehicleDescription);
		testVehicle.setVehicleYear(vehicleYear);
		testVehicle.setVehicleMake(vehicleMake);
		testVehicle.setVehicleModel(vehicleModel);
		testVehicle.setVehicleScore(vehicleScore);
		testVehicle.setPhoto(vehiclePhoto);

		vehicleService.save(testVehicle);

		List<Vehicle> vehicleList = vehicleService.fetchAll();

		Boolean vehiclePresent = false;
		for (Vehicle v: vehicleList) {
			if(v.getVehicleSubmissionID() == vehicleSubmissionID){
				if(v == testVehicle){
					vehiclePresent = true;
				}
			}
		}
        assertTrue(vehiclePresent);
	}


}
