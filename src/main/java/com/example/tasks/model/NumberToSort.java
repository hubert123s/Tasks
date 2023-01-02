package com.example.tasks.model;

import com.example.tasks.OrderType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NumberToSort {
    private List<Integer> numbers;
    @NotNull
    private OrderType order;

}
