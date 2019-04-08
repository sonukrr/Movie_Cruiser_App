import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MovieRouterModule } from './modules/movie/movie-routing.module';
import { userRouterModule } from './modules/authentication/auth-routing.module';

const appRoutes: Routes = [
  {path:'',
  redirectTo:'login',
  pathMatch: 'full'  
  }
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes),MovieRouterModule,userRouterModule],
  exports: [RouterModule]
})
export class AppRoutingModule { }
