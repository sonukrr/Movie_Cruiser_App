import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { WatchlistComponent } from './components/watchlist/watchlist.component';
import { TmdbContainerComponent } from './components/tmdb-container/tmdb-container.component';
import { SearchComponent } from './components/search/search.component';
import { AuthGuardGuard } from '../../auth-guard.guard';
const movieRoutes: Routes = [
    {
        path: 'movies',
        children: [
            {
                path: '',
                redirectTo: '/movies/popular',
                pathMatch: 'full',
                canActivate: [AuthGuardGuard]
            },
            {
                path: 'popular',
                component: TmdbContainerComponent,
                canActivate: [AuthGuardGuard],
                data: {
                    movieType: "popular"
                }
            },
            {
                path: 'top_rated',
                component: TmdbContainerComponent,
                canActivate: [AuthGuardGuard],
                data: {
                    movieType: "top_rated"
                }
            },
            {
                path: 'watchlist',
                component: WatchlistComponent,
                canActivate: [AuthGuardGuard]

            },
            {
                path: 'search',
                component: SearchComponent,
                canActivate: [AuthGuardGuard]

            },
        ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(movieRoutes)],
    exports: [RouterModule]
})
export class MovieRouterModule { }
