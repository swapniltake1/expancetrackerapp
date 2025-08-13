package com.expansetrackerapp.service;

import com.expansetrackerapp.dto.*;
import com.expansetrackerapp.entity.*;


public interface BudgetService {
    Budget setOrUpdateBudget(String username, BudgetDTO dto);
    Budget getBudgetForMonth(String username, String month);
}
