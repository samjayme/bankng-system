package com.samueljavaspringboot.bankng_system.entity;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Account {

    @Id
    @Column(nullable = false, updatable = false)

    @GeneratedValue
     Long id;

    @Column
    private String accountName;

    @Column
    private String accountNumber;

    @Column
    private String accountType;

    @Column(precision = 10, scale = 2)
    private BigDecimal balance = BigDecimal.ZERO;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AccountCurrency> currencies = new LinkedHashSet<>();

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Transaction> transactions = new LinkedHashSet<>();

}
