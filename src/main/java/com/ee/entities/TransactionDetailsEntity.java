package com.ee.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "transaction_details", schema = "EventExplorer", catalog = "")
public class TransactionDetailsEntity {
    @Id
    @Column(name = "order_number", nullable = false, length = 50)
    private String orderNumber;
    @Basic
    @Column(name = "transaction_id", nullable = false, length = 50)
    private String transactionId;
    @Basic
    @Column(name = "transaction_ts", nullable = false)
    private Timestamp transactionTs;
    @Basic
    @Column(name = "bank_name", nullable = false, length = 50)
    private String bankName;
    @Basic
    @Column(name = "billing_uuid", nullable = false, length = 70)
    private String billingUuid;
    @Basic
    @Column(name = "total_price", nullable = false, length = 45)
    private BigDecimal totalPrice;
    @Basic
    @Column(name = "status", nullable = true, length = 50)
    private String status;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Timestamp getTransactionTs() {
        return transactionTs;
    }

    public void setTransactionTs(Timestamp transactionTs) {
        this.transactionTs = transactionTs;
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

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransactionDetailsEntity that = (TransactionDetailsEntity) o;

        if (orderNumber != null ? !orderNumber.equals(that.orderNumber) : that.orderNumber != null) return false;
        if (transactionId != null ? !transactionId.equals(that.transactionId) : that.transactionId != null)
            return false;
        if (transactionTs != null ? !transactionTs.equals(that.transactionTs) : that.transactionTs != null)
            return false;
        if (bankName != null ? !bankName.equals(that.bankName) : that.bankName != null) return false;
        if (billingUuid != null ? !billingUuid.equals(that.billingUuid) : that.billingUuid != null) return false;
        if (totalPrice != null ? !totalPrice.equals(that.totalPrice) : that.totalPrice != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderNumber != null ? orderNumber.hashCode() : 0;
        result = 31 * result + (transactionId != null ? transactionId.hashCode() : 0);
        result = 31 * result + (transactionTs != null ? transactionTs.hashCode() : 0);
        result = 31 * result + (bankName != null ? bankName.hashCode() : 0);
        result = 31 * result + (billingUuid != null ? billingUuid.hashCode() : 0);
        result = 31 * result + (totalPrice != null ? totalPrice.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
