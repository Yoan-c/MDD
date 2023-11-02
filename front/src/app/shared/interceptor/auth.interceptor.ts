import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { AuthService } from "../service/auth.service";

@Injectable({
    providedIn: 'root',
})
export class AuthInterceptor implements HttpInterceptor {
    
    constructor(private authService: AuthService){}

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        if (req.url.includes('login') || req.url.includes('register'))
            return next.handle(req)
        const token = this.authService.getToken();
        req = req.clone({
            setHeaders : {
                Authorization: 'Bearer ' + token
            }
        })
        return next.handle(req)
    }
    
}