package com.expansetrackerapp.controller;

import com.expansetrackerapp.dto.BudgetDTO;
import com.expansetrackerapp.entity.Budget;
import com.expansetrackerapp.service.BudgetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/budgets")
@RequiredArgsConstructor
public class BudgetController {
    private final BudgetService budgetService;

    @PostMapping
    public ResponseEntity<BudgetDTO> setBudget(Authentication authentication, @RequestBody BudgetDTO dto) {
        String username = authentication.getName();
        return ResponseEntity.ok(budgetService.setOrUpdateBudget(username, dto));
    }

    @GetMapping("/{month}")
    public ResponseEntity<Budget> getBudget(Authentication authentication, @PathVariable String month) {
        String username = authentication.getName();
        return ResponseEntity.ok(budgetService.getBudgetForMonth(username, month));
    }
}