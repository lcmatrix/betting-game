/*
 * Created on 30.03.2017
 * 
 * Copyright(c) 1995 - 2017 T-Systems Multimedia Solutions GmbH
 * Riesaer Str. 5, 01129 Dresden
 * All rights reserved.
 */

package de.bettinggame.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for index/startpage.
 */
@Controller
public class MainController extends AbstractController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
