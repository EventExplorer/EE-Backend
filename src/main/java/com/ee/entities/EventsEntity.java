package com.ee.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "events", schema = "EventExplorer", catalog = "")
public class EventsEntity {
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "event_id", nullable = false, length = 40)
    private String eventId;
    @Basic
    @Column(name = "event_name", nullable = false, length = 50)
    private String eventName;
    @Basic
    @Column(name = "event_type", nullable = false, length = 50)
    private String eventType;
    @Basic
    @Column(name = "event_date", nullable = false)
    private Date eventDate;
    @Basic
    @Column(name = "event_time", nullable = false)
    private Time eventTime;
    @Basic
    @Column(name = "venue_name", nullable = false, length = 50)
    private String venueName;
    @Basic
    @Column(name = "capacity", nullable = false)
    private int capacity;
    @Basic
    @Column(name = "create_ts", nullable = false)
    private Timestamp createTs;
    @Basic
    @Column(name = "update_ts", nullable = false)
    private Timestamp updateTs;
    @Basic
    @Column(name = "regular_price", nullable = false, precision = 2)
    private BigDecimal regularPrice;
    @Basic
    @Column(name = "city", nullable = false, length = 50)
    private String city;
    @Basic
    @Column(name = "state", nullable = false, length = 50)
    private String state;
    @Basic
    @Column(name = "contact_number", nullable = false, length = 10)
    private String contactNumber;
    @Basic
    @Column(name = "venue_type", nullable = false, length = 50)
    private String venueType;
    @Basic
    @Column(name = "description", nullable = false, length = 500)
    private String description;
    @Basic
    @Column(name = "event_email_address", nullable = false, length = 45)
    private String eventEmailAddress;
    @Basic
    @Column(name = "customer_id", nullable = false, length = 40)
    private String customerId;
    @Basic
    @Column(name = "user_email", nullable = false, length = 45)
    private String userEmail;
    @Basic
    @Column(name = "vip_price", nullable = false, precision = 2)
    private BigDecimal vipPrice;
    @Basic
    @Column(name = "disabled_price", nullable = false, precision = 2)
    private BigDecimal disabledPrice;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public Time getEventTime() {
        return eventTime;
    }

    public void setEventTime(Time eventTime) {
        this.eventTime = eventTime;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
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

    public BigDecimal getRegularPrice() {
        return regularPrice;
    }

    public void setRegularPrice(BigDecimal regularPrice) {
        this.regularPrice = regularPrice;
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

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getVenueType() {
        return venueType;
    }

    public void setVenueType(String venueType) {
        this.venueType = venueType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEventEmailAddress() {
        return eventEmailAddress;
    }

    public void setEventEmailAddress(String eventEmailAddress) {
        this.eventEmailAddress = eventEmailAddress;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public BigDecimal getVipPrice() {
        return vipPrice;
    }

    public void setVipPrice(BigDecimal vipPrice) {
        this.vipPrice = vipPrice;
    }

    public BigDecimal getDisabledPrice() {
        return disabledPrice;
    }

    public void setDisabledPrice(BigDecimal disabledPrice) {
        this.disabledPrice = disabledPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventsEntity that = (EventsEntity) o;

        if (capacity != that.capacity) return false;
        if (eventId != null ? !eventId.equals(that.eventId) : that.eventId != null) return false;
        if (eventName != null ? !eventName.equals(that.eventName) : that.eventName != null) return false;
        if (eventType != null ? !eventType.equals(that.eventType) : that.eventType != null) return false;
        if (eventDate != null ? !eventDate.equals(that.eventDate) : that.eventDate != null) return false;
        if (eventTime != null ? !eventTime.equals(that.eventTime) : that.eventTime != null) return false;
        if (venueName != null ? !venueName.equals(that.venueName) : that.venueName != null) return false;
        if (createTs != null ? !createTs.equals(that.createTs) : that.createTs != null) return false;
        if (updateTs != null ? !updateTs.equals(that.updateTs) : that.updateTs != null) return false;
        if (regularPrice != null ? !regularPrice.equals(that.regularPrice) : that.regularPrice != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (contactNumber != null ? !contactNumber.equals(that.contactNumber) : that.contactNumber != null)
            return false;
        if (venueType != null ? !venueType.equals(that.venueType) : that.venueType != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (eventEmailAddress != null ? !eventEmailAddress.equals(that.eventEmailAddress) : that.eventEmailAddress != null)
            return false;
        if (customerId != null ? !customerId.equals(that.customerId) : that.customerId != null) return false;
        if (userEmail != null ? !userEmail.equals(that.userEmail) : that.userEmail != null) return false;
        if (vipPrice != null ? !vipPrice.equals(that.vipPrice) : that.vipPrice != null) return false;
        if (disabledPrice != null ? !disabledPrice.equals(that.disabledPrice) : that.disabledPrice != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = eventId != null ? eventId.hashCode() : 0;
        result = 31 * result + (eventName != null ? eventName.hashCode() : 0);
        result = 31 * result + (eventType != null ? eventType.hashCode() : 0);
        result = 31 * result + (eventDate != null ? eventDate.hashCode() : 0);
        result = 31 * result + (eventTime != null ? eventTime.hashCode() : 0);
        result = 31 * result + (venueName != null ? venueName.hashCode() : 0);
        result = 31 * result + capacity;
        result = 31 * result + (createTs != null ? createTs.hashCode() : 0);
        result = 31 * result + (updateTs != null ? updateTs.hashCode() : 0);
        result = 31 * result + (regularPrice != null ? regularPrice.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (contactNumber != null ? contactNumber.hashCode() : 0);
        result = 31 * result + (venueType != null ? venueType.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (eventEmailAddress != null ? eventEmailAddress.hashCode() : 0);
        result = 31 * result + (customerId != null ? customerId.hashCode() : 0);
        result = 31 * result + (userEmail != null ? userEmail.hashCode() : 0);
        result = 31 * result + (vipPrice != null ? vipPrice.hashCode() : 0);
        result = 31 * result + (disabledPrice != null ? disabledPrice.hashCode() : 0);
        return result;
    }
}
