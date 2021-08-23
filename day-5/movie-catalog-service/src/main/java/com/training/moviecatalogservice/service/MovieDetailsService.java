package com.training.moviecatalogservice.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.training.moviecatalogservice.model.Movie;

@Service
public class MovieDetailsService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod="getMovieDetailsFallback", commandProperties= {
	                                 @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="2000"),
	                                 @HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="8"),
	                                 @HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="50"),
	                                 @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="10000")
	                             })
	public Movie getMovieDetails(int id) {
		ResponseEntity<Movie> response = restTemplate.getForEntity("http://MOVIE-DETAILS-SERVICE/api/v1/movies/" + id, Movie.class);
		return response.getBody();
	}
		
	public Movie getMovieDetailsFallback(int id) {
	    return new Movie(id, "Unknown", 0, Collections.emptyList(), Collections.emptyList());
	}
	

}
