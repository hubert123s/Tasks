package com.example.tasks.controller;

import com.example.tasks.model.GetCurrentCurrencyValueCommand;
import com.example.tasks.exception.NotFoundCurrencyException;
import com.example.tasks.model.RateRequestResponse;
import com.example.tasks.model.GetCurrentCurrencyValueCommandResponse;
import com.example.tasks.service.RateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/currencies")
public class RateController {
    private final RateService rateService;

    @GetMapping("/request")
    List<RateRequestResponse> getRequest() {
        return rateService.findAll();
    }

    @PostMapping("/get-current-currency-value-command")
    GetCurrentCurrencyValueCommandResponse getCurrentCurrencyValue(@RequestBody GetCurrentCurrencyValueCommand getCurrentCurrencyValueCommand) throws NotFoundCurrencyException {
        return rateService.getCurrentCurrencyValue(getCurrentCurrencyValueCommand);
    }
}
