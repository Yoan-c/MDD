import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription, mergeMap, tap } from 'rxjs';
import { ButtonComponent } from 'src/app/core/components/button/button.component';
import { Topic } from 'src/app/shared/interface/topic.interface';
import { User } from 'src/app/shared/interface/user.interface';
import { TopicService } from 'src/app/shared/service/topic.service';
import { UserService } from 'src/app/shared/service/user.service';

@Component({
  selector: 'app-topic-item',
  templateUrl: './topic-item.component.html',
  styleUrls: ['./topic-item.component.scss']
})
export class TopicItemComponent implements OnInit, OnDestroy {

  topics: Topic[] = [];
  topic$!: Subscription
  subscribe$!: Subscription
  user$!: Subscription
  user!: User;

  constructor(private topicService: TopicService, private userService: UserService){}

  ngOnInit(): void {
    this.topic$ = this.userService.user$.pipe(
      mergeMap(user => {
        this.user = user;
        return  this.topicService.getTopic()
      }))
      .subscribe({
        next: (topics) => this.topics = topics
      })
  }

  ngOnDestroy(): void {
    this.topic$.unsubscribe();
    if (this.subscribe$)
      this.subscribe$.unsubscribe();
  }

  onClick(id : string, appButton : ButtonComponent) {

    if (this.user.idTopic?.includes(id))
      return
    this.user.idTopic?.push(id)
    appButton.label = "Abonné"
    console.log(id);
    this.subscribe$ = this.userService.sub(id).pipe(
      mergeMap(_ => {return this.userService.getMe()})
      ).subscribe();
  }
}
