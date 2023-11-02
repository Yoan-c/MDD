import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TopicRoutingModule } from './topic-routing.module';
import { TopicItemComponent } from './topic-item/topic-item.component';
import { CoreModule } from 'src/app/core/core.module';


@NgModule({
  declarations: [
    TopicItemComponent
  ],
  imports: [
    CommonModule,
    TopicRoutingModule,
    CoreModule
  ]
})
export class TopicModule { }
