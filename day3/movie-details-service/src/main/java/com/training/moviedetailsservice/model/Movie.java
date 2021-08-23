package com.training.moviedetailsservice.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "movies")
@Data @NoArgsConstructor @AllArgsConstructor
public class Movie {
	
	@Id	
	private int movieId;
	private String title;
	private int releaseYear;
	private List<String> directors;
	private List<String> casts;
	
}
