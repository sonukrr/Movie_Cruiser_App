import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Movie } from '../models/movie';
import { Observable } from 'rxjs';
import { retry } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class MovieServiceService {

  tmdbEndpoint: string;
  imagePrefix: string;
  apiKey: string;
  endpoint: string;
  watchListEndpoint: string;
  springEndPoint: string;
  search: string;


  constructor(private http: HttpClient) {
    this.tmdbEndpoint = "https://api.themoviedb.org/3/movie";
    this.imagePrefix = 'https://image.tmdb.org/t/p/w500';
    this.apiKey = "api_key=91a758e92e998595c89a752855767fca";
    // this.watchListEndpoint = "http://localhost:8000/watchlist";
    this.springEndPoint = "http://localhost:8000/api/v1/movieservice/movie";
    this.search = "http://api.themoviedb.org/3/search/movie?";
  }

  getMovies(type: string, page: number): Observable<Array<Movie>> {
    this.endpoint = `${this.tmdbEndpoint}/${type}?${this.apiKey}&page=${page}`;
    return this.http.get(this.endpoint).pipe(
      retry(3),
      map(this.pickMovieResults), map(this.transformPosterPath.bind(this))
    );
  }

  saveWatchListMovies(movie: any) {

    return this.http.post(this.springEndPoint, movie);
  }

  getMyWatchList(): Observable<Array<Movie>> {

    const url = `${this.springEndPoint}/fetchallmovies`;
    return this.http.get<Array<Movie>>(url);

  }

  updateComments(movie: any) {
    const url = `${this.springEndPoint}`;
    return this.http.put(this.springEndPoint, movie);
  }

  deleteFromMyWatchlist(movie: Movie): any {
    const url = `${this.springEndPoint}/`;
    return this.http.delete(url + movie.id);
  }

  searchMovies(searchKey: string): Observable<Array<Movie>> {

    const url = `${this.search}${this.apiKey}&language=en-US&page=1&include_adult=false&query=${searchKey}`;
    if (searchKey.length > 0) {
      return this.http.get(url).pipe(
        retry(3), map(this.pickMovieResults), map(this.transformPosterPath.bind(this))
      );
    }

  }

  transformPosterPath(movies): Array<Movie> {
    return movies.map(movie => {
      movie.poster_path = `${this.imagePrefix}${movie.poster_path}`;
      return movie;
    });
  }

  pickMovieResults(response) {
    return response.results;
  }

  transformMovieForDb(movie: Movie): any {

    let movieAdded = `{ "movieId":"${movie.id}","name":"${movie.title}","comments":"${movie.comments}","releaseDate":"${movie.release_date}","posterPath":"${movie.poster_path}"}`
    return JSON.parse(movieAdded);
  }

  transformMoviesForUi(response): Array<Movie> {
    let movie: Movie;
    let movies: Array<Movie> = [];
    response.forEach(element => {
      movie = new Movie();
      movie.id = element.movieId;
      movie.title = element.name;
      movie.overview = element.comments;
      movie.poster_path = element.posterPath;
      movie.release_date = element.releaseDate;
      movies.push(movie);
    });
    return movies;
  }


}
