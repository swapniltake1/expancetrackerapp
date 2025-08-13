package com.expansetrackerapp.service;

import com.expansetrackerapp.entity.Expense;
import com.expansetrackerapp.dto.ExpenseDTO;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseService {
    Expense createExpense(String username, ExpenseDTO dto);
    Expense updateExpense(String username, Long id, ExpenseDTO dto);
    void deleteExpense(String username, Long id);
    List<Expense> getExpenses(String username, LocalDate start, LocalDate end, String category);
}
