package com.seerbit.middleware.erp.middleware.model;


public class PaymentLink {
    private String status;
    
 
    private String paymentLinkName;

    private String Token;
    private String someSt;
  
    
    private String description;
    private String currency;
    
  
    private String publicKey;
    
    private String paymentFrequency;
    
  
    private String paymentReference;
    
    private boolean linkExpirable;
    
  
    private String expiryDate;
    
    private boolean oneTime;
    
   
    private String customizationName;
    
    private String email;
    private Float amount;
    
   
    private RequiredFields requiredFields;

    // Getters and setters for each field

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAmount() {
        return String.format("%.2f", amount);
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String Token) {
        this.Token = Token;
    }

    public String getSomeSt() {
        return someSt;
    }

    public void setSomeSt(String someSt) {
        this.someSt = someSt;
    }

  

    public String getPaymentLinkName() {
        return paymentLinkName;
    }

    public void setPaymentLinkName(String paymentLinkName) {
        this.paymentLinkName = paymentLinkName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPaymentFrequency() {
        return paymentFrequency;
    }

    public void setPaymentFrequency(String paymentFrequency) {
        this.paymentFrequency = paymentFrequency;
    }

    public String getPaymentReference() {
        return paymentReference;
    }

    public void setPaymentReference(String paymentReference) {
        this.paymentReference = paymentReference;
    }

    public boolean isLinkExpirable() {
        return linkExpirable;
    }

    public void setLinkExpirable(boolean linkExpirable) {
        this.linkExpirable = linkExpirable;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public boolean isOneTime() {
        return oneTime;
    }

    public void setOneTime(boolean oneTime) {
        this.oneTime = oneTime;
    }

    public String getCustomizationName() {
        return customizationName;
    }

    public void setCustomizationName(String customizationName) {
        this.customizationName = customizationName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RequiredFields getRequiredFields() {
        return requiredFields;
    }

    public void setRequiredFields(RequiredFields requiredFields) {
        this.requiredFields = requiredFields;
    }

    // Inner class for RequiredFields
    public static class RequiredFields {
        private boolean address;
        private Boolean amount;
        
        private boolean customerName;
        
        private boolean mobileNumber;
        
        private boolean invoiceNumber;

        // Getters and setters for each field
                    // Getter for customerName
                    public boolean isCustomerName() {
                        return customerName;
                    }

                    // Setter for customerName
                    public void setCustomerName(Boolean customerName) {
                        this.customerName = customerName;
                    }

                    // Getter for mobileNumber
                    public boolean isMobileNumber() {
                        return mobileNumber;
                    }

                    // Setter for mobileNumber
                    public void setMobileNumber(boolean mobileNumber) {
                        this.mobileNumber = mobileNumber;
                    }

                    // Getter for invoiceNumber
                    public boolean isInvoiceNumber() {
                        return invoiceNumber;
                    }

                    // Setter for invoiceNumber
                    public void setInvoiceNumber(boolean invoiceNumber) {
                        this.invoiceNumber = invoiceNumber;
                    }
        public boolean isAddress() {
            return address;
        }

        public void setAddress(boolean address) {
            this.address = address;
        }

        public boolean isAmount() {
            return amount;
        }

        public void setAmount(Boolean amount) {
            this.amount = amount;
        }

    }
}