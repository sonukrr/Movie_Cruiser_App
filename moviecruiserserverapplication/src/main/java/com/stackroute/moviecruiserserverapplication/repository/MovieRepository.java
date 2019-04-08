package com.stackroute.moviecruiserserverapplication.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.stackroute.moviecruiserserverapplication.domain.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

	//@Query
	List<Movie> findByUserId(String userId);

	Optional<Movie> findByMovieId(String movieId);

	Optional<Movie> findByMovieIdAndUserId(String movieId, String userId);

	@Modifying
    @Transactional
	@Query("delete from Movie where movieId=(?1) and userId = (?2)")
	void deleteByMovieIdAndUserId(String movieId, String userId); 
}
