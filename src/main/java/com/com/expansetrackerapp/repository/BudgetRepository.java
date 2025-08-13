package com.expansetrackerapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BudgetRepository extends JpaRepository<com.expansetrackerapp.entity.Budget, Long> {
    Optional<com.expansetrackerapp.entity.Budget> findByUserAndMonth(com.expansetrackerapp.entity.User user, String month);
}