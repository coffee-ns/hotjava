package com.hotjava.app.hotjava;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;



/**
 * TODO
 * Create a controller for HotJava REST endpoints and web UI
 * Create a class handles the CRUD operations for HotJava Vehicles, via HTTP actions.
 * which class also serves HTML based web pages, for UI interactions with vehicles.
 *
 */
@Controller
public class HotJavaController {

    /**
     * Handle the / endpoint
     * @return
     */
    @RequestMapping("/")
    public String index() {
        return "index";
    }


}
