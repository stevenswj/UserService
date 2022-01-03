package com.userservice.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * This error controller mainly handles 404 errors (bad path)
 *
 * @author Weston Stevens
 */
@RestController
public class IndexController implements ErrorController {
    private static final String PATH = "/error";

    /*
     * The error path to be redirected
     *
     * @return String - The error path
     */
    @Override
    public String getErrorPath() {
        return PATH;
    }

    /*
     * 404 redirects user to error page displaying following error text
     *
     * @return String - The error text
     */
    @RequestMapping(PATH)
    public String error() {
        return "No Mapping available.";
    }
}