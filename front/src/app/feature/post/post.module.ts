import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PostRoutingModule } from './post-routing.module';
import { PostItemComponent } from './post-item/post-item.component';
import { PostListComponent } from './post-list/post-list.component';
import { CoreModule } from 'src/app/core/core.module';
import { PostService } from './service/post-item.service';
import { SharedModule } from 'src/app/shared/shared.module';
import { PostFormComponent } from './post-form/post-form.component';
import { ReactiveFormsModule } from '@angular/forms';
import { PostComponent } from './post/post.component';
import { CommentComponent } from './comment/comment.component';
import { CommentService } from './service/comment.service';


@NgModule({
  declarations: [
    PostItemComponent,
    PostListComponent,
    PostFormComponent,
    PostComponent,
    CommentComponent
  ],
  imports: [
    CommonModule,
    CoreModule,
    PostRoutingModule,
    SharedModule,
    ReactiveFormsModule
  ],
  providers : [PostService, CommentService]
})
export class PostModule { }
