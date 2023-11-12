import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TopicItemComponent } from './topic-item/topic-item.component';
import { authGuard } from 'src/app/shared/auth.guard';

const routes: Routes = [
  {path: '', component: TopicItemComponent, canActivate : [authGuard]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TopicRoutingModule { }
