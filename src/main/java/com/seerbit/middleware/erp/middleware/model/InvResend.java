package com.seerbit.middleware.erp.middleware.model;

public class InvResend {

    private String publicKey;
    private String token;
    private String invoiceno;
    private String orderno;
    private String customerEmail;


    public InvResend(){}
    // Getter for publicKey
    public String getPublicKey() {
        return publicKey;
    }

    // Setter for publicKey
    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    // Getter for token
    public String getToken() {
        return token;
    }

    // Setter for token
    public void setToken(String token) {
        this.token = token;
    }

    // Getter for invoiceno
    public String getInvoiceno() {
        return invoiceno;
    }

    // Setter for invoiceno
    public void setInvoiceno(String invoiceno) {
        this.invoiceno = invoiceno;
    }

     // Setter for orderno
     public void setOrderNo(String orderno) {
        this.orderno = orderno;
    }
    public String getOrderNo() {
        return orderno;
    }
    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
}
