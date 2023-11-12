import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription, mergeMap } from 'rxjs';
import { PostService } from '../service/post-item.service';
import { PostItem } from '../interface/post-item.interface';
import { TopicService } from 'src/app/shared/service/topic.service';
import { Topic } from 'src/app/shared/interface/topic.interface';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.scss']
})
export class PostComponent implements OnInit, OnDestroy {
  
  postItem!: PostItem;
  postItem$! : Subscription;
  topic!: Topic;

  constructor(
    private route: ActivatedRoute,
    private postService : PostService,
    private topicService: TopicService  
  ){}


  ngOnInit(): void {
    this.postItem$ = this.route.queryParams.pipe(
      mergeMap(param => {
        return this.postService.getPostById(param['id']);
      }),
      mergeMap(post => {
        this.postItem = post;      
        return this.topicService.getTopicById(post.idTopic!);
      })
    ).subscribe({
      next: (topic : Topic) => this.topic = topic
    });
    
  }
  ngOnDestroy(): void {
    this.postItem$.unsubscribe();
  }

}
