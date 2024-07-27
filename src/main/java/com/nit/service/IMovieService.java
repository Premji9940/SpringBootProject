package com.nit.service;

import java.util.List;

import com.nit.entity.Movie;

public interface IMovieService {

	public String movieInsert(Movie m);

	public Iterable<Movie> getMovie();

	public String deleteMovie(Integer id);

	public String updateMovie(Integer id);

}
