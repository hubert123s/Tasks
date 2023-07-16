package com.example.tasks.mapper;

import com.example.tasks.model.Rate;
import com.example.tasks.model.RateOutputDto;
import com.example.tasks.model.RateSelected;
import org.springframework.stereotype.Service;

@Service
public class RateMapper {
    public RateSelected toDto(Rate rate) {
        RateSelected rateSelected = new RateSelected();
        rateSelected.setValue(rate.getValue());
        return rateSelected;
    }
    public RateOutputDto toDtoWithoutID(Rate rate) {
        RateOutputDto rateOutputDto = new RateOutputDto();
        rateOutputDto.setCurrency(rate.getCurrency());
        rateOutputDto.setName(rate.getName());
        rateOutputDto.setDate(rate.getDate());
        rateOutputDto.setValue(rate.getValue());
        return rateOutputDto;
    }
}
