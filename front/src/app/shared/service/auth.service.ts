import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";

@Injectable({
    providedIn: 'root',
})
export class AuthService {
    private isAuthenticate = false;

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

    logout () {
        localStorage.removeItem('jwt');
    }

    login(token : string) {
        this.setToken(token);
    }
}