package io.teko.moviecatalogservice.services;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.teko.moviecatalogservice.models.Rating;
import io.teko.moviecatalogservice.models.UserRating;

@Service
public class UserRatingInfo {
	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "getFallbackUserRating")
	public UserRating getUserRating(@PathVariable("userId") String userId) {
		
		return restTemplate.getForObject("http://ratingsdataservice/ratingsdata/users/" + userId, UserRating.class);
	}
	
	
	// FallBackMethodForUserRating
	public UserRating getFallbackUserRating(@PathVariable("userId") String userId) {
		UserRating userRating = new UserRating();
		userRating.setUserId(userId);
		HashMap<String,List<Rating>> hashMap = new HashMap<String,List<Rating>>();
		hashMap.put("1",Arrays.asList(new Rating("0",0)));
		userRating.setUserRatings(hashMap);
		return userRating;
	}

}
