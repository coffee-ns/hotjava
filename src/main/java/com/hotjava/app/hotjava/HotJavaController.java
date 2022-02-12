package com.hotjava.app.hotjava;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class HotJavaController {

    /**
     * Handles / endpoint
     * @return index view
     */
    @RequestMapping("/")
    public String index()
    {
        return "index";
    }
}
