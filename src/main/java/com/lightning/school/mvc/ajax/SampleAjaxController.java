package com.lightning.school.mvc.ajax;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ajax/sample")
public class SampleAjaxController {

    @GetMapping("/hello/")
    public String helloWorld(){
        return "Hello World";
    }

    @GetMapping("/hello/{name}/")
    public String hello(@PathVariable String name){
        return "Hello " + name;
    }

    @GetMapping("/hello/5/")
    public List<String> hello5(){
        List<String> hello = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            hello.add("Hello World"+i);
        }
        return hello;
    }
}
