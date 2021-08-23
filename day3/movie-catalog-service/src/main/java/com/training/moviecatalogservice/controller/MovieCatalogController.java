package com.training.moviecatalogservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.training.moviecatalogservice.model.CatalogItem;
import com.training.moviecatalogservice.model.Movie;
import com.training.moviecatalogservice.model.MovieCatalog;
import com.training.moviecatalogservice.model.MovieRating;
import com.training.moviecatalogservice.model.Rating;

@RestController
@RequestMapping("/api/v1")
@RefreshScope
public class MovieCatalogController {
	
	@Value("${app.version}")
	private String appVersion;
	
	@Value("${app.name}")
	private String appName;
	
	@Value("${app.author.email}")
	private String appAuthorEmail;	
	
	@Autowired
	private RestTemplate restTemplate;
	
	/*@Autowired
	private DiscoveryClient discoveryClient;*/

	@GetMapping("/catalog/{username}")
	public ResponseEntity<MovieCatalog> getMovieCatalogByUsername(@PathVariable String username) {
		
		/*List<ServiceInstance> list1 = discoveryClient.getInstances("MOVIE-RATING-SERVICE");
		list1.forEach(serviceInstance -> System.out.println(serviceInstance.getUri()));
		
		List<ServiceInstance> list2 = discoveryClient.getInstances("MOVIE-DETAILS-SERVICE");
		list2.forEach(serviceInstance -> System.out.println(serviceInstance.getUri()));*/
		
		// Call movie-rating-service to get all ratings given by user
		
		ResponseEntity<MovieRating> response = restTemplate.getForEntity("http://MOVIE-RATING-SERVICE/api/v1/ratings/" + username, MovieRating.class);
		MovieRating movieRating = response.getBody();
		List<Rating> ratings = movieRating.getRatings();
		
		// Call movie-details-service to get movie information
		
		MovieCatalog movieCatalog = new MovieCatalog();
		movieCatalog.setUsername(username);
		List<CatalogItem> catalogItems = new ArrayList<>();
		movieCatalog.setCatalogItems(catalogItems);
		
		for (Rating rating: ratings) {
			
			// ResponseEntity<Movie> response = restTemplate.getForEntity("http://localhost:8081/api/v1/movies/" + rating.getMovieId(), Movie.class);
			
			Movie movie = restTemplate.getForEntity("http://MOVIE-DETAILS-SERVICE/api/v1/movies/" + rating.getMovieId(), Movie.class).getBody();
			CatalogItem catalogItem = new CatalogItem(movie, rating.getRating());
			catalogItems.add(catalogItem);
		}
		
		return new ResponseEntity<MovieCatalog>(movieCatalog, HttpStatus.OK);
	}
	
	@GetMapping("/app-info")
	public String getAppDetails() {
		return "App Version => " + appVersion + " |  App Name => " + appName + " | App Author Email => " + appAuthorEmail;
 	}
}













