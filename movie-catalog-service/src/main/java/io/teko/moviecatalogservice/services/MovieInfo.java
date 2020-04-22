package io.teko.moviecatalogservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import io.teko.moviecatalogservice.models.CatalogItem;
import io.teko.moviecatalogservice.models.Movie;
import io.teko.moviecatalogservice.models.Rating;

@Service
public class MovieInfo {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${movieInfoUrl}")
	private String movieInfoUrl;
	

	@HystrixCommand(fallbackMethod = "getFallbackCatalogItem",
			threadPoolKey = "movieInfoPool",
			threadPoolProperties = {
					@HystrixProperty(name = "coreSize",value = "20"),
					@HystrixProperty(name = "maxQueueSize", value = "10"),
			},
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
					@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000"),
			
			}
			)
	public CatalogItem getCatalogItem(Rating rating) {
		Movie movie = restTemplate.getForObject(movieInfoUrl + rating.getMovieId(),Movie.class);
		
		return new CatalogItem(movie.getName(),movie.getDescription(),rating.getRating());
	}
	
	// FallBackMethodForCatalogItem
	public CatalogItem getFallbackCatalogItem(Rating rating) {
		return new CatalogItem("Movie Names Was not Found","No Description",0);
	}
	
	
}
