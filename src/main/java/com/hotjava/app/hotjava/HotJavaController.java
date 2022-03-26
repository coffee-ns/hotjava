package com.hotjava.app.hotjava;

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
     * @return add
     */
    @RequestMapping("/add")
    public String add() {
        return "add";
    }

}
