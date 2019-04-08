package com.stackroute.moviecruiserserverapplication.service;

import java.util.List;

import com.stackroute.moviecruiserserverapplication.domain.Movie;
import com.stackroute.moviecruiserserverapplication.exception.MovieAlreadyExistException;
import com.stackroute.moviecruiserserverapplication.exception.MovieNotFoundException;

public interface MovieService {
	boolean saveMovie(Movie movie) throws MovieAlreadyExistException;

	Movie updateMovie(Movie movie) throws MovieNotFoundException;

	boolean deleteMovieByid(int id) throws MovieNotFoundException;

	Movie getMovieById(String movieId,String userId) throws MovieNotFoundException;

	List<Movie> fetchAllMovies(String userId);

	boolean deleteByMovieIdAndUserId(String movieId,String userId) throws MovieNotFoundException;

}
