package com.kartikeye.simplewebapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String greet() {
        return "Welcome!!!";
    }

    @GetMapping("info")
    public String aboutMachine() {
        String os = System.getProperty("os.name");
        String version = System.getProperty("os.version");
        String javaVersion = System.getProperty("java.version");

        return "OS : " + os + " Version : " + version + " javaVersion : " + javaVersion;
    }
}
