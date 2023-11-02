import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PostItemComponent } from './post-item/post-item.component';
import { authGuard } from 'src/app/shared/auth.guard';
import { PostFormComponent } from './post-form/post-form.component';
import { PostComponent } from './post/post.component';

const routes: Routes = [
  
  {path : '', component : PostItemComponent, canActivate : [authGuard]},
  {path : ':id', component : PostComponent, canActivate : [authGuard]},
  {path : 'create', component : PostFormComponent, canActivate : [authGuard]},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PostRoutingModule { }
