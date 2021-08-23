package com.training.movieratingservice.model;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "movieratings")
@Data @NoArgsConstructor @AllArgsConstructor
public class MovieRating {

    @Id
	private String username;
	@ElementCollection
	private List<Rating> ratings;
}
