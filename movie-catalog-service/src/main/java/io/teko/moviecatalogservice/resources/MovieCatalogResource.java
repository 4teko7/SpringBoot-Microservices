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
import io.teko.moviecatalogservice.services.MovieInfo;
import io.teko.moviecatalogservice.services.UserRatingInfo;

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
	
	
	@Autowired
	private MovieInfo movieInfo;
	
	@Autowired
	private UserRatingInfo userRatingInfo;
	
//	@HystrixCommand(fallbackMethod = "getFallbackCatalog")
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
		
		UserRating userRatings = userRatingInfo.getUserRating(userId);
		try{return userRatings.getUserRatings().get(userId).stream().map(rating -> movieInfo.getCatalogItem(rating) ).collect(Collectors.toList());}
		catch(Exception e) {return Arrays.asList(movieInfo.getFallbackCatalogItem(new Rating()));}
		

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


