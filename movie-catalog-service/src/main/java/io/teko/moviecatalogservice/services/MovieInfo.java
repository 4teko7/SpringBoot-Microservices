package io.teko.moviecatalogservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.teko.moviecatalogservice.models.CatalogItem;
import io.teko.moviecatalogservice.models.Movie;
import io.teko.moviecatalogservice.models.Rating;

@Service
public class MovieInfo {
	
	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "getFallbackCatalogItem")
	public CatalogItem getCatalogItem(Rating rating) {
		Movie movie = restTemplate.getForObject("http://movieinfoservis/movies/" + rating.getMovieId(),Movie.class);
		
		return new CatalogItem(movie.getName(),movie.getDescription(),rating.getRating());
	}
	
	// FallBackMethodForCatalogItem
	public CatalogItem getFallbackCatalogItem(Rating rating) {
		return new CatalogItem("Movie Names Was not Found","No Description",0);
	}
	
	
}
