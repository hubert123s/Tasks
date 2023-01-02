package com.example.tasks.service;

import com.example.tasks.ObjectOfNullException;
import com.example.tasks.OrderType;
import com.example.tasks.model.NumberSorted;
import com.example.tasks.model.NumberToSort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
public class SortService {
    private List<Integer> sorting(NumberToSort number) throws ObjectOfNullException {
        if (Objects.isNull(number.getNumbers())) {
            throw new ObjectOfNullException("No numbers to sort");
        }
        return number.getNumbers().stream().sorted(getDirectionSorting(number.getOrder())).toList();
    }

    private Comparator<Integer> getDirectionSorting(OrderType orderType) {
        return orderType == OrderType.ASC ? Comparator.naturalOrder() : Comparator.reverseOrder();
    }

    public NumberSorted getNumbersSorted(NumberToSort number) throws ObjectOfNullException {
        return new NumberSorted(sorting(number));
    }
}
