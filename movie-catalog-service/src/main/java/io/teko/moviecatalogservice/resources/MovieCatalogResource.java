package io.teko.moviecatalogservice.resources;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.springframework.cloud.client.discovery.DiscoveryClient;

import io.teko.moviecatalogservice.models.CatalogItem;
import io.teko.moviecatalogservice.models.Movie;
import io.teko.moviecatalogservice.models.Rating;
import io.teko.moviecatalogservice.models.UserRating;

import java.util.Arrays;


@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
//	@HystrixCommand(fallbackMethod = "getFallbackCatalog")
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
		
		UserRating userRatings = getUserRating(userId);
		try{return userRatings.getUserRatings().get(userId).stream().map(rating -> getCatalogItem(rating) ).collect(Collectors.toList());}
		catch(Exception e) {return Arrays.asList(getFallbackCatalogItem(new Rating()));}
		

	}
	

	@HystrixCommand(fallbackMethod = "getFallbackUserRating")
	private UserRating getUserRating(@PathVariable("userId") String userId) {
		
		return restTemplate.getForObject("http://ratingsdataservice/ratingsdata/users/" + userId, UserRating.class);
	}
	
	@HystrixCommand(fallbackMethod = "getFallbackCatalogItem")
	private CatalogItem getCatalogItem(Rating rating) {
		Movie movie = restTemplate.getForObject("http://movieinfoservis/movies/" + rating.getMovieId(),Movie.class);
		
		return new CatalogItem(movie.getName(),movie.getDescription(),rating.getRating());
	}
	
	
	// FallBackMethodForUserRating
	private UserRating getFallbackUserRating(@PathVariable("userId") String userId) {
		UserRating userRating = new UserRating();
		userRating.setUserId(userId);
		HashMap<String,List<Rating>> hashMap = new HashMap<String,List<Rating>>();
		hashMap.put("1",Arrays.asList(new Rating("0",0)));
		userRating.setUserRatings(hashMap);
		return userRating;
	}
	
	// FallBackMethodForCatalogItem
	private CatalogItem getFallbackCatalogItem(Rating rating) {
		return new CatalogItem("Movie Names Was not Found","No Description",0);
	}
		
}





//Movie movie = webClientBuilder.build()
//.get()
//.uri("http://localhost:8082/movies/\" + rating.getMovieId()")
//.retrieve()
//.bodyToMono(Movie.class)
//.block();





//List<CatalogItem> a = new ArrayList<CatalogItem>();
//a.add(new CatalogItem("Batman","Kara Şovalye Batman",9));
//a.add(new CatalogItem("Batman","Kara Şovalye Batman",9));
//
//CatalogItem b[] = {new CatalogItem("Batman","Kara Şovalye Batman",9),new CatalogItem("Superman","Mavi Şovalye Superman",8)};
//return b;


