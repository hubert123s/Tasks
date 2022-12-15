package com.example.tasks.service;

import org.springframework.stereotype.Service;

@Service
public class PingPongService {

    private static final String PONG ="pong";
    public String getPong(){
        return PONG;
    }

}
