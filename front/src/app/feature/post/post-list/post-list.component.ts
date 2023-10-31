import { Component, Input } from '@angular/core';
import { PostItem } from '../interface/post-item.interface';

@Component({
  selector: 'app-post-list',
  templateUrl: './post-list.component.html',
  styleUrls: ['./post-list.component.scss']
})
export class PostListComponent {

  @Input()  postItem: PostItem | undefined;

  
}
