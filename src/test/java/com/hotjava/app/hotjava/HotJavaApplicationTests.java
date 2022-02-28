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
		int submissionId = 8675309;
		 String OwnerName = "test name";
		 String Description ="test desc";
		 String Year = "1999";
	     String Make = "test make";
		 String Model = "test model";
		 String Score = "test score";
		 Photo Photo = new Photo();
		 Photo.setPhotoId(111);

		Vehicle testVehicle = new Vehicle();
		testVehicle.setSubmissionID(submissionId);
		testVehicle.setOwnerName(OwnerName);
		testVehicle.setDescription(Description);
		testVehicle.setYear(Year);
		testVehicle.setMake(Make);
		testVehicle.setModel(Model);
		testVehicle.setScore(Score);
		testVehicle.setPhoto(Photo);
		Photo.setPhotoId(111);
		Photo.setFileName("civic-type-r.jpg");
		Photo.setPath("src/main/resources/img/civic-type-r.jpg");

		assertEquals(submissionId,testVehicle.getSubmissionID());
		assertEquals(OwnerName,testVehicle.getOwnerName());
		assertEquals(Description,testVehicle.getDescription());
		assertEquals(Year,testVehicle.getYear());
		assertEquals(Make,testVehicle.getMake());
		assertEquals(Model,testVehicle.getModel());
		assertEquals(Score,testVehicle.getScore());
		assertEquals(Photo, testVehicle.getPhoto());

	}

	/**
	 * Validate that the DAOStub and service can add vehicles.
	 */
	@Test
	void verifyServiceCanAddVehicles() throws Exception{
		int submissionId = 8675309;
		String OwnerName = "test name";
		String Description ="test desc";
		String Year = "1999";
		String Make = "test make";
		String Model = "test model";
		String Score = "test score";
		Photo Photo = new Photo();
		Photo.setPhotoId(111);
		Photo.setFileName("civic-type-r.jpg");
		Photo.setPath("src/main/resources/img/civic-type-r.jpg");

		Vehicle testVehicle = new Vehicle();
		testVehicle.setSubmissionID(submissionId);
		testVehicle.setOwnerName(OwnerName);
		testVehicle.setDescription(Description);
		testVehicle.setYear(Year);
		testVehicle.setMake(Make);
		testVehicle.setModel(Model);
		testVehicle.setScore(Score);
		testVehicle.setPhoto(Photo);

		vehicleServiceNoMock.save(testVehicle);

		List<Vehicle> vehicleList = vehicleServiceNoMock.fetchAll();

		Boolean Present = false;
		for (Vehicle v: vehicleList) {
			if(v.getSubmissionID() == submissionId){
				if(v == testVehicle){
					Present = true;
				}
			}
		}
        assertTrue(Present);
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
		mockVehicle.setSubmissionID(8675309);
		mockVehicle.setOwnerName("coffee");
		mockVehicle.setDescription("black");
		mockVehicle.setYear("2001");
		mockVehicle.setMake("Nissan");
		mockVehicle.setModel("Silvia");
		mockVehicle.setScore("0");
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
		mockVehicle.setSubmissionID(909);
		mockVehicle.setOwnerName("coffee");
		mockVehicle.setDescription("black");
		mockVehicle.setYear("2001");
		mockVehicle.setMake("Nissan");
		mockVehicle.setModel("Silvia");
		mockVehicle.setScore("0");
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
