import { Component, OnInit, Inject } from '@angular/core';
import { Movie } from '../../models/movie';
import { MatSnackBar, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { MovieServiceService } from '../../services/movie-service.service';


@Component({
  selector: 'movie-movie-dialog',
  templateUrl: './movie-dialog.component.html',
  styleUrls: ['./movie-dialog.component.css']
})
export class MovieDialogComponent implements OnInit {


  movie: Movie;
  comments: string;
  actionType: string;

  constructor(public snackBar: MatSnackBar, public dialogRef: MatDialogRef<MovieDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, private movieService: MovieServiceService) {
    this.comments = data.obj.comments;
    this.movie = data.obj;
    this.actionType = data.actionType;
  }

  ngOnInit() {

  }
  onNoClick() {
    this.dialogRef.close();
  }


  updateComments() {
    this.movie.comments = this.comments;
    let updatedMovie = this.movieService.transformMovieForDb(this.movie);

    this.movieService.updateComments(updatedMovie).subscribe((res) => {
      this.snackBar.open("Movie updated to watchlist successfully", "", {
        duration: 2000,
      });
    });
    this.dialogRef.close();
  }

}
