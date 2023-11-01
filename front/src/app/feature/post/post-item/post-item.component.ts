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
}
