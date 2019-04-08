import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginComponent } from './login.component';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule, MatInputModule, MatButtonModule } from '@angular/material';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterTestingModule } from '@angular/router/testing';
import { By } from '@angular/platform-browser';
import { Location } from '@angular/common';
import { Observable } from 'rxjs';
import { of } from 'rxjs';

const testConfig = {
  userData: {
    firstName: 'test',
    lastName: 'testLast',
    userId: 'testUser',
    password: 'testPass'
  }
}

fdescribe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let authService: AuthService;
  let spyUser: any;
  let routes: Router;
  let location: Location;

  class AuthServiceStub {
    currentUser: any;
    
    constructor() {

     }

    login(credentials) {
      if(credentials.userId == testConfig.userData.userId) {
        console.log('data:::', this.currentUser);
        return of(credentials.userId);
      } else {
        return of(false);
      }
    }
  }

  class dummy {

  }

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LoginComponent ],
      imports: [FormsModule, MatFormFieldModule, MatInputModule,
        MatButtonModule, BrowserAnimationsModule, HttpClientModule,
        RouterTestingModule.withRoutes(
          [{path: '', component: dummy}]
        )
      ],
      providers: [{provide: AuthService, useClass: AuthServiceStub}]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    routes = TestBed.get(Router);
    fixture = TestBed.createComponent(LoginComponent);
    location = TestBed.get(Location);
    component = fixture.componentInstance;
    fixture.detectChanges();
    fixture.debugElement.injector.get(AuthService);
  });

  it('should create app component', async(() => {
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  }));

  it('should contain two input box for userId and password', () => {
    let userId = fixture.debugElement.query(By.css('.inputId'));
    let password = fixture.debugElement.query(By.css('.inputPass'));
    let registerButton = fixture.debugElement.query(By.css('.register-button'));
    let userButton = fixture.debugElement.query(By.css('.login-user'));

    let userIdInput = userId.nativeElement;
    let passwordInput = password.nativeElement;
    let registerButtonInput = registerButton.nativeElement;
    let userButtonInput = userButton.nativeElement;

    expect(userIdInput).toBeTruthy();
    expect(passwordInput).toBeTruthy();
    expect(registerButtonInput).toBeTruthy();
    expect(userButtonInput).toBeTruthy();
  });

  it('should redirect to login if registered successfully', async(() => {
    let userId = fixture.debugElement.query(By.css('.inputId'));
    let password = fixture.debugElement.query(By.css('.inputPass'));
    let userButton = fixture.debugElement.query(By.css('.login-user'));

    let userIdInput = userId.nativeElement;
    let passwordInput = password.nativeElement;
    let userButtonInput = userButton.nativeElement;
    fixture.detectChanges();
    fixture.whenStable().then(() => {
      userIdInput.value = 'testuser';
      passwordInput.value = 'testpass';
      userIdInput.dispatchEvent(new Event('input'));
      passwordInput.dispatchEvent(new Event('input'));
      userButtonInput.click();
    }).then(() => {
      expect(location.path()).toBe('');
    });
  }));
});

