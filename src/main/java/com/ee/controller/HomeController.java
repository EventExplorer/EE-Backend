package com.ee.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.modeler.BaseAttributeFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;


@Controller
public class HomeController{


    @GetMapping("/test")
    public String home(Model model) {
        // Assuming you have a React component named "App"
        String reactApp = "<App />";
        model.addAttribute("reactApp", reactApp);
        return "v4";
    }
    @GetMapping(value = {"/", "/**/{path:[^\\.]*}"})
    public String forward() {
        return "forward:/index.html";
    }

}