package com.nit.repo;

import org.springframework.data.repository.CrudRepository;

import com.nit.entity.Movie;

public interface IMovieRepo extends CrudRepository<Movie, Integer> {

}
