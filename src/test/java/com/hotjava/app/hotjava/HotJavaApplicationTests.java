package com.hotjava.app.hotjava;

import com.hotjava.app.hotjava.dao.IVehicleDAO;
import com.hotjava.app.hotjava.dto.Photo;
import com.hotjava.app.hotjava.dto.Vehicle;
import com.hotjava.app.hotjava.service.IVehicleService;
import com.hotjava.app.hotjava.service.VehicleService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class HotJavaApplicationTests {

	private IVehicleService vehicleService;
	private Vehicle mockVehicle = new Vehicle();

	@MockBean
	private IVehicleDAO vehicleDAO;

	@Autowired
	private IVehicleService vehicleServiceNoMock; //used for non-mockito tests

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
		 vehiclePhoto.setPhotoId(111);

		Vehicle testVehicle = new Vehicle();
		testVehicle.setVehicleSubmissionID(vehicleSubmissionID);
		testVehicle.setVehicleOwnerName(vehicleOwnerName);
		testVehicle.setVehicleDescription(vehicleDescription);
		testVehicle.setVehicleYear(vehicleYear);
		testVehicle.setVehicleMake(vehicleMake);
		testVehicle.setVehicleModel(vehicleModel);
		testVehicle.setVehicleScore(vehicleScore);
		testVehicle.setPhoto(vehiclePhoto);
		vehiclePhoto.setPhotoId(111);
		vehiclePhoto.setFileName("civic-type-r.jpg");
		vehiclePhoto.setPath("src/main/resources/img/civic-type-r.jpg");

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

		Photo vehiclePhoto = new Photo();
		vehiclePhoto.setPhotoId(111);
		vehiclePhoto.setFileName("civic-type-r.jpg");
		vehiclePhoto.setPath("src/main/resources/img/civic-type-r.jpg");

		Vehicle testVehicle = new Vehicle();
		testVehicle.setVehicleSubmissionID(8675309);
		testVehicle.setVehicleOwnerName("test name");
		testVehicle.setVehicleDescription("test desc");
		testVehicle.setVehicleYear("1999");
		testVehicle.setVehicleMake("test make");
		testVehicle.setVehicleModel("test model");
		testVehicle.setVehicleScore("test score");
		testVehicle.setPhoto(vehiclePhoto);

		vehicleServiceNoMock.save(testVehicle);

		List<Vehicle> vehicleList = vehicleServiceNoMock.fetchAll();

		Boolean vehiclePresent = false;
		for (Vehicle v: vehicleList) {
			if(v.getVehicleSubmissionID() == testVehicle.getVehicleSubmissionID()){
				if(v == testVehicle){
					vehiclePresent = true;
				}
			}
		}
        assertTrue(vehiclePresent);

	}

	/**
	 * Validate that a complete Vehicle form can be submitted with confirmation notification.
	 */
	@Test
	void vehicleFormSubmissionWithAllData() throws Exception {
		//TODO adjust method below after views and forms are created
		givenVehicleDataIsAvailable();
		whenCompleteVehicleFormIsSubmitted();
		thenVehicleCanBeSavedWithConfirmationMessage();
	}

	private void givenVehicleDataIsAvailable() throws Exception {
		Mockito.when(vehicleDAO.save(mockVehicle)).thenReturn(mockVehicle);
		vehicleService = new VehicleService(vehicleDAO);
	}

	private void whenCompleteVehicleFormIsSubmitted() {
		mockVehicle.setVehicleSubmissionID(8675309);
		mockVehicle.setVehicleOwnerName("coffee");
		mockVehicle.setVehicleDescription("black");
		mockVehicle.setVehicleYear("2001");
		mockVehicle.setVehicleMake("Nissan");
		mockVehicle.setVehicleModel("Silvia");
		mockVehicle.setVehicleScore("0");
		Photo vehiclePhoto = new Photo();
		vehiclePhoto.setPhotoId(111);
		vehiclePhoto.setFileName("civic-type-r.jpg");
		vehiclePhoto.setPath("src/main/resources/img/civic-type-r.jpg");
		mockVehicle.setPhoto(vehiclePhoto);
	}

	private void thenVehicleCanBeSavedWithConfirmationMessage() throws Exception {
		//TODO adjust method below after views and forms are created
		Vehicle createdVehicle = vehicleService.save(mockVehicle);
		assertEquals(mockVehicle,createdVehicle);
		verify(vehicleDAO, atLeastOnce()).save(mockVehicle);

		//TODO verify confirmation
	}




	/**
	 * Validate that an incomplete Vehicle form is rejected with "required field" message.
	 */
	@Test
	void vehicleIncompleteFormRejectsSubmission() throws Exception {
		givenVehicleDataIsAvailable();
		whenIncompleteVehicleForm();
		thenSubmmittedFormIsRejectedWithIncompleteMessage();
	}

	private void whenIncompleteVehicleForm() {
		mockVehicle = new Vehicle();
		mockVehicle.setVehicleSubmissionID(909);
		mockVehicle.setVehicleOwnerName("coffee");
		mockVehicle.setVehicleDescription("black");
		mockVehicle.setVehicleYear("2001");
		mockVehicle.setVehicleMake("Nissan");
		mockVehicle.setVehicleModel("Silvia");
		mockVehicle.setVehicleScore("0");
		Photo vehiclePhoto = new Photo();
		//photo has no values
		mockVehicle.setPhoto(vehiclePhoto);
	}

	private void thenSubmmittedFormIsRejectedWithIncompleteMessage() throws Exception {
		Vehicle rejectedVehicle = vehicleService.save(mockVehicle);
		assertEquals(mockVehicle,rejectedVehicle);
		//vehicleDAO should not be called
		verify(vehicleDAO, never()).save(mockVehicle);

		//TODO verify missing field message
	}

	/**
	 * Validate that vehicles score is updated with message when voting arrow is clicked.
	 */
	@Test
	void vehicleScoreUpdatedWhenVoted(){
		givenVehicleProfileIsPresent();
		whenUserVotesWithArrow();
		thenVehicleScoreIsUpdatedWithMessage();
	}

	private void givenVehicleProfileIsPresent() {
		//TODO
	}

	private void whenUserVotesWithArrow() {
		//TODO
	}

	private void thenVehicleScoreIsUpdatedWithMessage() {
		//TODO
	}


	/**
	 * Validate that page refreshes with new vehicle when vehicle is "skipped".
	 */
	@Test
	void newVehicleWhenSkipped(){
		givenVehicleProfileIsPresent();
		whenUserSelectsSkip();
		pageRefreshWithNewVehicle();
	}

	private void pageRefreshWithNewVehicle() {
		//TODO
	}

	private void whenUserSelectsSkip() {
		//TODO
	}


}
