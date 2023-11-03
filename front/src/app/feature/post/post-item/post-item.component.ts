import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription, mergeMap, tap } from 'rxjs';
import { PostService } from '../service/post-item.service';
import { ErrorApp } from 'src/app/shared/service/errorApp.service';
import { PostItem } from '../interface/post-item.interface';
import { Router } from '@angular/router';
import { UserService } from 'src/app/shared/service/user.service';
import { User } from 'src/app/shared/interface/user.interface';

@Component({
  selector: 'app-post-item',
  templateUrl: './post-item.component.html',
  styleUrls: ['./post-item.component.scss']
})
export class PostItemComponent implements OnInit, OnDestroy{
  
  post$! : Subscription
  postItem! : PostItem[]
  user!: User | null;
  sortPostAsc : boolean = true;
  arrow: string = "&#x2B73;"

  constructor(
    private postService : PostService,
    private errorApp : ErrorApp,
    private router: Router,
    private userService: UserService
  ){}

  ngOnInit(): void {
    this.post$ = this.userService.user$.pipe(
      mergeMap(user => {
        return this.postService.getPost(user.idTopic)
      })
    ).subscribe({
      next : postItem => this.postItem = postItem,
      error : err => this.errorApp.checkError(err)
    })
  }

  ngOnDestroy(): void {
    this.post$.unsubscribe();
  }

  create(label : string) {
    if (label.includes("Créer"))
      this.router.navigate(['post/create'])
  }

  sortPost(){
    if (this.sortPostAsc){
      this.sortPostAsc = !this.sortPostAsc
      this.postItem = this.postItem.sort(this.sortPostByCreatedAsc)
      this.arrow = "&#x2B71;"
      return
    }
    this.arrow = "&#x2B73;"
    this.sortPostAsc = !this.sortPostAsc
    this.postItem = this.postItem.sort(this.sortPostByCreatedDesc)
  }

  sortPostByCreatedAsc(postItem : PostItem, newPostItem : PostItem) {
    return new Date(newPostItem.created_at!).getMilliseconds() - new Date(postItem.created_at!).getMilliseconds()
  }

  sortPostByCreatedDesc(postItem : PostItem, newPostItem : PostItem) {
    return new Date(postItem.created_at!).getMilliseconds() - new Date(newPostItem.created_at!).getMilliseconds()
  }
}
