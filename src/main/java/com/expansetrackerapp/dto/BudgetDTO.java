package com.expansetrackerapp.dto;

import lombok.Data;
import java.math.BigDecimal;

public class BudgetDTO {
    private Long id;
    // month in format YYYY-MM
    private String month;
    private BigDecimal limitAmount;
    
    
    
    public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getMonth() {
		return month;
	}



	public void setMonth(String month) {
		this.month = month;
	}



	public BigDecimal getLimitAmount() {
		return limitAmount;
	}



	public void setLimitAmount(BigDecimal limitAmount) {
		this.limitAmount = limitAmount;
	}



	public BudgetDTO() {
		super();
		// Default constructor
	}
}
