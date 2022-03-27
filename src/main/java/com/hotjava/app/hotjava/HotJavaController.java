package com.hotjava.app.hotjava;


import com.hotjava.app.hotjava.dto.Photo;
import com.hotjava.app.hotjava.service.IVehicleService;
import com.hotjava.app.hotjava.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.http.HttpStatus;


/**
 * The controller for HotJava REST endpoints and web UI
 * <p>
 * This class handles the CRUD operations for HotJava Vehicles, via HTTP actions.
 * </p>
 * <p>
 * This class also serves HTML based web pages, for UI interactions with vehicles.
 * </p>
 */
@Controller
public class HotJavaController {

    @Autowired
    IVehicleService vehicleService;

    /**
     * Handle the / endpoint
     * @return index
     */
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    /**
     * Handle the /add endpoint
     * @return addVehicle
     */
    @RequestMapping("/add")
    public String add() { return "addVehicle"; }

    /**
     * Handle the /search endpoint
     * @return searchVehicle
     */
    @RequestMapping("/search")
    public String search() { return "searchVehicle"; }

    /**
     * Handle the /vote endpoint
     * @return vote
     */
    @RequestMapping("/vote")
    public String vote() { return "vote"; }

    /**
     * Handles the /vehicleUpload endpoint
     * Send vehicle data
     * @param imageFile
     * @param photo
     * @return
     */

    @PostMapping("/vehicleUpload")
    public String vehicleUpload(@RequestParam("imageFile") MultipartFile imageFile, Photo photo) throws IOException {
    String returnValue = "start";
    vehicleService.saveImage(imageFile, photo);
    return returnValue;
    }

}
