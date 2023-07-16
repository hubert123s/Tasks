package com.example.tasks.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RateOutputDto {
    private String currency;
    private String name;
    private OffsetDateTime date;
    private BigDecimal value;
}
