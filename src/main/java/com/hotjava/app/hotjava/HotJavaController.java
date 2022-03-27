package com.hotjava.app.hotjava;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
