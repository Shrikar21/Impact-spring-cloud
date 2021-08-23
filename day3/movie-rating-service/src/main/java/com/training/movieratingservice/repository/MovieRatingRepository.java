package com.training.movieratingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.movieratingservice.model.MovieRating;

public interface MovieRatingRepository extends JpaRepository<MovieRating, String> {

}
