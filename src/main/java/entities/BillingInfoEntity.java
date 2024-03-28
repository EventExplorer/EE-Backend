package com.ee.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "billing-info", schema = "EventExplorer", catalog = "")
public class BillingInfoEntity {
    @Basic
    @Column(name = "email", nullable = false, length = 50)
    private String email;
    @Basic
    @Column(name = "card_holder_name", nullable = false, length = 50)
    private String cardHolderName;
    @Basic
    @Column(name = "card_number", nullable = false, length = 12)
    private String cardNumber;
    @Basic
    @Column(name = "cvv", nullable = false)
    private int cvv;
    @Basic
    @Column(name = "expiry_date", nullable = false)
    private String expiryDate;
    @Basic
    @Column(name = "address", nullable = false, length = 50)
    private String address;
    @Basic
    @Column(name = "city", nullable = false, length = 50)
    private String city;
    @Basic
    @Column(name = "state", nullable = false, length = 50)
    private String state;
    @Basic
    @Column(name = "phone_number", nullable = false, length = 10)
    private String phoneNumber;
    @Basic
    @Column(name = "zipcode", nullable = false)
    private int zipcode;
    @Basic
    @Column(name = "bank_name", nullable = false, length = 50)
    private String bankName;

    @Id
    @Column(name = "billing_uuid", nullable = false, length = 70)
    private String billingUuid;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBillingUuid() {
        return billingUuid;
    }

    public void setBillingUuid(String billingUuid) {
        this.billingUuid = billingUuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BillingInfoEntity that = (BillingInfoEntity) o;

        if (cvv != that.cvv) return false;
        if (expiryDate != that.expiryDate) return false;
        if (zipcode != that.zipcode) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (cardHolderName != null ? !cardHolderName.equals(that.cardHolderName) : that.cardHolderName != null)
            return false;
        if (cardNumber != null ? !cardNumber.equals(that.cardNumber) : that.cardNumber != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(that.phoneNumber) : that.phoneNumber != null) return false;
        if (bankName != null ? !bankName.equals(that.bankName) : that.bankName != null) return false;
        if (billingUuid != null ? !billingUuid.equals(that.billingUuid) : that.billingUuid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + (cardHolderName != null ? cardHolderName.hashCode() : 0);
        result = 31 * result + (cardNumber != null ? cardNumber.hashCode() : 0);
        result = 31 * result + cvv;
        result = 31 * result + (expiryDate != null ? expiryDate.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + zipcode;
        result = 31 * result + (bankName != null ? bankName.hashCode() : 0);
        result = 31 * result + (billingUuid != null ? billingUuid.hashCode() : 0);
        return result;
    }
}
