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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class HotJavaApplicationTests {

	private IVehicleService vehicleService;
	private Vehicle mockVehicle = new Vehicle();
	private List<Vehicle> mockVehicleList = new ArrayList<>();

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
		 int vehicleScore = 0;
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
		givenVehicleDataIsAvailable();
		int vehicleSubmissionID = 8675309;
		String vehicleOwnerName = "test name";
		String vehicleDescription ="test desc";
		String vehicleYear = "1999";
		String vehicleMake = "test make";
		String vehicleModel = "test model";
		int vehicleScore = 0;
		Photo vehiclePhoto = new Photo();
		vehiclePhoto.setPhotoId(111);
		vehiclePhoto.setFileName("civic-type-r.jpg");
		vehiclePhoto.setPath("src/main/resources/img/civic-type-r.jpg");
		
		mockVehicle.setVehicleSubmissionID(vehicleSubmissionID);
		mockVehicle.setVehicleOwnerName(vehicleOwnerName);
		mockVehicle.setVehicleDescription(vehicleDescription);
		mockVehicle.setVehicleYear(vehicleYear);
		mockVehicle.setVehicleMake(vehicleMake);
		mockVehicle.setVehicleModel(vehicleModel);
		mockVehicle.setVehicleScore(vehicleScore);
		mockVehicle.setPhoto(vehiclePhoto);

		vehicleServiceNoMock.save(mockVehicle);
		mockVehicleList.add(mockVehicle);

		Mockito.when(vehicleDAO.fetchAll()).thenReturn(mockVehicleList);
		List<Vehicle> vehicleList = vehicleService.fetchAll();

		Boolean vehiclePresent = false;
		for (Vehicle v: vehicleList) {
			if(v.getVehicleSubmissionID() == vehicleSubmissionID){
				if(v == mockVehicle){
					vehiclePresent = true;
				}
			}
		}
        assertTrue(vehiclePresent);
		verify(vehicleDAO, atLeastOnce()).save(mockVehicle);
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
		mockVehicle.setVehicleScore(0);
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
		mockVehicle.setVehicleScore(0);
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
	void vehicleScoreUpdatedWhenVoted() throws Exception {
		givenVehicleDataIsAvailable();
		givenVehicleProfileIsSaved();
		whenUserVotesUp();
		thenVehicleScoreIsIncreasedByOneWithMessage();
		whenUserVotesDown();
		thenVehicleScoreIsDecreasedByOneWithMessage();
	}

	private void givenVehicleProfileIsSaved() throws Exception {
		mockVehicle.setVehicleSubmissionID(8675310);
		mockVehicle.setVehicleOwnerName("coffee");
		mockVehicle.setVehicleDescription("black");
		mockVehicle.setVehicleYear("2001");
		mockVehicle.setVehicleMake("Nissan");
		mockVehicle.setVehicleModel("Silvia");
		mockVehicle.setVehicleScore(0);
		Photo vehiclePhoto = new Photo();
		vehiclePhoto.setPhotoId(111);
		vehiclePhoto.setFileName("civic-type-r.jpg");
		vehiclePhoto.setPath("src/main/resources/img/civic-type-r.jpg");
		mockVehicle.setPhoto(vehiclePhoto);
		Vehicle createdVehicle = vehicleService.save(mockVehicle);
	}

	private void whenUserVotesUp() throws Exception {
		vehicleService.updateVehicleScore(mockVehicle,true);
	}

	private void whenUserVotesDown() throws Exception {
		vehicleService.updateVehicleScore(mockVehicle,false);
	}

	private void thenVehicleScoreIsIncreasedByOneWithMessage() throws Exception {
		assertTrue(mockVehicle.getVehicleScore() > 0 );
		verify(vehicleDAO, atLeast(2)).save(mockVehicle);
	}

	private void thenVehicleScoreIsDecreasedByOneWithMessage() throws Exception {
		assertTrue(mockVehicle.getVehicleScore() < 1 );
		verify(vehicleDAO, atLeast(3)).save(mockVehicle);
	}


	/**
	 * Validate that page refreshes with new vehicle when vehicle is "skipped".
	 */
	@Test
	void newVehicleWhenSkipped() throws Exception {
		givenVehicleDataIsAvailable();
		givenMultipleVehiclesAvailable();
		whenUserSelectsSkip();
		pageRefreshWithNewVehicle();
	}

	private void givenMultipleVehiclesAvailable() throws Exception {
		Vehicle veh1 = new Vehicle();
		veh1.setVehicleSubmissionID(8675309);
		veh1.setVehicleOwnerName("coffee");
		veh1.setVehicleDescription("black");
		veh1.setVehicleYear("2001");
		veh1.setVehicleMake("Nissan");
		veh1.setVehicleModel("Silvia");
		veh1.setVehicleScore(0);
		Photo vehiclePhoto = new Photo();
		vehiclePhoto.setPhotoId(111);
		vehiclePhoto.setFileName("civic-type-r.jpg");
		vehiclePhoto.setPath("src/main/resources/img/civic-type-r.jpg");
		veh1.setPhoto(vehiclePhoto);
		mockVehicleList.add(veh1);

		Vehicle veh2 = new Vehicle();
		veh2.setVehicleSubmissionID(8675310);
		veh2.setVehicleOwnerName("coffee");
		veh2.setVehicleDescription("black");
		veh2.setVehicleYear("2001");
		veh2.setVehicleMake("Nissan");
		veh2.setVehicleModel("Silvia");
		veh2.setVehicleScore(0);
		Photo vehiclePhoto2 = new Photo();
		vehiclePhoto2.setPhotoId(111);
		vehiclePhoto2.setFileName("civic-type-r.jpg");
		vehiclePhoto2.setPath("src/main/resources/img/civic-type-r.jpg");
		veh2.setPhoto(vehiclePhoto);
		veh2.setVehicleSubmissionID(8675310);

		mockVehicleList.add(veh2);
	}

	private void pageRefreshWithNewVehicle() {
		assertNotEquals(mockVehicle, mockVehicleList.get(0));
		verify(vehicleDAO, atLeastOnce()).fetchAll();
	}

	private void whenUserSelectsSkip() {
		mockVehicle = mockVehicleList.get(0);
		Mockito.when(vehicleDAO.fetchAll()).thenReturn(mockVehicleList);
		mockVehicle = vehicleService.fetchDifferentVehicle(mockVehicle.getVehicleSubmissionID());
	}


}
