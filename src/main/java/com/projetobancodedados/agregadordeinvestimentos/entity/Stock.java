package com.projetobancodedados.agregadordeinvestimentos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_stocks")
public class Stock {
    @Id
    @Column(name = "stock_id")
    private String stockId;
    @Column(name = "description")
    private String description;
    @Column(name = "value")
    private double value;
    public Stock(){

    }

    public Stock(String stockId, String description, double value) {
        this.stockId = stockId;
        this.description = description;
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
