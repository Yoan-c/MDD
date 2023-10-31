import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription, tap } from 'rxjs';
import { PostService } from '../service/post-item.service';
import { ErrorApp } from 'src/app/shared/service/errorApp.service';
import { PostItem } from '../interface/post-item.interface';

@Component({
  selector: 'app-post-item',
  templateUrl: './post-item.component.html',
  styleUrls: ['./post-item.component.scss']
})
export class PostItemComponent implements OnInit, OnDestroy{
  
  post$! : Subscription
  postItem! : PostItem[]
  constructor(private postService : PostService, private errorApp : ErrorApp){}

  ngOnInit(): void {
    this.post$ = this.postService.getPost().pipe(
      tap(postItem => {
        console.log(postItem)
        this.postItem = postItem
      }
      )
    ).subscribe({
      error : err => this.errorApp.checkError(err)
      
    });  
  }

  ngOnDestroy(): void {
    this.post$.unsubscribe();
  }

}
