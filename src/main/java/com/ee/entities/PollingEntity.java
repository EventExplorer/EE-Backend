package com.ee.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "polling", schema = "EventExplorer", catalog = "")
public class PollingEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "category_id", nullable = false)
    private String categoryId;
    @Basic
    @Column(name = "artist_name", nullable = false, length = 255)
    private String artistName;
    @Basic
    @Column(name = "votes", nullable = false)
    private String votes;
    @Basic
    @Column(name = "email", nullable = false, length = 45)
    private String email;
    @Basic
    @Column(name = "current_ts", nullable = false)
    private Timestamp currentTs;
    @Basic
    @Column(name = "update_ts", nullable = false)
    private Timestamp updateTs;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getVotes() {
        return votes;
    }

    public void setVotes(String votes) {
        this.votes = votes;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getCurrentTs() {
        return currentTs;
    }

    public void setCurrentTs(Timestamp currentTs) {
        this.currentTs = currentTs;
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

        PollingEntity that = (PollingEntity) o;

        if (id != that.id) return false;
        if (categoryId != null ? !categoryId.equals(that.categoryId) : that.categoryId != null) return false;
        if (votes != null ? !votes.equals(that.votes) : that.votes != null) return false;
        if (artistName != null ? !artistName.equals(that.artistName) : that.artistName != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (currentTs != null ? !currentTs.equals(that.currentTs) : that.currentTs != null) return false;
        if (updateTs != null ? !updateTs.equals(that.updateTs) : that.updateTs != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (categoryId != null ? categoryId.hashCode() : 0);
        result = 31 * result + (artistName != null ? artistName.hashCode() : 0);
        result = 31 * result + (votes != null ? votes.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (currentTs != null ? currentTs.hashCode() : 0);
        result = 31 * result + (updateTs != null ? updateTs.hashCode() : 0);
        return result;
    }
}
