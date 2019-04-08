import { Component, OnInit } from '@angular/core';
import { MovieServiceService } from '../../services/movie-service.service';
import { Movie } from '../../models/movie';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  movies: Array<Movie>;
  constructor(private movieService: MovieServiceService) {
    this.movies = [];
  }

  ngOnInit() {
  }

  onEnter(searchKey) {
    this.movieService.searchMovies(searchKey).subscribe((res) => {
      this.movies = res;
    });
  }
}
