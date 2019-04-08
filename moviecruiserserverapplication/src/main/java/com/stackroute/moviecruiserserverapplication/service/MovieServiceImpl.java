package com.stackroute.moviecruiserserverapplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.moviecruiserserverapplication.domain.Movie;
import com.stackroute.moviecruiserserverapplication.exception.MovieAlreadyExistException;
import com.stackroute.moviecruiserserverapplication.exception.MovieNotFoundException;
import com.stackroute.moviecruiserserverapplication.repository.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	public MovieRepository movieRepo;
	
	public MovieServiceImpl(MovieRepository movieRepo) {
		// TODO Auto-generated constructor stub
		this.movieRepo = movieRepo ;
	}

	@Override
	public boolean saveMovie(Movie movie) throws MovieAlreadyExistException {
		// TODO Auto-generated method stub
		final Optional<Movie> object = movieRepo.findById(movie.getId());
		if (object.isPresent()) {
			throw new MovieAlreadyExistException("Could not save Movie, Movie already exists");
		}
		movieRepo.save(movie);
		return true;
	}

	@Override
	public Movie updateMovie(Movie movie) throws MovieNotFoundException {
		// TODO Auto-generated method stub
		Movie updatedMovie = new Movie();
		
		Optional<Movie> object = movieRepo.findByMovieIdAndUserId(movie.getMovieId(), movie.getUserId());
		movie.setId(object.get().getId());
		if (object.isPresent()) {
			updatedMovie = movieRepo.save(movie);
		} else
			throw new MovieNotFoundException("Movie does not exist,Can't update");
		return updatedMovie;
	}

	@Override
	public boolean deleteMovieByid(int id) throws MovieNotFoundException {
		// TODO Auto-generated method stub
		Optional<Movie> object = movieRepo.findById(id);
		if (object.isPresent()) {
			movieRepo.deleteById(id);
		} else
			throw new MovieNotFoundException("Movie does not exists,Can't delete");
		return true;
	}

	@Override
	public Movie getMovieById(String movieId,String userId) throws MovieNotFoundException {
		// TODO Auto-generated method stub
		Movie movie = new Movie();
		Optional<Movie> object = movieRepo.findByMovieIdAndUserId(movieId,userId);
		if (object.isPresent()) {
			movie = object.get();
		} else
			throw new MovieNotFoundException("Movie does not exists,Can't fetch details");
		return movie;
	}

	//returns the list of all movies of a particular user
	@Override
	public List<Movie> fetchAllMovies(String userId) {
		// TODO Auto-generated method stub
		return movieRepo.findByUserId(userId);

	}

	@Override
	public boolean deleteByMovieIdAndUserId(String movieId, String userId)throws MovieNotFoundException {
		// TODO Auto-generated method stub
		Optional<Movie> options = movieRepo.findByMovieIdAndUserId(movieId, userId);
		if(options.isPresent())
		{
			movieRepo.deleteByMovieIdAndUserId(movieId,userId);
		}else throw new MovieNotFoundException("Movie does not exist");
		return false;
	}

}
