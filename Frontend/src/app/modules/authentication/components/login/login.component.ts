import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  newUser:User;

  constructor(private authService:AuthService, private router : Router) { 
    this.newUser= new User();
  }

  ngOnInit() {
  }

  loginUser(){
    this.authService.loginUser(this.newUser).subscribe((res)=>{
      if(res.token)
      {
        this.authService.setToken(res.token);
        this.router.navigate(['/movies/popular']);
      }
    });
  }

}
