package com.seerbit.middleware.erp.middleware.model;

import com.google.api.client.util.DateTime;

public class POSTransaction {
    private String id;
    private String posid;
    private String merchantid;
    private String transactionValue;
    private String status;
    private String merchatTerminalId;
    private String transactionRef;
    private String senTime;
    private String receivDateTime;
    private String erpTransactionRef;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
 // Getter and Setter methods for 'posid'
 public String getPosid() {
    return posid;
}

public void setPosid(String posid) {
    this.posid = posid;
}

// Getter and Setter methods for 'merchantid'
public String getMerchantid() {
    return merchantid;
}

public void setMerchantid(String merchantid) {
    this.merchantid = merchantid;
}

// Getter and Setter methods for 'erpTransactionRef'
public String getErpTransactionRef() {
    return erpTransactionRef;
}

public void setErpTransactionRef(String erpTransactionRef) {
    this.erpTransactionRef = erpTransactionRef;
}

// Getter and Setter methods for 'transactionValue'
public String getTransactionValue() {
    return transactionValue;
}

public void setTransactionValue(String transactionValue) {
    this.transactionValue = transactionValue;
}

// Getter and Setter methods for 'status'
public String getStatus() {
    return status;
}

public void setStatus(String status) {
    this.status = status;
}

// Getter and Setter methods for 'merchatTerminalId'
public String getMerchatTerminalId() {
    return merchatTerminalId;
}

public void setMerchatTerminalId(String merchatTerminalId) {
    this.merchatTerminalId = merchatTerminalId;
}

// Getter and Setter methods for 'transactionRef'
public String getTransactionRef() {
    return transactionRef;
}

public void setTransactionRef(String transactionRef) {
    this.transactionRef = transactionRef;
}

// Getter and Setter methods for 'senTime'
public String getSenTime() {
    return senTime;
}

public void setSenTime(String senTime) {
    this.senTime = senTime;
}

// Getter and Setter methods for 'receivDateTime'
public String getReceivDateTime() {
    return receivDateTime;
}

public void setReceivDateTime(String receivDateTime) {
    this.receivDateTime = receivDateTime;
}
    
}

