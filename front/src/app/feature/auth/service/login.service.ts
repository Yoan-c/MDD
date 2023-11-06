import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { Observable } from "rxjs";
import { Login } from "../interface/login.interface";


@Injectable()
export class LoginService {

    constructor(private http: HttpClient){}

    private apiUrl = 'http://localhost:8080/api/auth'

    login(login: Login): Observable<{token : string}> {
        return this.http.post<{token : string}>(`${this.apiUrl}/login`, login);
    }

}