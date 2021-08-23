package com.training.movieratingservice.controller;

import org.bouncycastle.asn1.cms.OtherKeyAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.movieratingservice.model.MovieRating;
import com.training.movieratingservice.repository.MovieRatingRepository;

@RestController
@RequestMapping("/api/v1")
public class MovieRatingController {
	
	@Autowired
	private MovieRatingRepository movieRatingRepository;

	@GetMapping("/ratings/{username}")
	public ResponseEntity<MovieRating> getRatingsByUsername(@PathVariable String username) {
		
		MovieRating movieRating = movieRatingRepository.findById(username).get();
		return new ResponseEntity<MovieRating>(movieRating, HttpStatus.OK);
	}
	
	@PostMapping("/ratings")
	public ResponseEntity<MovieRating> addNewMovieRating(@RequestBody MovieRating movieRating) { 
		
		MovieRating savedMovieRating = movieRatingRepository.save(movieRating);
		return new ResponseEntity<MovieRating>(savedMovieRating, HttpStatus.CREATED);
	}
	
	@GetMapping("/ratings/{username}/movies/count")
	public ResponseEntity<Integer> getMovieCounts(@PathVariable String username) {
		
		MovieRating movieRatings = movieRatingRepository.findById(username).get();
		return new ResponseEntity<Integer>(movieRatings.getRatings().size(), HttpStatus.OK); 
	}
}











