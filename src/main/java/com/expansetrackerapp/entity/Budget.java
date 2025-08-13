package com.expansetrackerapp.entity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Builder
@Entity
@Table(name = "budgets", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "month"})})
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // month format: YYYY-MM (e.g., 2025-08)
    @Column(nullable = false)
    private String month;

    @Column(nullable = false)
    private BigDecimal limitAmount;
    
    


public Budget() {
		super();
		// TODO Auto-generated constructor stub
	}




public Budget(Long id, User user, String month, BigDecimal limitAmount) {
		super();
		this.id = id;
		this.user = user;
		this.month = month;
		this.limitAmount = limitAmount;
	}




public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public User getUser() {
		return user;
	}




	public void setUser(User user) {
		this.user = user;
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




public BigDecimal getAmount() {
    return this.limitAmount;
}
}