import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { PostItem } from "../interface/post-item.interface";
import { Observable } from "rxjs";

@Injectable()
export class PostService {

    constructor(private http: HttpClient){}

    private apiUrl = 'http://localhost:8080/api'

    getPost(): Observable<PostItem[]> {
        return this.http.get<PostItem[]>(`${this.apiUrl}/post`)
    }

}