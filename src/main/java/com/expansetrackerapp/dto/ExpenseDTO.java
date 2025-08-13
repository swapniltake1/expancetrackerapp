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
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
    
    public ExpenseDTO(BigDecimal amount, String category, String description, LocalDate date) {
		this.amount = amount;
		this.category = category;
		this.description = description;
		this.date = date;
	}
}
