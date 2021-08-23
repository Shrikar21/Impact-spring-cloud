package com.training.moviecatalogservice.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class MovieRating {

    
	private String username;
	private List<Rating> ratings;
}