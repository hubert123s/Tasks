package com.example.tasks.controller;

import com.example.tasks.service.PingPongService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/status")
public class PingPongController {

    private final PingPongService pingPongService;

    @GetMapping("ping")
    String pingPong(){
        return pingPongService.getPong();
    }
}
