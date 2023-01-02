package com.example.tasks.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class PingPongServiceTest {
    @Test
    void shouldReturnPong(){
        PingPongService pingPongService = new PingPongService();
        assertThat(pingPongService.getPong()).isEqualTo("pong");
    }
}
