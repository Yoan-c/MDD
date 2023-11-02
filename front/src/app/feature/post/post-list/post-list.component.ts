import { Component, Input } from '@angular/core';
import { PostItem } from '../interface/post-item.interface';
import { Router } from '@angular/router';

@Component({
  selector: 'app-post-list',
  templateUrl: './post-list.component.html',
  styleUrls: ['./post-list.component.scss']
})
export class PostListComponent {

  @Input()  postItem: PostItem | undefined;

  constructor(private router: Router){}

  onClick() {
    this.router.navigate(['/post/post'], {queryParams : {id : this.postItem?.id}})
    
  }
  
}
