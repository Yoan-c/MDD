import { Component, ElementRef, Input, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { CommentService } from '../service/comment.service';
import { Subscription, mergeMap} from 'rxjs';
import { Comment } from '../interface/comment.interface';
import { UserService } from 'src/app/shared/service/user.service';
import { User } from 'src/app/shared/interface/user.interface';

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.scss']
})
export class CommentComponent implements OnInit, OnDestroy{

  @Input() idPost!: string;
  @ViewChild('commentContain') commentContain: ElementRef | undefined;
  comments: Comment[] = [];
  comments$!: Subscription;
  comment: Comment = {};
  onError : Boolean = false;
  user!: User;
  constructor(
    private commentService: CommentService,
    private userService : UserService
  ){}


  ngOnInit(): void {
     this.comments$ =  this.userService.user$.pipe(
      mergeMap(user => {
        this.user = user;
        return this.commentService.getCommentByIdTopic(this.idPost);
      })
     )
     .subscribe({
      next : (comments) => this.comments = comments
     });
  }

  ngOnDestroy(): void {
    this.comments$.unsubscribe();
  }

  onClick() {
    if (!this.commentContain?.nativeElement.value)
      return;
    this.comment.comment = this.commentContain?.nativeElement.value;
    this.comment.idPost = this.idPost;
    this.comment.idUser = this.user.pseudo;
    this.commentService.postComment(this.comment).subscribe({
      next: () => {
        this.comments.push(this.comment);
        this.comment = {};
        this.commentContain!.nativeElement.value = "";
      },
      error: () => this.onError = true
    });
  }
}
