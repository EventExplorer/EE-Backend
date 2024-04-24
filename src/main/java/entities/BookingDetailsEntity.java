package com.ee.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "booking_details", schema = "EventExplorer", catalog = "")
public class BookingDetailsEntity {
    @Basic
    @Column(name = "event_id", nullable = false, length = 100)
    private String eventId;
    @Basic
    @Column(name = "email_id", nullable = false, length = 255)
    private String emailId;
    @Id
    @Column(name = "order_number", nullable = false, length = 50)
    private String orderNumber;
    @Basic
    @Column(name = "seat_number", nullable = false, length = 45)
    private String seatNumber;
    @Basic
    @Column(name = "attendees", nullable = false)
    private int attendees;
    @Basic
    @Column(name = "total_price", nullable = false, precision = 2)
    private BigDecimal totalPrice;
    @Basic
    @Column(name = "billing_uuid", nullable = false, length = 70)
    private String billingUuid;
    @Basic
    @Column(name = "confirmation_code", nullable = false, length = 50)
    private String confirmationCode;
    @Basic
    @Column(name = "create_ts", nullable = true)
    private Timestamp createTs;
    @Basic
    @Column(name = "update_ts", nullable = true)
    private Timestamp updateTs;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public int getAttendees() {
        return attendees;
    }

    public void setAttendees(int attendees) {
        this.attendees = attendees;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getBillingUuid() {
        return billingUuid;
    }

    public void setBillingUuid(String billingUuid) {
        this.billingUuid = billingUuid;
    }

    public String getConfirmationCode() {
        return confirmationCode;
    }

    public void setConfirmationCode(String confirmationCode) {
        this.confirmationCode = confirmationCode;
    }

    public Timestamp getCreateTs() {
        return createTs;
    }

    public void setCreateTs(Timestamp createTs) {
        this.createTs = createTs;
    }

    public Timestamp getUpdateTs() {
        return updateTs;
    }

    public void setUpdateTs(Timestamp updateTs) {
        this.updateTs = updateTs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookingDetailsEntity that = (BookingDetailsEntity) o;

        if (attendees != that.attendees) return false;
        if (eventId != null ? !eventId.equals(that.eventId) : that.eventId != null) return false;
        if (emailId != null ? !emailId.equals(that.emailId) : that.emailId != null) return false;
        if (orderNumber != null ? !orderNumber.equals(that.orderNumber) : that.orderNumber != null) return false;
        if (seatNumber != null ? !seatNumber.equals(that.seatNumber) : that.seatNumber != null) return false;
        if (totalPrice != null ? !totalPrice.equals(that.totalPrice) : that.totalPrice != null) return false;
        if (billingUuid != null ? !billingUuid.equals(that.billingUuid) : that.billingUuid != null) return false;
        if (confirmationCode != null ? !confirmationCode.equals(that.confirmationCode) : that.confirmationCode != null)
            return false;
        if (createTs != null ? !createTs.equals(that.createTs) : that.createTs != null) return false;
        if (updateTs != null ? !updateTs.equals(that.updateTs) : that.updateTs != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = eventId != null ? eventId.hashCode() : 0;
        result = 31 * result + (emailId != null ? emailId.hashCode() : 0);
        result = 31 * result + (orderNumber != null ? orderNumber.hashCode() : 0);
        result = 31 * result + (seatNumber != null ? seatNumber.hashCode() : 0);
        result = 31 * result + attendees;
        result = 31 * result + (totalPrice != null ? totalPrice.hashCode() : 0);
        result = 31 * result + (billingUuid != null ? billingUuid.hashCode() : 0);
        result = 31 * result + (confirmationCode != null ? confirmationCode.hashCode() : 0);
        result = 31 * result + (createTs != null ? createTs.hashCode() : 0);
        result = 31 * result + (updateTs != null ? updateTs.hashCode() : 0);
        return result;
    }
}
