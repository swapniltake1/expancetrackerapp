package com.expansetrackerapp.repository;

import com.expansetrackerapp.entity.Expense;
import com.expansetrackerapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByUser(User user);
    List<Expense> findByUserAndDateBetweenAndCategory(User user, LocalDate start, LocalDate end, String category);
}
