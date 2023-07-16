package com.example.tasks.repository;

import com.example.tasks.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RateRepository extends JpaRepository<Rate, Long> {
}
