import { Component, OnInit, Input } from '@angular/core';
import { MovieServiceService } from '../../services/movie-service.service';
import { ActivatedRoute } from '@angular/router';
import { Movie } from '../../models/movie';

@Component({
  selector: 'movie-tmdb-container',
  templateUrl: './tmdb-container.component.html',
  styleUrls: ['./tmdb-container.component.css']
})
export class TmdbContainerComponent implements OnInit {


  @Input()
  movies: Array<Movie>;

  movieType: string;

  constructor(private movieService: MovieServiceService, private route: ActivatedRoute) {
    this.movies = [];
  }

  ngOnInit() {
    this.route.data.subscribe((res) => {
      this.movieType = res.movieType;

    });

    this.movieService.getMovies(this.movieType, 1).subscribe((movie) => {
      this.movies.push(...movie);
    });
  }

}
