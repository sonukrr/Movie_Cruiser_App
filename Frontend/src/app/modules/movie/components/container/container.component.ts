import { Component, OnInit, Input } from '@angular/core';
import { Movie } from 'src/app/modules/movie/models/movie';
import { MovieServiceService } from '../../services/movie-service.service';
import { ActivatedRoute } from '@angular/router';
import { MatSnackBar } from '@angular/material';


@Component({
  selector: 'movie-container',
  templateUrl: './container.component.html',
  styleUrls: ['./container.component.css']
})
export class ContainerComponent implements OnInit {


  @Input()
  useWatchlistApi: boolean;

  @Input()
  movies: Array<Movie>;



  constructor(private movieService: MovieServiceService, private snackBar: MatSnackBar) { }
  ngOnInit() {

    console.log(this.movies);

  }

  addToWatchList(movie) {

    let message = `${movie.title}  added to watchlist`;
    this.movieService.saveWatchListMovies(this.movieService.transformMovieForDb(movie)).subscribe((res) => {
      this.snackBar.open(message, '', {
        duration: 1000
      });

    });
  }

  deleteFromWatchlist(movie) {
    this.movieService.deleteFromMyWatchlist(movie).subscribe((res) => {

      let message = `${movie.title} deleted from Watchlist`;
      this.snackBar.open(message, '', {
        duration: 1000
      });

      this.movies.forEach((element, index) => {
        if (element.id == movie.id) {
          this.movies.splice(index, 1);
        }
      });
    });
  }


}
