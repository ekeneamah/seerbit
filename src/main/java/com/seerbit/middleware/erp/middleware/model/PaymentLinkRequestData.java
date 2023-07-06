package com.seerbit.middleware.erp.middleware.model;

public class PaymentLinkRequestData {
    private String publickey;
    private String bearertoken;
    private String paymentReference;

    // Getters and setters

    public String getPublickey() {
        return publickey;
    }

    public void setPublickey(String publickey) {
        this.publickey = publickey;
    }

    public String getBearertoken() {
        return bearertoken;
    }

    public void setBearertoken(String bearertoken) {
        this.bearertoken = bearertoken;
    }

    public String getPaymentreference() {
        return paymentReference;
    }
    public void setPaymentReference(String paymentReference) {
        this.paymentReference = paymentReference;
    }
}
