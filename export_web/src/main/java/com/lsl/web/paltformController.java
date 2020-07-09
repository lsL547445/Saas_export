package com.lsl.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class paltformController {
    @RequestMapping("/login")
    public String index(){

        return "home/main";
    }
}
