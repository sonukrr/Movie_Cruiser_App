package com.stackroute.moviecruiserserverapplication.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.moviecruiserserverapplication.domain.Movie;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Transactional
public class MovieRepositoryTest {

	@Autowired
	private transient MovieRepository repo;

	public void setRepo(final MovieRepository repo) {
		this.repo = repo;
	}

	private transient Movie movie;

	Optional<Movie> options;

	@Before
	public void setUp() {
		movie = new Movie(0, "123456", "Pirates of Caribean", "must watch adventure movie", "posterpath unknown",
				"20/5/2014", 45.3, 112, "sonukrr");
		options = Optional.of(movie);
	}

	@After
	public void tearDown() {
		repo.deleteAllInBatch();
	}

	@Test
	public void testSaveMovie() {
		final Movie savedMovie = repo.save(movie);
		final Movie movie = repo.findById(savedMovie.getId()).get();
		assertThat(movie.getId()).isEqualTo(savedMovie.getId());
	}

	@Test
	public void tetsUpdateMovie() {
		final Movie savedMovie = repo.save(new Movie(0, "1", "Pirates of Caribean", "must watch adventure movie", "posterpath unknown",
				"20/5/2014", 45.3, 112, "100"));
		final Movie movie = repo.findById(savedMovie.getId()).get();
		assertEquals(movie.getName(), "Pirates of Caribean");
		movie.setName("POC returns");
		movie.setComments("Hi");
		final Movie tempMovie = repo.findById(savedMovie.getId()).get();
		assertEquals("Hi", tempMovie.getComments());
	}

	@Test
	public void testDeleteMovie() {
		final Movie savedMovie = repo.save(new Movie(0, "1", "Pirates of Caribean", "must watch adventure movie", "posterpath unknown",
				"20/5/2014", 45.3, 112, "100"));
		final Movie movie = repo.findById(savedMovie.getId()).get();
		assertEquals(movie.getName(), "Pirates of Caribean");
		repo.delete(movie);
		assertThat(repo.existsById(movie.getId())).isEqualTo(false);
	}

	@Test
	public void testGetMovie() {
		final Movie savedMovie = repo.save(new Movie(0, "1", "Pirates of Caribean", "must watch adventure movie", "posterpath unknown",
				"20/5/2014", 45.3, 112, "100"));

		final Movie movie = repo.findById(savedMovie.getId()).get();
		final Optional<Movie> option = repo.findByMovieIdAndUserId("1", "100");
		Movie fetchedMovie = option.get();
		assertEquals(movie.getName(), fetchedMovie.getName());

	}

	@Test
	public void testGetAllMovies() {
		repo.save(new Movie(0, "1", "Pirates of Caribean", "must watch adventure movie", "posterpath unknown",
				"20/5/2014", 45.3, 112, "100"));
		repo.save(new Movie(2, "2", "Hulk the great", "must watch adventure movie", "posterpath unknown", "20/5/2014",
				45.3, 112, "180"));
		repo.save(new Movie(3, "3", "Pirates of Caribean", "must watch adventure movie", "posterpath unknown",
				"20/5/2014", 45.3, 112, "190"));
		List<Movie> movieList = repo.findAll();
		assertEquals(movieList.get(0).getName(), "Pirates of Caribean");
	}
}
