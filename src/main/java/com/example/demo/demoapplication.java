package com.example.demo;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.web.bind.annotation.*;

@SpringBootApplication

@RestController

public class demoapplication {

    @GetMapping("/")

    public String home() {

        return "Deployed by using Jenkins + Argo CD GitOps";

    }

    public static void main(String[] args) {

        SpringApplication.run(demoapplication.class, args);

    }

}
 