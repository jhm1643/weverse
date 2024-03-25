package com.weverse.shop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weverse/shop")
public class TestController {

    @GetMapping("/test")
    public ResponseEntity<Void> test(){
        return ResponseEntity.ok().build();
    }
}
