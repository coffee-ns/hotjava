package com.hotjava.app.hotjava;

import com.hotjava.app.hotjava.dto.Photo;
import com.hotjava.app.hotjava.dto.Vehicle;
import com.hotjava.app.hotjava.service.IVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.http.HttpStatus;
import java.io.IOException;
import java.util.List;


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

    @PostMapping("/addVehicle")
    public ModelAndView addVehicle(Vehicle vehicle, @RequestParam(value="imageFile", required = false)MultipartFile imageFile, Model model) {
        ModelAndView mv = new ModelAndView();
        try {
            vehicleService.save(vehicle);
        } catch (Exception e) {
            e.printStackTrace();
            mv.setViewName("error");
            return mv;
        }

        Photo photo = new Photo();
        try {
            photo.setFileName(imageFile.getOriginalFilename());
            photo.setVehicle(vehicle);
            vehicleService.saveImage(imageFile, photo);
            model.addAttribute("vehicle", vehicle);
            mv.setViewName("addVehicle");
        } catch (IOException e) {
            e.printStackTrace();
            mv.setViewName("error");
            return mv;
        }

        mv.addObject("photo", photo);
        mv.addObject("vehicle",vehicle);
        return mv;
    }


    /**
     * Handle the /search endpoint
     * @return searchVehicle
     */
    @RequestMapping("/search")
    public String search() { return "searchVehicle"; }

    /**
     * Handle the /searchVehicle endpoint
     * @return searchVehicle
     */
    @GetMapping("/searchVehicle")
    public ModelAndView search(@RequestParam(value="searchTerm", required=false, defaultValue="None")  String searchTerm ) {
        ModelAndView mv = new ModelAndView();
        try {
        List<Vehicle> vehicleList = vehicleService.fetchVehiclesByMake(searchTerm);
        mv.addObject("vehicles", vehicleList);
    } catch (Exception e) {
        e.printStackTrace();
        mv.setViewName("error");
        return mv;
    }
        mv.setViewName("searchVehicle");
     return mv;
    }


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

    @PostMapping("/voteUpdate")
    public String vote(Vehicle vehicle, boolean upvote) { try {
        vehicleService.updateVehicleScore(vehicle,upvote);
    } catch (Exception e) {
        e.printStackTrace();
        return "error";
    }
        return "vote"; }

    /**
     * Handle the /addVehicle endpoint
     * @return addVehicle
     */



    /**
     * Handle the deleting of a vehicle
     * @returns respective http stats response entity
     */
    @DeleteMapping("/vehicle/{id}/")
    public ResponseEntity deleteVehicle(@PathVariable("id") int id) {
        try {
            vehicleService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Handle the retrieval of all vehicles
     */
    @GetMapping("/vehicle")
    @ResponseBody
    public List<Vehicle> fetchAllVehicles() {
        return vehicleService.fetchAll();
    }

    /**
     * Fetch a vehicle with the given ID.
     *
     * Given the parameter id, find a vehicle that corresponds to this unique ID.
     *
     * Returns one of the following status codes:
     * 200: vehicle found
     * 400: vehicle not found
     *
     * @param id a unique identifier for this vehicle
     */
    @GetMapping("/vehicle/{id}/")
    public ResponseEntity fetchVehicleById(@PathVariable("id") int id) {
        try {
            Vehicle foundVehicle = vehicleService.fetchById(id);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity(foundVehicle, headers, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
