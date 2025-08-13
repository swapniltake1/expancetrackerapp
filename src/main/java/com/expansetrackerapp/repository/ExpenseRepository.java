package com.expansetrackerapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.expansetrackerapp.entity.Expense;
import com.expansetrackerapp.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<com.expansetrackerapp.entity.Expense> findByUser(com.expansetrackerapp.entity.User user);

    List<Expense> findByUserAndDateBetween(com.expansetrackerapp.entity.User user, LocalDate start, LocalDate end);

    List<Expense> findByUserAndCategory(com.expansetrackerapp.entity.User user, String category);

    @Query("SELECT e FROM Expense e WHERE e.user = :user AND (:start IS NULL OR e.date >= :start) AND (:end IS NULL OR e.date <= :end)")
    List<Expense> findByUserAndOptionalDateRange(@Param("user") User user, @Param("start") LocalDate start, @Param("end") LocalDate end);
}