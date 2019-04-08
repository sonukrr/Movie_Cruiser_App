import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from './modules/authentication/services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardGuard implements CanActivate {

  constructor(private route:Router, private authService:AuthService){}
  canActivate(){
    if(!this.authService.isTokenExpired()){
      return true;
    }
    this.route.navigate(['/login']);
    return false;
  }
}
