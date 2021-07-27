package com.training.moviecatalogservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class CatalogItem {

	private Movie movie;
	private int rating;
}
