import { HttpClient } from "@angular/common/http"
import { Injectable } from "@angular/core"
import { Observable } from "rxjs"
import { Register } from "../interface/register.inteface"

@Injectable()
export class RegisterService {

    constructor(private http: HttpClient){}

    private apiUrl = 'http://localhost:8080/api/auth'

    register(register : Register): Observable<void> {
        return this.http.post<void>(`${this.apiUrl}/register`, register)
    }

}