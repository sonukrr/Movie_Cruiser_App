package com.stackroute.moviecruiserserverapplication.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.moviecruiserserverapplication.MovieCruiserServerApplicationApplication;
import com.stackroute.moviecruiserserverapplication.domain.Movie;
import com.stackroute.moviecruiserserverapplication.service.MovieService;

//to import hasSize()
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = MovieCruiserServerApplicationApplication.class)
public class MovieControllerTest {

	@Autowired
	private transient MockMvc mockMvc;

	@InjectMocks
	private transient MovieController controller;

	@MockBean
	private transient MovieService movieService;

	private transient Movie movie;
	private transient List<Movie> moviesList = new ArrayList<>();

	transient Optional<Movie> options;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		movie = new Movie(1,"1", "Pirates of Caribean", "must watch adventure movie", "posterpath unknown", "20/5/2014",45.3,112,"100");
		moviesList.add(movie);
		moviesList.add(new Movie(2,"2", "A quiet place", "must watch horror movie", "posterpath unknown", "20/5/2014",45.3,112,"100"));
		moviesList.add(new Movie(3,"3", "Dracula Untold", "must watch dracula movie", "posterpath unknown", "20/5/2014",45.3,112,"100"));
		options = Optional.of(movie);
	}

	@Test
	public void testSaveNewMovieSuccess() {

		try {
			Mockito.when(movieService.saveMovie(movie)).thenReturn(true);
			String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzb251a3JyIiwiaWF0IjoxNTUzMzYzMzczfQ.Zt357opduLxkI_jFGIKVawdCLLuHwO75f6bUXd-r6ow";
			mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/movieservice/movie").header("authorization","Bearer "+ token).contentType(MediaType.APPLICATION_JSON)
					.content(asJsonString(movie))).andExpect(status().is2xxSuccessful())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(jsonPath("$.id", is(1))).andExpect(jsonPath("$.name", is("Pirates of Caribean")))
					.andExpect(jsonPath("$.comments", is("must watch adventure movie")))
					.andExpect(jsonPath("$.posterPath", is("posterpath unknown")))
					.andExpect(jsonPath("$.releaseDate", is("20/5/2014")));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testUpdateMovie() {

		try {
			Mockito.when(movieService.updateMovie(movie)).thenReturn(movie);

			movie.setName("POC new");
			movie.setPosterPath("found posterpath");

			mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/movieservice/movie").contentType(MediaType.APPLICATION_JSON)
					.content(asJsonString(movie))).andExpect(status().is2xxSuccessful())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(jsonPath("$.id", is(1))).andExpect(jsonPath("$.name", is("POC new")))
					.andExpect(jsonPath("$.comments", is("must watch adventure movie")))
					.andExpect(jsonPath("$.posterPath", is("found posterpath")))
					.andExpect(jsonPath("$.releaseDate", is("20/5/2014")));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testDeleteMovieById() {

		try {

			Mockito.when(movieService.deleteMovieByid(1)).thenReturn(true);
			mockMvc.perform(delete("/api/v1/movieservice/movie/{id}", 1)).andExpect(status().is2xxSuccessful())
					.andExpect(jsonPath("$.message", is("Succesfully deleted")));
			verify(movieService, times(1)).deleteMovieByid(1);
			verifyNoMoreInteractions(movieService);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testGetMovieById() {

		try {

			Mockito.when(movieService.getMovieById("1", "100")).thenReturn(movie);
			mockMvc.perform(get("/api/v1/movieservice/movie/1")).andExpect(status().is2xxSuccessful())
					.andExpect(jsonPath("$.id", is(1)));
			verify(movieService, times(1)).getMovieById("1", "100");
			verifyNoMoreInteractions(movieService);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testFetchAllMovies() {
	String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzb251a3JyIiwiaWF0IjoxNTUzMzYzMzczfQ.Zt357opduLxkI_jFGIKVawdCLLuHwO75f6bUXd-r6ow";
		try {

			Mockito.when(movieService.fetchAllMovies("100")).thenReturn(moviesList);
			mockMvc.perform(get("/api/v1/movieservice/movie/fetchallmovies").header("authorization","Bearer "+token)).andExpect(status().isOk())
					.andExpect(jsonPath("$.*", hasSize(3)));
			verify(movieService, times(1)).fetchAllMovies("100");
			verifyNoMoreInteractions(movieService);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
