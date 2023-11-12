import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { User } from "../interface/user.interface";
import { BehaviorSubject, Observable, tap } from "rxjs";
import { Topic } from "../interface/topic.interface";

@Injectable({
    providedIn: 'root',
})
export class UserService {

    private apiUrl = 'http://localhost:8080/api/user';
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
                this.userSubject.next(user);
                localStorage.setItem('user', JSON.stringify(user));
            })
        )
    }

    sub(id: string): Observable<void> {
        return this.http.patch<void>(`${this.apiUrl}/sub/${id}`, "");
    }

    unSubscribeUser(id: string): Observable<void> {
        return this.http.patch<void>(`${this.apiUrl}/unsub/${id}`, "");
    }

    getAllTopicByUser(): Observable<Topic[]> {
        return this.http.get<Topic[]>(`${this.apiUrl}/topic`);
    }

    updateMe(pseudo: string, email: string, password: string, confirmPassword: string): Observable<{token : string}>{
        const user = {
            pseudo, email, password, confirmPassword
        };
        return this.http.patch<{token : string}>(`${this.apiUrl}/me`, user);
    }
}