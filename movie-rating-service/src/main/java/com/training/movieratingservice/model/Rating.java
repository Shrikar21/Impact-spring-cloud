package com.training.movieratingservice.model;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data @NoArgsConstructor @AllArgsConstructor
public class Rating {
	
	private int movieId;
	private int rating;
}
