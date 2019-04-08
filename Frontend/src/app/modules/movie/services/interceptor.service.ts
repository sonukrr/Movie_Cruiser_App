import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from '../../authentication/services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class InterceptorService implements HttpInterceptor {

  constructor(private auth : AuthService) { }

  intercept(request: HttpRequest<any>, next: HttpHandler):Observable<HttpEvent<any>>{

    request = request.clone({
setHeaders: {
  Authorization: `Bearer ${this.auth.getToken()}`
}
    });
    return next.handle(request);
    
  }
}
