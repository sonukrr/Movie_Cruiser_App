import { Component, OnInit } from '@angular/core';
import { MovieServiceService } from '../../services/movie-service.service';
import { Movie } from '../../models/movie';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'movie-watchlist',
  templateUrl: './watchlist.component.html',
  styleUrls: ['./watchlist.component.css']
})
export class WatchlistComponent implements OnInit {

  movies: Array<Movie>;
  useWatchlistApi: boolean = true;

  constructor(private movieService: MovieServiceService, private snackBar: MatSnackBar) {
    this.movies = [];
  }


  ngOnInit() {
    let message = "Watch list is empty";
    let movie: Movie;
    this.movieService.getMyWatchList().subscribe((res) => {


      if (res.length === 0) {
        this.snackBar.open(message, '', {
          duration: 1000
        });
      } else
        this.movies = this.movieService.transformMoviesForUi(res);
    });

  }

}
