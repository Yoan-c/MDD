import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TopicItemComponent } from './topic-item/topic-item.component';

const routes: Routes = [
  {path: '', component: TopicItemComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TopicRoutingModule { }
