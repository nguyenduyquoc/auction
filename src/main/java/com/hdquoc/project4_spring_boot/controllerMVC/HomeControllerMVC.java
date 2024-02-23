package com.hdquoc.project4_spring_boot.controllerMVC;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("")
public class HomeControllerMVC {
    @GetMapping("/")
    public String homePage(){
        return "index";
    }

}