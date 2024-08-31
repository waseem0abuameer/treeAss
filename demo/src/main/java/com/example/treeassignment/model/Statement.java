package com.example.treeassignment.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * The type Statement.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Statement")
public class Statement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonManagedReference
    private Account account;

    @Column(name = "datefield", updatable = false)
    private LocalDate datefield;

    @Column(name = "amount")
    private BigDecimal amount;




}
