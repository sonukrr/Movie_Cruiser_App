import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { User } from '../../models/user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  newUser:User;

  constructor(private authService:AuthService , private router:Router) {
    this.newUser = new User();
   }


  ngOnInit() {
  }

  registerUser(){
   this.authService.registerUser(this.newUser).subscribe((res) => {
  
    this.router.navigate(['/login']);
   });
  }


  resetInput(){
    this.newUser=null;
  }
}
