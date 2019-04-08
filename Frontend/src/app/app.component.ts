import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './modules/authentication/services/auth.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'moviecruiserfrontend';

  constructor(private router:Router,private authService:AuthService){};

  ngOnInit(){
  }

  logOut() {
    this.authService.deleteToken();
    this.router.navigate(['/login']);
  }
}
