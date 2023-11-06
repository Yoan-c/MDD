import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Comment } from "../interface/comment.interface";
import { Observable } from "rxjs";

@Injectable()
export class CommentService {

    constructor(private http: HttpClient){}

    private apiUrl = 'http://localhost:8080/api'

    getCommentByIdTopic(idComment: string): Observable<Comment[]>{
        return this.http.get<Comment[]>(`${this.apiUrl}/comment/${idComment}`);
    }

    postComment(comment: Comment): Observable<void> {
        return this.http.post<void>(`${this.apiUrl}/comment`, comment);
    }
}