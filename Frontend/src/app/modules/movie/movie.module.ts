import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HelloWorldComponent } from './components/hello-world/hello-world.component';
import { ThumbnailComponent } from './components/thumbnail/thumbnail.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { ContainerComponent } from './components/container/container.component';
import { MatButtonModule, MatCheckboxModule, MatCardModule, MatSnackBarModule, MatFormFieldModule, MatInputModule, MatToolbarModule, MatDialogModule } from '@angular/material';
import { TmdbContainerComponent } from './components/tmdb-container/tmdb-container.component';
import { WatchlistComponent } from './components/watchlist/watchlist.component';
import { MovieDialogComponent } from './components/movie-dialog/movie-dialog.component';
import { FormsModule } from '@angular/forms';
import { SearchComponent } from './components/search/search.component';
import { InterceptorService } from './services/interceptor.service';
import { MovieServiceService } from './services/movie-service.service';

@NgModule({
  declarations:
    [
      HelloWorldComponent,
      ThumbnailComponent,
      ContainerComponent,
      TmdbContainerComponent,
      WatchlistComponent,
      MovieDialogComponent,
      SearchComponent

    ],

  imports:
    [
      HttpClientModule,
      CommonModule,
      FormsModule,
      MatCardModule,
      MatButtonModule,
      MatSnackBarModule,
      MatFormFieldModule,
      MatInputModule,


    ],

  exports: [
    HelloWorldComponent,
    ThumbnailComponent
  ],

  providers: [
    MovieServiceService,
    {provide:HTTP_INTERCEPTORS,
    useClass:InterceptorService,
  multi :true}
  ],

  entryComponents: [MovieDialogComponent],

})
export class MovieModule { }
