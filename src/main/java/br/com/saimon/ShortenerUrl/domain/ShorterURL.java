package br.com.saimon.ShortenerUrl.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Objects;


@Document
public class ShorterURL {
    @Id
    private String id;
    private String url;
    private String userId;
    private String hash;
    private Date createdAt;
    private boolean expired;

    public ShorterURL() {
    }

    public ShorterURL(String url, String userId, String hash, Date createdAt, boolean expired) {
        this.url = url;
        this.userId = userId;
        this.hash = hash;
        this.createdAt = createdAt;
        this.expired = expired;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShorterURL)) return false;
        ShorterURL that = (ShorterURL) o;
        return isExpired() == that.isExpired() && Objects.equals(getId(), that.getId()) && Objects.equals(getUrl(), that.getUrl()) && Objects.equals(getUserId(), that.getUserId()) && Objects.equals(getHash(), that.getHash()) && Objects.equals(getCreatedAt(), that.getCreatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUrl(), getUserId(), getHash(), getCreatedAt(), isExpired());
    }
}
