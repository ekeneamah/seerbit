package com.seerbit.middleware.erp.middleware.model;
public class InvoiceItems {
private String itemName;
private int quantity;
private double rate;
private double tax;

public String getItemName() {
    return itemName;
}

public void setItemName(String itemName) {
    this.itemName = itemName;
}

public int getQuantity() {
    return quantity;
}

public void setQuantity(int quantity) {
    this.quantity = quantity;
}

public double getRate() {
    return rate;
}

public void setRate(double rate) {
    this.rate = rate;
}

public double getTax() {
    return tax;
}

public void setTax(double tax) {
    this.tax = tax;
}
}
