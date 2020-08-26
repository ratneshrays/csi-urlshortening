/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kx.urlshortener.resource;

/**
 *
 * @author ratnesh
 */
import com.kx.urlshortener.Utility;
import com.kx.urlshortener.model.UrlModel;
import com.kx.urlshortener.repository.UrlRepository;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/stark/")
public class UrlController {
    
    @Autowired
    private UrlRepository urlRepository;
    @Autowired
    private MongoOperations mongoOperations;
	
	@RequestMapping(value="/addurl", method=RequestMethod.POST)
	public ResponseEntity<Object> getShortenUrl(@RequestBody UrlModel urlModel) throws MalformedURLException {
            if(urlModel.getOriginalUrl() != null && !urlModel.getOriginalUrl().isEmpty()){
		String randomChar = Utility.getRandomChars();
		Utility.setShortUrl(randomChar, urlModel);
                urlRepository.save(urlModel);
		return new ResponseEntity<Object>(urlModel, HttpStatus.OK);
            }
            else
                throw new MalformedURLException("URL not found");
	}
	
	@RequestMapping(value="/{short_key}", method=RequestMethod.GET)
	public void getFullUrl(HttpServletResponse response, @PathVariable String short_key) 
                throws IOException {
            List<UrlModel> urlModels = urlRepository.findByShortKey(short_key);
            String url = "";
            UrlModel urlModel = urlModels.get(0);
            if (urlModels.size() == 1 && urlModel != null) {
                url = urlModel.getOriginalUrl();
                Query query = new Query();
                query.addCriteria(Criteria.where("short_key").is(short_key));
                Update update = new Update();
                update.set("hit_count", urlModel.getHitCount()+1);
                update.set("modify_time", System.currentTimeMillis());
                mongoOperations.updateFirst(query, update, UrlModel.class);
            }
            else if(urlModel == null){
                String short_url = Utility.createUrl(short_key);
                UrlModel urlModel2 = urlRepository.findByShortUrl(short_url);
                if(urlModel2 != null){
                    url = urlModel.getOriginalUrl();
                }
                else
                    throw new IOException("URL Not Found");
            }
            response.sendRedirect(url);
	}
}
