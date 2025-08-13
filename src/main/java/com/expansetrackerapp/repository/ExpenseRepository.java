package com.expansetrackerapp.repository;

import com.expansetrackerapp.entity.Expense;
import com.expansetrackerapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByUser(User user);

    List<Expense> findByUserAndDateBetweenAndCategory(User user, LocalDate start, LocalDate end, String category);

    List<Expense> findByUserAndDateBetween(User user, LocalDate start, LocalDate end);

    List<Expense> findByUserAndCategory(User user, String category);

    List<Expense> findByUserAndDateAfter(User user, LocalDate start);

    List<Expense> findByUserAndDateBefore(User user, LocalDate end);
}
