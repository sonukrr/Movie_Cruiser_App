import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Movie } from '../../models/movie';
import { ArrayType } from '@angular/compiler/src/output/output_ast';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { MovieServiceService } from '../../services/movie-service.service';
import { MatSnackBarModule, MatSnackBar, MatDialog } from '@angular/material';
import { MovieDialogComponent } from '../movie-dialog/movie-dialog.component';


@Component({
  selector: 'movie-thumbnail',
  templateUrl: './thumbnail.component.html',
  styleUrls: ['./thumbnail.component.css']
})
export class ThumbnailComponent implements OnInit {

  @Input()
  movie: Movie;

  @Input()
  useWatchlistApi: boolean;

  @Output()
  deleteMovie = new EventEmitter();

  @Output()
  addMovie = new EventEmitter();

  @Output()
  updateMovie = new EventEmitter();

  constructor(private movieService: MovieServiceService, private snackBar: MatSnackBar, private http: HttpClientModule, private dialog: MatDialog) {
 
  }

  ngOnInit() {

  }

  addToWatchList() {
    this.addMovie.emit(this.movie);

  }

  deleteFromWatchList() {
    this.deleteMovie.emit(this.movie);

  }


  updateFromWatchList(actionType) {
    let dialogRef = this.dialog.open(MovieDialogComponent, {

      width: '400px',
      data: { obj: this.movie, actionType: actionType }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log("Dialog was closed");
    });


  }

}
