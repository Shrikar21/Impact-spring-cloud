package com.training.moviecatalogservice.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class Movie {
		
	private int movieId;
	private String title;
	private int releaseYear;
	private List<String> directors;
	private List<String> casts;
	
}