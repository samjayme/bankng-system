package com.samueljavaspringboot.bankng_system.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
public class AccountCurrency {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String currency;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AccountCurrency accountCurrency = (AccountCurrency) o;
        return id != null && Objects.equals(id, accountCurrency.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
