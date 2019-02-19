package com.lightning.school.mvc.pages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping
public class HomePage {

    @GetMapping("/")
    public String handleRequest(ModelMap model){
        model.addAttribute("title", "TOTO");
        return "index";
    }

}
