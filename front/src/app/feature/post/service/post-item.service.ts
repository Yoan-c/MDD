import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { PostItem } from "../interface/post-item.interface";
import { Observable } from "rxjs";

@Injectable()
export class PostService {

    constructor(private http: HttpClient){}

    private apiUrl = 'http://localhost:8080/api'

    getPost(idTopics : string[] | undefined): Observable<PostItem[]> {
        const topic = {'topics' : idTopics};
        return this.http.post<PostItem[]>(`${this.apiUrl}/post/topics`, topic)
    }

    create(PostItem: PostItem): Observable<void> {
        return this.http.post<void>(`${this.apiUrl}/post`, PostItem)
    }

    getPostById(idPost: string): Observable<PostItem>{
        return this.http.get<PostItem>(`${this.apiUrl}/post/${idPost}`)
    }

}