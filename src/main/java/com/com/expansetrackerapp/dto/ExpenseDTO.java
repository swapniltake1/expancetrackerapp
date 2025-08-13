package com.expansetrackerapp.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ExpenseDTO {
    private BigDecimal amount;
    private String category;
    private String description;
    private LocalDate date;
}
