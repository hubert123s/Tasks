package com.example.tasks;

import com.example.tasks.model.NumberSorted;
import com.example.tasks.model.NumberToSort;
import com.example.tasks.service.SortService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class SortServiceTest {
    @Test
    void shouldSortAscending() throws ObjectOfNullException {
        SortService sortService = new SortService();
        List<Integer> numbers = List.of(4, 1, 6, 5, 10);
        List<Integer> expectedNumbers = List.of(1, 4, 5, 6, 10);
        NumberToSort number = new NumberToSort(numbers, OrderType.ASC);
        NumberSorted numberSorted = new NumberSorted(expectedNumbers);
        assertThat(sortService.getNumbersSorted(number).getNumbers()).isEqualTo(numberSorted.getNumbers());
    }

    @Test
    void shouldSortDescending() throws ObjectOfNullException {
        SortService sortService = new SortService();
        List<Integer> numbers = List.of(4, 1, 6, 5, 10);
        List<Integer> expectedNumbers = List.of(10, 6, 5, 4, 1);
        NumberToSort number = new NumberToSort(numbers, OrderType.DESC);
        NumberSorted numberSorted = new NumberSorted(expectedNumbers);
        assertThat(sortService.getNumbersSorted(number).getNumbers()).isEqualTo(numberSorted.getNumbers());

    }

    @Test
    void shouldThrowExceptionIfNumberIsNull() {
        SortService sortService = new SortService();
        NumberToSort number = new NumberToSort(null, OrderType.DESC);
        assertThatThrownBy(() -> sortService.getNumbersSorted(number)).isExactlyInstanceOf(ObjectOfNullException.class);

    }
}
