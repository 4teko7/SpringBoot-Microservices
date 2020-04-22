package io.teko.ratingsdataservice.models;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class UserRating {

	private String userId;
	private HashMap<String,List<Rating>> userRatings = new HashMap<String,List<Rating>>();
	
	public UserRating() {
		fillUserRatings();
	}
	public UserRating(List<Rating> userRatings,String userId) {
		fillUserRatings();
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	
	public void fillUserRatings() {
		userRatings.put("1", Arrays.asList(new Rating("1",1),
				new Rating("2",2),
				new Rating("3",3),
				new Rating("4",4),
				new Rating("5",5),
				new Rating("6",6),
				new Rating("7",7),
				new Rating("8",8),
				new Rating("9",9),
				new Rating("10",10)));
	}
	public HashMap<String, List<Rating>> getUserRatings() {
		return userRatings;
	}
	public void setUserRatings(HashMap<String, List<Rating>> userRatings) {
		this.userRatings = userRatings;
	}

}
