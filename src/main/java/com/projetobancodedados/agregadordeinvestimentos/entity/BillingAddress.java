package com.projetobancodedados.agregadordeinvestimentos.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tb_billingAdress")
public class BillingAddress {
    @Id
    @Column (name = "account_id")
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "account_id")
    private Account account;

    @Column (name = "street")
    private String street;
    @Column (name = "number")
    private int number;

    public BillingAddress() {
    }

    public BillingAddress(UUID id, Account account, String street, int number) {
        this.id = id;
        this.account = account;
        this.street = street;
        this.number = number;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
