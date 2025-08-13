package com.expansetrackerapp.service;

import com.expansetrackerapp.dto.BudgetDTO;
import com.expansetrackerapp.entity.Budget;
import com.expansetrackerapp.entity.User;
import com.expansetrackerapp.repository.BudgetRepository;
import com.expansetrackerapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BudgetServiceImpl implements BudgetService {
    private final BudgetRepository budgetRepository;
    private final UserRepository userRepository;

    @Override
    public Budget setOrUpdateBudget(String username, BudgetDTO budgetDTO) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        var optional = budgetRepository.findByUserAndMonth(user, budgetDTO.getMonth());
        Budget b = optional.orElse(Budget.builder().user(user).month(budgetDTO.getMonth()).build());
        b.setLimitAmount(budgetDTO.getLimitAmount());
        return budgetRepository.save(b);
    }

    @Override
    public Budget getBudgetForMonth(String username, String month) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        return budgetRepository.findByUserAndMonth(user, month).orElseThrow(() -> new RuntimeException("Budget not found for month"));
    }


}
