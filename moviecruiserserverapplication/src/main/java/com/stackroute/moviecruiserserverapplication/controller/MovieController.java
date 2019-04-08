package com.stackroute.moviecruiserserverapplication.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.moviecruiserserverapplication.domain.Movie;
import com.stackroute.moviecruiserserverapplication.exception.MovieAlreadyExistException;
import com.stackroute.moviecruiserserverapplication.exception.MovieNotFoundException;
import com.stackroute.moviecruiserserverapplication.service.MovieService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.swagger.annotations.Api;


@RequestMapping("/api/v1/movieservice/movie")
@CrossOrigin
@Api(value = "onlinestore", description = "Operations pertaining to movie cruiser application")
@RestController
public class MovieController {

	@Autowired
	public MovieService movieService;
	
	public MovieController(final MovieService movieService) {
		// TODO Auto-generated constructor stub
		this.movieService = movieService ;
	}

	// if movie already exists it won't be saved
	@PostMapping()
	public ResponseEntity<?> saveNewMovie(@RequestBody final Movie movie, HttpServletRequest request) {
		
		final String authHeader = request.getHeader("authorization");
		final String token = authHeader.substring(7);
		String userId = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
		System.out.println("---->"+userId);
		
		ResponseEntity<?> responseEntity;
		try {
			movie.setUserId(userId);
			movieService.saveMovie(movie);
			responseEntity = new ResponseEntity<Movie>(movie, HttpStatus.CREATED);
		} catch (MovieAlreadyExistException e) {
			// TODO Auto-generated catch block
			responseEntity = new ResponseEntity<String>("{ \"message\" : \"" + e.getMessage() + "\"}",
					HttpStatus.CONFLICT);
		}
		return responseEntity;

	}

	// update movie only if it already exist in Db
	@PutMapping
	public ResponseEntity<?> updateMovie(@RequestBody final Movie movie, HttpServletRequest req) {
		System.out.println("--->--->"+movie);
		Claims claim =(Claims) req.getAttribute("claims");
		String userId = claim.getSubject();
		movie.setUserId(userId);
		ResponseEntity<?> responseEntity;
		try {
			responseEntity = new ResponseEntity<Movie>(movieService.updateMovie(movie), HttpStatus.OK);
		} catch (MovieNotFoundException e) {
			// TODO Auto-generated catch block
			responseEntity = new ResponseEntity<String>("{ \"message\" : \"" + e.getMessage() + "\"}",
					HttpStatus.NOT_FOUND);
		}
		return responseEntity;

	}

	// get movie details by Id only if id matches
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> getMovieById(@PathVariable("id") final String movieId, HttpServletRequest req) {
		ResponseEntity<?> responseEntity;
		try {
			Claims claim =(Claims) req.getAttribute("claims");
			String userId = claim.getSubject();
			responseEntity = new ResponseEntity<Movie>(movieService.getMovieById(movieId,userId), HttpStatus.OK);
		} catch (MovieNotFoundException e) {
			// TODO Auto-generated catch block
			responseEntity = new ResponseEntity<String>("{ \"message\" : \"" + e.getMessage() + "\"}",
					HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}

	// fetch all movies from db
	@GetMapping(path = "/fetchallmovies")
	public ResponseEntity<?> fetchAllMovies( final HttpServletRequest req) {

		Claims claim =(Claims) req.getAttribute("claims");
		String userId = claim.getSubject();
		ResponseEntity<?> responseEntity;
		responseEntity = new ResponseEntity<>(movieService.fetchAllMovies(userId), HttpStatus.OK);
		return responseEntity;
	}

	// delete movie from db by getting id
	@DeleteMapping(path = "/{movieId}")
	public ResponseEntity<?> deleteMovieById(@PathVariable("movieId") final String movieId,final HttpServletRequest req) {
		ResponseEntity<?> responseEntity;
		try {
			Claims claim =(Claims) req.getAttribute("claims");
			String userId = claim.getSubject();
			movieService.deleteByMovieIdAndUserId(movieId,userId);
			responseEntity = new ResponseEntity<Object>("{\"message\":\"Succesfully deleted\"}", HttpStatus.OK);
		} catch (MovieNotFoundException e) {
			// TODO Auto-generated catch block
			responseEntity = new ResponseEntity<String>("{ \"message\" : \"" + e.getMessage() + "\"}",
					HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}


}
