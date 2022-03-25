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
		testVehicle.setSubmissionID(vehicleSubmissionID);
		testVehicle.setOwnerName(vehicleOwnerName);
		testVehicle.setDescription(vehicleDescription);
		testVehicle.setYear(vehicleYear);
		testVehicle.setMake(vehicleMake);
		testVehicle.setModel(vehicleModel);
		testVehicle.setScore(vehicleScore);
		testVehicle.setPhotoID(vehiclePhoto.getPhotoId());
		vehiclePhoto.setPhotoId(111);
		vehiclePhoto.setFileName("civic-type-r.jpg");
		vehiclePhoto.setPath("src/main/resources/img/civic-type-r.jpg");

		assertEquals(vehicleSubmissionID,testVehicle.getSubmissionID());
		assertEquals(vehicleOwnerName,testVehicle.getOwnerName());
		assertEquals(vehicleDescription,testVehicle.getDescription());
		assertEquals(vehicleYear,testVehicle.getYear());
		assertEquals(vehicleMake,testVehicle.getMake());
		assertEquals(vehicleModel,testVehicle.getModel());
		assertEquals(vehicleScore,testVehicle.getScore());
		assertEquals(vehiclePhoto.getPhotoId(), testVehicle.getPhotoID());

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
		
		mockVehicle.setSubmissionID(vehicleSubmissionID);
		mockVehicle.setOwnerName(vehicleOwnerName);
		mockVehicle.setDescription(vehicleDescription);
		mockVehicle.setYear(vehicleYear);
		mockVehicle.setMake(vehicleMake);
		mockVehicle.setModel(vehicleModel);
		mockVehicle.setScore(vehicleScore);
		mockVehicle.setPhotoID(vehiclePhoto.getPhotoId());

		vehicleServiceNoMock.save(mockVehicle);
		mockVehicleList.add(mockVehicle);

		Mockito.when(vehicleDAO.fetchAll()).thenReturn(mockVehicleList);
		List<Vehicle> vehicleList = vehicleService.fetchAll();

		Boolean vehiclePresent = false;
		for (Vehicle v: vehicleList) {
			if(v.getSubmissionID() == vehicleSubmissionID){
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
		mockVehicle.setSubmissionID(8675309);
		mockVehicle.setOwnerName("coffee");
		mockVehicle.setDescription("black");
		mockVehicle.setYear("2001");
		mockVehicle.setMake("Nissan");
		mockVehicle.setModel("Silvia");
		mockVehicle.setScore(0);
		Photo vehiclePhoto = new Photo();
		vehiclePhoto.setPhotoId(111);
		vehiclePhoto.setFileName("civic-type-r.jpg");
		vehiclePhoto.setPath("src/main/resources/img/civic-type-r.jpg");
		mockVehicle.setPhotoID(vehiclePhoto.getPhotoId());
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
		mockVehicle.setScore(0);
		Photo vehiclePhoto = new Photo();
		//photo has no values
		mockVehicle.setPhotoID(vehiclePhoto.getPhotoId());
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
		mockVehicle.setSubmissionID(8675310);
		mockVehicle.setOwnerName("coffee");
		mockVehicle.setDescription("black");
		mockVehicle.setYear("2001");
		mockVehicle.setMake("Nissan");
		mockVehicle.setModel("Silvia");
		mockVehicle.setScore(0);
		Photo vehiclePhoto = new Photo();
		vehiclePhoto.setPhotoId(111);
		vehiclePhoto.setFileName("civic-type-r.jpg");
		vehiclePhoto.setPath("src/main/resources/img/civic-type-r.jpg");
		mockVehicle.setPhotoID(vehiclePhoto.getPhotoId());
		Vehicle createdVehicle = vehicleService.save(mockVehicle);
	}

	private void whenUserVotesUp() throws Exception {
		vehicleService.updateVehicleScore(mockVehicle,true);
	}

	private void whenUserVotesDown() throws Exception {
		vehicleService.updateVehicleScore(mockVehicle,false);
	}

	private void thenVehicleScoreIsIncreasedByOneWithMessage() throws Exception {
		assertTrue(mockVehicle.getScore() > 0 );
		verify(vehicleDAO, atLeast(2)).save(mockVehicle);
	}

	private void thenVehicleScoreIsDecreasedByOneWithMessage() throws Exception {
		assertTrue(mockVehicle.getScore() < 1 );
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
		veh1.setSubmissionID(8675309);
		veh1.setOwnerName("coffee");
		veh1.setDescription("black");
		veh1.setYear("2001");
		veh1.setMake("Nissan");
		veh1.setModel("Silvia");
		veh1.setScore(0);
		Photo vehiclePhoto = new Photo();
		vehiclePhoto.setPhotoId(111);
		vehiclePhoto.setFileName("civic-type-r.jpg");
		vehiclePhoto.setPath("src/main/resources/img/civic-type-r.jpg");
		veh1.setPhotoID(vehiclePhoto.getPhotoId());
		mockVehicleList.add(veh1);

		Vehicle veh2 = new Vehicle();
		veh2.setSubmissionID(8675310);
		veh2.setOwnerName("coffee");
		veh2.setDescription("black");
		veh2.setYear("2001");
		veh2.setMake("Nissan");
		veh2.setModel("Silvia");
		veh2.setScore(0);
		Photo vehiclePhoto2 = new Photo();
		vehiclePhoto2.setPhotoId(111);
		vehiclePhoto2.setFileName("civic-type-r.jpg");
		vehiclePhoto2.setPath("src/main/resources/img/civic-type-r.jpg");
		veh2.setPhotoID(vehiclePhoto.getPhotoId());
		veh2.setSubmissionID(8675310);

		mockVehicleList.add(veh2);
	}

	private void pageRefreshWithNewVehicle() {
		assertNotEquals(mockVehicle, mockVehicleList.get(0));
		verify(vehicleDAO, atLeastOnce()).fetchAll();
	}

	private void whenUserSelectsSkip() {
		mockVehicle = mockVehicleList.get(0);
		Mockito.when(vehicleDAO.fetchAll()).thenReturn(mockVehicleList);
		mockVehicle = vehicleService.fetchDifferentVehicle(mockVehicle.getSubmissionID());
	}


}