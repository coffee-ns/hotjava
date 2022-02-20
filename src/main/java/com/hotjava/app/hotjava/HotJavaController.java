package com.hotjava.app.hotjava;

import com.hotjava.app.hotjava.service.IVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;



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
     * @return
     */

    @RequestMapping("/")
    public String index() {
        return "start";
    }

    /**
     * Handle the /post endpoint
     * @return
     */

    @RequestMapping("/post")
    public String post() {
        return "post";
    }

    /**
     * Handle the /browse endpoint
     * @return
     */

    @RequestMapping("/browse")
    public String browse() {
        return "browse";
    }

    /**
     * Handle the /search endpoint
     * @return
     */

    @RequestMapping("/search")
    public String search() {
        return "search";
    }

}
