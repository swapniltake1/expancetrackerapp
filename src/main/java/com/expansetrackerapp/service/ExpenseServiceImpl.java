package com.expansetrackerapp.service;

import com.expansetrackerapp.dto.ExpenseDTO;
import com.expansetrackerapp.entity.Expense;
import com.expansetrackerapp.entity.User;
import com.expansetrackerapp.repository.ExpenseRepository;
import com.expansetrackerapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    @Override
    public Expense createExpense(String username, ExpenseDTO dto) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        Expense e = Expense.builder()
                .user(user)
                .amount(dto.getAmount())
                .category(dto.getCategory())
                .description(dto.getDescription())
                .date(dto.getDate() == null ? LocalDate.now() : dto.getDate())
                .build();
        return expenseRepository.save(e);
    }

    @Override
    public Expense updateExpense(String username, Long id, ExpenseDTO dto) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        Expense e = expenseRepository.findById(id).orElseThrow(() -> new RuntimeException("Expense not found"));
        if (!e.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Access denied");
        }
        e.setAmount(dto.getAmount());
        e.setCategory(dto.getCategory());
        e.setDescription(dto.getDescription());
        e.setDate(dto.getDate() == null ? e.getDate() : dto.getDate());
        return expenseRepository.save(e);
    }

    @Override
    public void deleteExpense(String username, Long id) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        Expense e = expenseRepository.findById(id).orElseThrow(() -> new RuntimeException("Expense not found"));
        if (!e.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Access denied");
        }
        expenseRepository.delete(e);
    }

    @Override
    public List<Expense> getExpenses(String username, LocalDate start, LocalDate end, String category) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        if (category != null && !category.isBlank()) {
            return expenseRepository.findByUserAndCategory(user, category);
        }
        if (start != null || end != null) {
            return expenseRepository.findByUserAndOptionalDateRange(user, start, end);
        }
        return expenseRepository.findByUser(user);
    }
}
