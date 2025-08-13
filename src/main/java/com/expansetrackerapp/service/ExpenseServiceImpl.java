package com.expansetrackerapp.service;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expansetrackerapp.dto.ExpenseDTO;
import com.expansetrackerapp.entity.Expense;
import com.expansetrackerapp.repository.ExpenseRepository;
import com.expansetrackerapp.repository.UserRepository;
import com.expansetrackerapp.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

	@Override
	public Expense createExpense(String username, ExpenseDTO dto) {
		User user = userRepository.findByUsername(username)
			.orElseThrow(() -> new RuntimeException("User not found"));
		Expense expense = Expense.builder()
				.user(user)
				.amount(dto.getAmount())
				.category(dto.getCategory())
				.date(dto.getDate())
				.description(dto.getDescription())
				.build();
		return expenseRepository.save(expense);
	}

	@Override
	public Expense updateExpense(String username, Long id, ExpenseDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteExpense(String username, Long id) {
		// TODO Auto-generated method stub
		
	}

public List<Expense> getExpenses(String username, LocalDate start, LocalDate end, String category) {
    User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found"));

    // If no filters are applied, return all expenses for the user
    if (start == null && end == null && (category == null || category.isEmpty())) {
        return expenseRepository.findByUser(user);
    }

    // Apply filters dynamically
    if (start != null && end != null && category != null && !category.isEmpty()) {
        return expenseRepository.findByUserAndDateBetweenAndCategory(user, start, end, category);
    } else if (start != null && end != null) {
        return expenseRepository.findByUserAndDateBetween(user, start, end);
    } else if (category != null && !category.isEmpty()) {
        return expenseRepository.findByUserAndCategory(user, category);
    } else if (start != null) {
        return expenseRepository.findByUserAndDateAfter(user, start);
    } else if (end != null) {
        return expenseRepository.findByUserAndDateBefore(user, end);
    }

    // Fallback
    return expenseRepository.findByUser(user);
}


}
