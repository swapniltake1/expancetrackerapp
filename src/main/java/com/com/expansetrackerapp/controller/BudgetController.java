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
        Budget budget = budgetService.setOrUpdateBudget(username, dto);
        BudgetDTO responseDto = new BudgetDTO();
        // Map fields from Budget to BudgetDTO as appropriate
        responseDto.setId(budget.getId());
        responseDto.setMonth(budget.getMonth());
        responseDto.setAmount(budget.getAmount());
        // Add other field mappings as needed
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{month}")
    public ResponseEntity<Budget> getBudget(Authentication authentication, @PathVariable String month) {
        String username = authentication.getName();
        return ResponseEntity.ok(budgetService.getBudgetForMonth(username, month));
    }
}