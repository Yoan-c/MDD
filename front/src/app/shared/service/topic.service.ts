import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Topic } from "../interface/topic.interface";
import { Observable } from "rxjs";

@Injectable({
    providedIn: 'root',
})
export class TopicService {

    constructor(private http: HttpClient){}

    private apiUrl = 'http://localhost:8080/api'

    getTopic(): Observable<Topic[]> {
        return this.http.get<Topic[]>(`${this.apiUrl}/topic`)
    }

    getTopicById(id : string): Observable<Topic> {
        return this.http.get<Topic>(`${this.apiUrl}/topic/${id}`)
    }
}