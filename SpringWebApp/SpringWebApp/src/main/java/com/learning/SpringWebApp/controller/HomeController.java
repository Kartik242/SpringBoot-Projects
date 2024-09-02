package com.learning.SpringWebApp.controller;

import com.learning.SpringWebApp.Alien;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home() {
        return "index";
    }

    @RequestMapping("add")
    public ModelAndView add(@RequestParam("num1") int num1, @RequestParam("num2") int num2, ModelAndView mv) {

        int result = num1 + num2;
        mv.addObject("result", result);
        mv.setViewName("result");
        return mv;
    }

//    @RequestMapping("addAlien")
//    public ModelAndView addAlien(@RequestParam("aid") int id, @RequestParam("aname") String name, ModelAndView mv) {
//
//        Alien alien = new Alien();
//        alien.setAid(id);
//        alien.setAname(name);
//        mv.addObject("alien",alien);
//        mv.setViewName("result");
//        return mv;
//    }


    @RequestMapping("addAlien")
    public String addAlien(@ModelAttribute Alien alien) {
        return "result";
    }

    @ModelAttribute("course")
    public String getCourse(){
        return "Java";
    }
}
