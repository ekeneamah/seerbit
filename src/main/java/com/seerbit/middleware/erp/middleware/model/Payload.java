package com.seerbit.middleware.erp.middleware.model;
public class Payload {
    private int invoiceID;
    private String invoiceNo;

    public Payload(int invoiceID, String invoiceNo) {
        this.invoiceID = invoiceID;
        this.invoiceNo = invoiceNo;
    }

    public int getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }
}
