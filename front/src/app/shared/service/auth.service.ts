import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { Observable } from "rxjs";

@Injectable({
    providedIn: 'root',
})
export class AuthService {
    private isAuthenticate = false;
    private apiUrl = 'http://localhost:8080/api/auth'

    constructor(private http : HttpClient, private router : Router){}

    getIsAuthenticated(){
        return this.isAuthenticate;
    }

    setIsAuthenticated(isAuthenticated: boolean){
        this.isAuthenticate = isAuthenticated;
    }

    getToken(){
        return localStorage.getItem('jwt');
    }
    
    setToken(token: string){
        localStorage.setItem('jwt', token);
    }

    isLoggued(){
        let token = localStorage.getItem('jwt');
        return token !== null ? true : false;
    }


    logout(): Observable<void> {
        localStorage.removeItem('jwt');
        localStorage.removeItem('user')
        return this.http.get<void>(`${this.apiUrl}/logout`)
    }

    login(token : string) {
        this.setToken(token);
    }
}