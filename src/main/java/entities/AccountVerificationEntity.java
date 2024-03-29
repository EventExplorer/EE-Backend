package com.ee.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "account_verification", schema = "EventExplorer")
public class AccountVerificationEntity {
    @Id
    @Column(name = "email", nullable = false, length = 100)
    private String email;
    @Basic
    @Column(name = "otp_passcode", nullable = false, length = 6)
    private String otpPasscode;
    @Basic
    @Column(name = "expiration_time", nullable = true)
    private LocalDateTime expirationTime;
    @Basic
    @Column(name = "created_at", nullable = true, length = 45)
    private LocalDateTime createdAt;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOtpPasscode() {
        return otpPasscode;
    }

    public void setOtpPasscode(String otpPasscode) {
        this.otpPasscode = otpPasscode;
    }

    public LocalDateTime getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(LocalDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountVerificationEntity that = (AccountVerificationEntity) o;

        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (otpPasscode != null ? !otpPasscode.equals(that.otpPasscode) : that.otpPasscode != null) return false;
        if (expirationTime != null ? !expirationTime.equals(that.expirationTime) : that.expirationTime != null)
            return false;
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + (otpPasscode != null ? otpPasscode.hashCode() : 0);
        result = 31 * result + (expirationTime != null ? expirationTime.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        return result;
    }
}
