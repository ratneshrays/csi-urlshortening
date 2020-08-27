/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kx.urlshortener.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 *
 * @author ratnesh
 */
@Document(collection = "url_shortening")
public class UrlModel {
    @Id
    @Field(name="id")
    String id;
    
    @Indexed(unique = true)
    @Field(name="short_key")
    String shortKey;
    
    @Indexed(unique = true)
    @Field(name="short_url")
    String shortUrl;
    
    @Field(name="original_url")
    String originalUrl;
    
    @Field(name="user_id")
    String userId;
    
    @Field(name="custom_url")
    String customUrl;
    
    @Field(name="creation_time")
    long creationTime;
    
    @Field(name="modify_time")
    long modifyTime;
    
    @Field(name="hit_count")
    long hitCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShortKey() {
        return shortKey;
    }

    public void setShortKey(String shortKey) {
        this.shortKey = shortKey;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCustomUrl() {
        return customUrl;
    }

    public void setCustomUrl(String customUrl) {
        this.customUrl = customUrl;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }

    public long getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(long modifyTime) {
        this.modifyTime = modifyTime;
    }

    public long getHitCount() {
        return hitCount;
    }

    public void setHitCount(long hitCount) {
        this.hitCount = hitCount;
    }

    @Override
    public String toString() {
        return "UrlModel{" + "id=" + id + ", shortKey=" + shortKey + ", shortUrl=" + shortUrl + ", originalUrl=" + originalUrl + ", userId=" + userId + ", customUrl=" + customUrl + ", creationTime=" + creationTime + ", modifyTime=" + modifyTime + ", hitCount=" + hitCount + '}';
    }

    
    
}
