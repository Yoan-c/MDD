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


@NgModule({
  declarations: [
    PostItemComponent,
    PostListComponent,
    PostFormComponent
  ],
  imports: [
    CommonModule,
    CoreModule,
    PostRoutingModule,
    SharedModule,
    ReactiveFormsModule
  ],
  providers : [PostService]
})
export class PostModule { }
