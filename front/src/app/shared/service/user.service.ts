import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { User } from "../interface/user.interface";
import { BehaviorSubject, Observable, of, tap } from "rxjs";

@Injectable({
    providedIn: 'root',
})
export class UserService {

    private apiUrl = 'http://localhost:8080/api/user'
    private userSubject = new BehaviorSubject<User>({});
    user$ = this.userSubject.asObservable();
    
    constructor(private http: HttpClient){
        const storedUser = localStorage.getItem('user');
        if (storedUser) {
            this.userSubject.next(JSON.parse(storedUser));
        }
    }
    
    getMe(): Observable<User> {
        return this.http.get<User>(`${this.apiUrl}/me`).pipe(
            tap(user => {
                this.userSubject.next(user)
                localStorage.setItem('user', JSON.stringify(user))
            })
        )
    }

    sub(id: string): Observable<void> {
        return this.http.patch<void>(`${this.apiUrl}/sub/${id}`, "")
    }
}