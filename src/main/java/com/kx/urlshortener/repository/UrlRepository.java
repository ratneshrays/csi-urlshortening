/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kx.urlshortener.repository;

import com.kx.urlshortener.model.UrlModel;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author ratnesh
 */
public interface UrlRepository extends MongoRepository<UrlModel, String>{
        
    public List<UrlModel> findByShortKey(String shortKey);
    
    public UrlModel findByShortUrl(String shortUrl);
    
}
