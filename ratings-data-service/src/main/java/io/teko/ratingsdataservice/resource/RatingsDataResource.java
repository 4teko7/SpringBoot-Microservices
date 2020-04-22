package io.teko.ratingsdataservice.resource;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.teko.ratingsdataservice.models.Rating;
import io.teko.ratingsdataservice.models.UserRating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsDataResource {
	
	UserRating userRating = new UserRating();
	
	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId) {
		return new Rating(movieId,4);
	}
	
	
	@RequestMapping("users/{userId}")
	public UserRating getUserRating(@PathVariable("userId") String userId) {
		userRating.setUserId(userId);
		return userRating;
	}
	
}
