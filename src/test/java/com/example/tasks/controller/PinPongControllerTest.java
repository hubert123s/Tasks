package com.example.tasks.controller;

import com.example.tasks.service.PingPongService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class PinPongControllerTest {
    @Mock
    PingPongService pingPongService;
    @Autowired
    MockMvc mockMvc;
    @BeforeEach
    void init() {
        PingPongController pingPongController = new PingPongController(pingPongService);
        mockMvc = MockMvcBuilders.standaloneSetup(pingPongController).build();
    }

    @Test
    void shouldReturnPong() throws Exception {
        Mockito.when(pingPongService.getPong()).thenReturn("pong");
        String expectedValue = "pong";
        mockMvc.perform(get("/status/ping"))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(jsonPath("$").value(expectedValue));
    }
}
