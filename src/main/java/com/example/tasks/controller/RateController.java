package com.example.tasks.controller;

import com.example.tasks.model.Rate;
import com.example.tasks.exception.NotFoundCurrencyException;
import com.example.tasks.model.RateSelected;
import com.example.tasks.service.RateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/currencies")
public class RateController {
    private final RateService rateService;

    @GetMapping("/request")
    List<Rate> getRequest() {
        return rateService.findAll();
    }

    @PostMapping("/get-current-currency-value-command")
    RateSelected addRate(@RequestBody Rate rate) throws NotFoundCurrencyException, ParseException {
        return rateService.saveRate(rate);
    }
}
