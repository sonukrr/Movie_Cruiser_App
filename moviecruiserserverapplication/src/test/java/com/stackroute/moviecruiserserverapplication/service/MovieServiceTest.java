package com.stackroute.moviecruiserserverapplication.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.moviecruiserserverapplication.MovieCruiserServerApplicationApplication;
import com.stackroute.moviecruiserserverapplication.domain.Movie;
import com.stackroute.moviecruiserserverapplication.exception.MovieAlreadyExistException;
import com.stackroute.moviecruiserserverapplication.exception.MovieNotFoundException;
import com.stackroute.moviecruiserserverapplication.repository.MovieRepository;


@RunWith(SpringRunner.class)
@SpringBootTest(classes=MovieCruiserServerApplicationApplication.class)
public class MovieServiceTest {

	
	@InjectMocks
	private transient MovieServiceImpl movieService;
	
	@Mock
	private transient MovieRepository movieRepo;
	
	private transient Movie movie;
	private transient List<Movie> moviesList = new ArrayList<>();
	
	transient Optional<Movie> options;
	
	@Before
	public void setUpMocks() {
		MockitoAnnotations.initMocks(this);
		movie = new Movie(1,"1", "Pirates of Caribean", "must watch adventure movie", "posterpath unknown", "20/5/2014",45.3,112,"100");
		moviesList.add(new Movie(2,"2", "A quiet place", "must watch horror movie", "posterpath unknown", "20/5/2014",45.3,112,"100"));
		moviesList.add(new Movie(3,"3","Dracula Untold", "must watch dracula movie", "posterpath unknown", "20/5/2014",45.3,112,"100"));
		options = Optional.of(movie);
	}
	
	@Test
	public void testMockCreation() {
		assertNotNull("Failure in injecting mocks, use proper annotations", movie);
	}
	
	@Test
	public void testSaveNewMovieSuccess() throws MovieAlreadyExistException {
		when(movieRepo.save(movie)).thenReturn(movie);
		boolean flag= movieService.saveMovie(movie);
		assertTrue("Creation of new movie failed",flag);
		verify(movieRepo, times(1)).save(movie);
		
	}
	
	@Test(expected = MovieAlreadyExistException.class)
	public void testSaveNewMovieFailure() throws MovieAlreadyExistException {
		when(movieRepo.findById(1)).thenReturn(options);
		when(movieRepo.save(movie)).thenReturn(movie);
		final boolean flag = movieService.saveMovie(movie);
		assertFalse("Saving movie failed",flag);
		verify(movieRepo, times(1)).findById(1);

	}
	@Test
	public void testGetMovieById() throws MovieNotFoundException {
		when(movieRepo.findByMovieIdAndUserId("1", "100")).thenReturn(options);
		final Movie fetchedMovie = movieService.getMovieById("1","100");
		assertEquals("Fetching movie by Id failed",movie,fetchedMovie);
		verify(movieRepo, times(1)).findByMovieIdAndUserId("1", "100");
	}
	
	@Test
	public void testUpdateMovie() throws MovieNotFoundException {
		movieRepo.save(movie);
		when(movieRepo.findByMovieIdAndUserId("1", "100")).thenReturn(options);
		when(movieRepo.save(movie)).thenReturn(movie);
		Movie updatedMovie = movieService.updateMovie(movie);
		assertEquals(movie, updatedMovie);
		verify(movieRepo,times(2)).save(movie);
		verify(movieRepo,times(1)).findByMovieIdAndUserId("1", "100");
	}
	
	@Test
	public void testFetchAllMovies() {
		List<Movie> fetchedMovieList = new ArrayList<>();
		when(movieRepo.findByUserId("100")).thenReturn(moviesList);
		fetchedMovieList = movieService.fetchAllMovies("100");
		assertThat(fetchedMovieList).hasSize(2);
		verify(movieRepo, times(1)).findByUserId("100");
	}
	
	@Test
	public void testDeleteMovieById() throws MovieNotFoundException {
		when(movieRepo.findById(1)).thenReturn(options);
		doNothing().when(movieRepo).deleteById(1);
		boolean flag = movieService.deleteMovieByid(1);
		assertTrue("deleting movie failed", flag);
		verify(movieRepo,times(1)).deleteById(1);
		verify(movieRepo,times(1)).findById(1);
	}
	
	

}
