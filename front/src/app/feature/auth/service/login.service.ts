import { Injectable } from "@angular/core";
import { HttpClient} from '@angular/common/http';
import { Observable } from "rxjs";
import { login } from "../interface/login.interface";

@Injectable()
export class LoginService {

    constructor(private http: HttpClient){}

    private apiUrl = 'http://localhost:8080/api/auth'

    login(login: login): Observable<String> {
        return this.http.post<string>(`${this.apiUrl}/login`, login)
    }

}