package com.seerbit.middleware.erp.middleware.model;

public class PaymentLinkPayload {
    private String paymentLinkId;
    private String status;
    private String description;
    private String successMessage;
    private String businessName;
    private String publicKey;
    private String customizationName;
    private String paymentFrequency;
    private String email;
    private RequiredFields requiredFields;
    private boolean linkExpirable;
    private String expiryDate;
    private boolean oneTime;

    public String getPaymentLinkId() {
        return paymentLinkId;
    }

    public void setPaymentLinkId(String paymentLinkId) {
        this.paymentLinkId = paymentLinkId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getCustomizationName() {
        return customizationName;
    }

    public void setCustomizationName(String customizationName) {
        this.customizationName = customizationName;
    }

    public String getPaymentFrequency() {
        return paymentFrequency;
    }

    public void setPaymentFrequency(String paymentFrequency) {
        this.paymentFrequency = paymentFrequency;
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

    // Inner class representing the 'requiredFields' structure
    public static class RequiredFields {
        private boolean address;
        private boolean amount;
        private boolean customerName;
        private boolean mobileNumber;
        private boolean invoiceNumber;

        public boolean isAddress() {
            return address;
        }

        public void setAddress(boolean address) {
            this.address = address;
        }

        public boolean isAmount() {
            return amount;
        }

        public void setAmount(boolean amount) {
            this.amount = amount;
        }

        public boolean isCustomerName() {
            return customerName;
        }

        public void setCustomerName(boolean customerName) {
            this.customerName = customerName;
        }

        public boolean isMobileNumber() {
            return mobileNumber;
        }

        public void setMobileNumber(boolean mobileNumber) {
            this.mobileNumber = mobileNumber;
        }

        public boolean isInvoiceNumber() {
            return invoiceNumber;
        }

        public void setInvoiceNumber(boolean invoiceNumber) {
            this.invoiceNumber = invoiceNumber;
        }
    }
}
