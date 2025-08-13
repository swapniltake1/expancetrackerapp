package com.expansetrackerapp.entity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "budgets", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "month"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
}