package com.example.tasks.controller;

import com.example.tasks.ObjectOfNullException;
import com.example.tasks.model.NumberSorted;
import com.example.tasks.model.NumberToSort;
import com.example.tasks.service.SortService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/numbers")
@CrossOrigin(origins = "http://localhost:4200")
public class SortController {
    private final SortService sortService;
    @PostMapping(value = "sort-command", produces = MediaType.APPLICATION_JSON_VALUE)
    NumberSorted getNumbers(@RequestBody NumberToSort number) throws ObjectOfNullException {
        return sortService.getNumbersSorted(number);
    }
}
