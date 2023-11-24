import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Subscription, tap } from 'rxjs';
import { Topic } from 'src/app/shared/interface/topic.interface';
import { TopicService } from 'src/app/shared/service/topic.service';
import { PostItem } from '../interface/post-item.interface';
import { PostService } from '../service/post-item.service';
import { Router } from '@angular/router';
import { UserService } from 'src/app/shared/service/user.service';
import { User } from 'src/app/shared/interface/user.interface';

@Component({
  selector: 'app-post-form',
  templateUrl: './post-form.component.html',
  styleUrls: ['./post-form.component.scss']
})
export class PostFormComponent implements OnInit, OnDestroy {
  
  topic!: Topic[];
  topic$!: Subscription;
  post$!: Subscription;
  postFormGroup = new FormGroup({});
  theme = new FormControl("");
  postTitle = new FormControl("");
  postContain = new FormControl("");
  post: PostItem = {};
  user!: User;

  constructor(
    private topicService: TopicService,
    private postService : PostService,
    private router : Router,
  ){}
  
  ngOnInit(): void {
    this.postFormGroup.addControl('theme', this.theme);
    this.postFormGroup.addControl('title', this.postTitle);
    this.postFormGroup.addControl('contain', this.postContain);

    this.topic$ = this.topicService.getTopic().pipe(
      tap(tabTopic => this.topic = tabTopic)
    ).subscribe();

  }
  ngOnDestroy(): void {
    this.topic$.unsubscribe();
    if (this.post$)
      this.post$.unsubscribe();
  }

  onSubmit(): void{
    this.post.title = this.postTitle.value!;
    this.post.post = this.postContain.value!;
    this.post.idTopic = this.theme.value!;
    this.post.idComment = [];
    this.post.created_at = new Date();
    this.post.updated_at = new Date();
    if (this.postFormGroup.valid){
      this.post$ = this.postService.create(this.post).subscribe({
        next : () => this.router.navigate(['/post'])
      });
    }
  }
}
