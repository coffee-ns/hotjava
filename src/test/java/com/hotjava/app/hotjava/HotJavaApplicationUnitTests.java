package com.hotjava.app.hotjava;

import com.hotjava.app.hotjava.dao.IVehicleDAO;
import com.hotjava.app.hotjava.dto.Photo;
import com.hotjava.app.hotjava.dto.Vehicle;
import com.hotjava.app.hotjava.service.IVehicleService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class HotJavaApplicationUnitTests {

	@MockBean
	private IVehicleDAO vehicleDAO;

	@Autowired
	private IVehicleService vehicleService;

	private Vehicle mockVehicle = new Vehicle();
	private Photo mockPhoto = new Photo();

	private final int VEHICLE_ID = 8675309;
	private final String VEHICLE_OWNER_NAME = "test name";
	private final String VEHICLE_DESCRIPTION = "test desc";
	private final String VEHICLE_YEAR = "1999";
	private final String VEHICLE_MAKE = "test make";
	private final String VEHICLE_MODEL = "test model";
	private final String VEHICLE_SCORE = "test score";
	private final int PHOTO_ID = 111;
	private final String PHOTO_FILE_NAME = "civic-type-r.jpg";
	private final String PHOTO_PATH = "src/main/resources/img/";


	@Test
	void contextLoads() {
	}

	/**
	 * Validate that the DTO properties for Vehicle can be set and retrieved.
	 */
	@Test
	void verifyVehicleProperties(){
		givenMockVehicleAndPhotoWerePopulated();

		assertEquals(VEHICLE_ID,mockVehicle.getVehicleSubmissionID());
		assertEquals(VEHICLE_OWNER_NAME,mockVehicle.getVehicleOwnerName());
		assertEquals(VEHICLE_DESCRIPTION,mockVehicle.getVehicleDescription());
		assertEquals(VEHICLE_YEAR,mockVehicle.getVehicleYear());
		assertEquals(VEHICLE_MAKE,mockVehicle.getVehicleMake());
		assertEquals(VEHICLE_MODEL,mockVehicle.getVehicleModel());
		assertEquals(VEHICLE_SCORE,mockVehicle.getVehicleScore());
		assertEquals(mockPhoto, mockVehicle.getPhoto());
	}

	void givenMockVehicleAndPhotoWerePopulated(){
		givenMockVehicleIsPopulated();
		mockPhoto.setPhotoId(PHOTO_ID);
		mockPhoto.setFileName(PHOTO_FILE_NAME);
		mockPhoto.setPath(PHOTO_PATH);
	}

	void givenMockVehicleIsPopulated(){
		mockVehicle.setVehicleSubmissionID(VEHICLE_ID);
		mockVehicle.setVehicleOwnerName(VEHICLE_OWNER_NAME);
		mockVehicle.setVehicleDescription(VEHICLE_DESCRIPTION);
		mockVehicle.setVehicleYear(VEHICLE_YEAR);
		mockVehicle.setVehicleMake(VEHICLE_MAKE);
		mockVehicle.setVehicleModel(VEHICLE_MODEL);
		mockVehicle.setVehicleScore(VEHICLE_SCORE);
		mockVehicle.setPhoto(mockPhoto);
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
	}

	private void whenCompleteVehicleFormIsSubmitted() {
		givenMockVehicleAndPhotoWerePopulated();
	}

	private void thenVehicleCanBeSavedWithConfirmationMessage() throws Exception {
		//TODO adjust method below after views and forms are created
		Vehicle createdVehicle = vehicleService.save(mockVehicle);
		assertEquals(mockVehicle,createdVehicle);
		verify(vehicleDAO, atLeastOnce()).save(mockVehicle);

		//TODO verify confirmation
	}

	/**
	 * Validate that an incomplete Vehicle can not be saved.
	 */
	@Test
	void vehicleIncompleteDataCanNotBeStored() throws Exception {
		givenVehicleDataIsAvailable();
		whenIncompleteVehicleForm();
		thenVehicleIsNotStored();
	}

	private void whenIncompleteVehicleForm() {
		givenMockVehicleIsPopulated();
	}

	private void thenVehicleIsNotStored() throws Exception {
		Vehicle rejectedVehicle = vehicleService.save(mockVehicle);
		assertEquals(mockVehicle,rejectedVehicle);
		//vehicleDAO should not be called
		verify(vehicleDAO, never()).save(mockVehicle);

	}

	/**
	 * Validate that vehicles score is updated when a vote is made.
	 */
	@Test
	void vehicleScoreUpdatedWhenVoted(){
		givenVehicleProfileIsPresent();
		whenVoteIsSubmitted();
		thenVehicleScoreIsUpdated();
	}

	private void givenVehicleProfileIsPresent() {
		//TODO
	}

	private void whenVoteIsSubmitted() {
		//TODO
	}

	private void thenVehicleScoreIsUpdated() {
		//TODO
	}

	/**
	 * Validate that new view with different vehicle are returned when a request to "skipp" a vehicle is made.
	 */
	@Test
	void newVehicleWhenSkipped(){
		givenVehicleProfileIsPresent();
		whenSkipVehicleIsRequested();
		thenNewViewWithDiffrentVehicleIsReturned();
	}

	private void whenSkipVehicleIsRequested() {
		//TODO
	}

	private void thenNewViewWithDiffrentVehicleIsReturned() {
		//TODO
	}
}
