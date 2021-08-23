package com.training.moviecatalogservice.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.training.moviecatalogservice.model.MovieRating;

@Service
public class MovieRatingService {

	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod="getMovieRatingFallback", commandProperties= {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="2000"),
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="8"),
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="50"),
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="10000")
        })
	public MovieRating getMovieRating(String username) {
		
		ResponseEntity<MovieRating> response = restTemplate.getForEntity("http://MOVIE-RATING-SERVICE/api/v1/ratings/" + username, MovieRating.class);
		MovieRating movieRating = response.getBody();
		return movieRating;
	}
	
	public MovieRating getMovieRatingFallback(String username) {
		return new MovieRating(username, Collections.emptyList());
	}
	
	@HystrixCommand(fallbackMethod="getMovieCountFallback", commandProperties= {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="2000"),
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="8"),
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="50"),
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="10000")
        })
	public int getMovieCount(String username) {
		ResponseEntity<Integer> response = restTemplate.getForEntity("http://MOVIE-RATING-SERVICE/api/v1/ratings/" + username + "/movies/count", Integer.class);
		return response.getBody();
	}
	
	public int getMovieCountFallback(String username) {
		return 0;
	}
}
