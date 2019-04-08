package com.stackroute.moviecruiserserverapplication.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "movie")
public class Movie {
	// do not write auto_generated else hibernate_sequence table will get
	// created.Hibernate v5.0 handles auto increment itself
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="movie_id")
	private String movieId;
	
	@Column(name = "name")
	private String name;

	@Column(name = "comments")
	private String comments;

	@Column(name = "poster_path")
	private String posterPath;

	@Column(name = "release_date")
	private String releaseDate;

	@Column(name = "vote_average")
	private double voteAverage;

	@Column(name = "vote_count")
	private int voteCount;
	
	@Column(name = "user_id")
	private String userId;

	public Movie() {
		super();
	}

	public Movie(int id, String movieId, String name, String comments, String posterPath, String releaseDate,
			double voteAverage, int voteCount, String userId) {
		super();
		this.id = id;
		this.movieId = movieId;
		this.name = name;
		this.comments = comments;
		this.posterPath = posterPath;
		this.releaseDate = releaseDate;
		this.voteAverage = voteAverage;
		this.voteCount = voteCount;
		this.userId = userId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getPosterPath() {
		return posterPath;
	}

	public void setPosterPath(String posterPath) {
		this.posterPath = posterPath;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public double getVoteAverage() {
		return voteAverage;
	}

	public void setVoteAverage(double voteAverage) {
		this.voteAverage = voteAverage;
	}

	public int getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + id;
		result = prime * result + ((movieId == null) ? 0 : movieId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((posterPath == null) ? 0 : posterPath.hashCode());
		result = prime * result + ((releaseDate == null) ? 0 : releaseDate.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		long temp;
		temp = Double.doubleToLongBits(voteAverage);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + voteCount;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (id != other.id)
			return false;
		if (movieId == null) {
			if (other.movieId != null)
				return false;
		} else if (!movieId.equals(other.movieId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (posterPath == null) {
			if (other.posterPath != null)
				return false;
		} else if (!posterPath.equals(other.posterPath))
			return false;
		if (releaseDate == null) {
			if (other.releaseDate != null)
				return false;
		} else if (!releaseDate.equals(other.releaseDate))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (Double.doubleToLongBits(voteAverage) != Double.doubleToLongBits(other.voteAverage))
			return false;
		if (voteCount != other.voteCount)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", movieId=" + movieId + ", name=" + name + ", comments=" + comments
				+ ", posterPath=" + posterPath + ", releaseDate=" + releaseDate + ", voteAverage=" + voteAverage
				+ ", voteCount=" + voteCount + ", userId=" + userId + "]";
	}

	
}
