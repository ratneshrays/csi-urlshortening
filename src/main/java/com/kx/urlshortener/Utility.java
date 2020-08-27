/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kx.urlshortener;

import com.kx.urlshortener.model.UrlModel;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ratnesh
 */
public class Utility {
    
    private Map<String, UrlModel> shortenUrlList = new HashMap<>();

    public static String getRandomChars() {
        String randomStr = "";
        String possibleChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        for (int i = 0; i < 9; i++){
                randomStr += possibleChars.charAt((int) Math.floor(Math.random() * possibleChars.length()));
        System.out.println("Random String is :: " +randomStr);
        }
        System.out.println("Random String is :: " +randomStr);
        return randomStr;
    } 
    
    public static UrlModel setShortUrl(String randomChar, UrlModel urlModel) throws MalformedURLException {
        urlModel.setShortKey(randomChar);
        urlModel.setCreationTime(System.currentTimeMillis());
        urlModel.setModifyTime(System.currentTimeMillis());
        urlModel.setShortUrl("http://localhost:8888/stark/"+randomChar);
        return urlModel;
    }
    
    public static String createUrl(String short_url) {
        String url = "http://localhost:8888/stark/"+short_url;
        return url;
    }
    
}
