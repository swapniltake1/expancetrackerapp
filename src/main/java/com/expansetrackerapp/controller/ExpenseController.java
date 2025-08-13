package com.expansetrackerapp.controller;

import com.expansetrackerapp.entity.Expense;
import com.expansetrackerapp.dto.ExpenseDTO;
import com.expansetrackerapp.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping("/create")
     public ResponseEntity<Expense> createExpense(
        Authentication authentication, 
        @RequestBody ExpenseDTO dto) {
    String username = authentication.getName();
    Expense expense = expenseService.createExpense(username, dto);
    return ResponseEntity
        .created(URI.create("/api/expenses/" + expense.getId()))
        .body(expense);
}

    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(Authentication authentication, @PathVariable Long id, @RequestBody ExpenseDTO dto) {
        String username = authentication.getName();
        return ResponseEntity.ok(expenseService.updateExpense(username, id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(Authentication authentication, @PathVariable Long id) {
        String username = authentication.getName();
        expenseService.deleteExpense(username, id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Expense>> getExpenses(
            Authentication authentication,
            @RequestParam(required = false) String start,
            @RequestParam(required = false) String end,
            @RequestParam(required = false) String category
    ) {
        String username = authentication.getName();
        LocalDate startDate = (start == null) ? null : LocalDate.parse(start);
        LocalDate endDate = (end == null) ? null : LocalDate.parse(end);
        return ResponseEntity.ok(expenseService.getExpenses(username, startDate, endDate, category));
    }
}
