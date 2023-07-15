package com.example.tasks.mapper;

import com.example.tasks.model.Rate;
import com.example.tasks.model.RateSelected;
import org.springframework.stereotype.Service;

@Service
public class RateMapper {
    public RateSelected toDto(Rate rate){
        RateSelected rateSelected = new RateSelected();
        rateSelected.setValue(rate.getValue());
        return rateSelected;
    }
}
