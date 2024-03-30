package entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "Events", schema = "EventExplorer")
public class EventsEntity {
    @Id
    @Column(name = "event_id")
    private String eventId;
    @Basic
    @Column(name = "event_name")
    private String eventName;
    @Basic
    @Column(name = "event_type")
    private String eventType;
    @Basic
    @Column(name = "event_date")
    private Date eventDate;
    @Basic
    @Column(name = "event_time")
    private Time eventTime;
    @Basic
    @Column(name = "venue_name")
    private String venueName;
    @Basic
    @Column(name = "capacity")
    private int capacity;
    @Basic
    @Column(name = "create_ts")
    private Timestamp createTs;
    @Basic
    @Column(name = "update_ts")
    private Timestamp updateTs;
    @Basic
    @Column(name = "price")
    private BigDecimal price;
    @Basic
    @Column(name = "city")
    private String city;
    @Basic
    @Column(name = "state")
    private String state;
    @Basic
    @Column(name = "contact_number")
    private String contactNumber;
    @Basic
    @Column(name = "venue_type")
    private String venueType;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "event_email_address")
    private String eventEmailAddress;
    @Basic
    @Column(name = "customer_id")
    private String customerId;
    @Basic
    @Column(name = "user_email")
    private String userEmail;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventsEntity that = (EventsEntity) o;
        return capacity == that.capacity && Objects.equals(eventId, that.eventId) && Objects.equals(eventName, that.eventName) && Objects.equals(eventType, that.eventType) && Objects.equals(eventDate, that.eventDate) && Objects.equals(eventTime, that.eventTime) && Objects.equals(venueName, that.venueName) && Objects.equals(createTs, that.createTs) && Objects.equals(updateTs, that.updateTs) && Objects.equals(price, that.price) && Objects.equals(city, that.city) && Objects.equals(state, that.state) && Objects.equals(contactNumber, that.contactNumber) && Objects.equals(venueType, that.venueType) && Objects.equals(description, that.description) && Objects.equals(eventEmailAddress, that.eventEmailAddress) && Objects.equals(customerId, that.customerId) && Objects.equals(userEmail, that.userEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, eventName, eventType, eventDate, eventTime, venueName, capacity, createTs, updateTs, price, city, state, contactNumber, venueType, description, eventEmailAddress, customerId, userEmail);
    }
}