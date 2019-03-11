package com.mazvile.menuappweb.controllers;

import com.mazvile.menuappweb.dao.HelloDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private HelloDAO helloDAO;

    @RequestMapping("/hello")
    public String hello() {
        return helloDAO.getName();
    }
}
