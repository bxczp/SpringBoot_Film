package com.bxczp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
    
    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title","首頁");
        modelAndView.addObject("mainPage", "film/indexFilm");
        modelAndView.addObject("mainPageKey", "#f");
        modelAndView.setViewName("index");
        return modelAndView;
    }
    
    
    @RequestMapping("/login")
    public String Login() {
        return "/login";
    }
    
    @RequestMapping("/admin")
    public String toAdmin() {
        return "/admin/main";
    }

}
