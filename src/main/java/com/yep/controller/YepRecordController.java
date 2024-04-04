package com.yep.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class YepRecordController {

    @RequestMapping("/")
    public String hello(){
        return "hello datagrip !!!";
    }
}
