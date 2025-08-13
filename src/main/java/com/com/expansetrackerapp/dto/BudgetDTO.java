package com.expansetrackerapp.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BudgetDTO {
    // month in format YYYY-MM
    private String month;
    private BigDecimal limitAmount;
}
