import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule, MatButtonModule, MatSnackBarModule, MatInputModule } from '@angular/material';
import { FormsModule } from '@angular/forms';
import { userRouterModule } from './auth-routing.module';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [LoginComponent, RegisterComponent],
  imports: [
    CommonModule,
     MatFormFieldModule,
      MatSelectModule,
       FormsModule,
        RouterModule, 
        MatFormFieldModule,
        MatButtonModule,
        MatSnackBarModule,
        MatFormFieldModule,
        MatInputModule,
  ]
})
export class AuthenticationModule { }
