package com.example.tasks.model;



import java.math.BigDecimal;
import java.time.OffsetDateTime;


public record RateRequestResponse(String currency, String name, OffsetDateTime date, BigDecimal value) {
}

