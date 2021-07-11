package com.ideasoft.practice.angular2boot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/hello_world")
@RestController
public class HelloWorldController {
    @GetMapping
    public String helloWorld(){
        return "hello, world";
    }
}
