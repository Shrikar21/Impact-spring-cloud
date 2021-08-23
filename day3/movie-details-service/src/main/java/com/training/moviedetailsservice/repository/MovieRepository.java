package com.training.moviedetailsservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.training.moviedetailsservice.model.Movie;

public interface MovieRepository extends MongoRepository<Movie, Integer> {

}
