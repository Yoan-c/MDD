import { trigger, state, style, transition, animate } from '@angular/animations';
import { AfterViewInit, ChangeDetectorRef, Component, ElementRef, Input, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
  animations: [
    trigger('slideAnimation', [
      state('void', style({ transform: 'translateX(-100%)',opacity: 0, 'z-index': 0 })),
      state('slide-in', style({ transform: 'translateX(0)', opacity: 1, 'z-index': 1 })),
      transition('void => slide-in', animate('1s')),
      transition('slide-in => void', animate('1s')),
    ])
  ]
})
export class HeaderComponent implements OnInit, AfterViewInit {

  @Input() route: string = 'post';
  @ViewChild('post') post: ElementRef = new ElementRef(null);
  @ViewChild('topic') topic: ElementRef = new ElementRef(null);
  @ViewChild('srcImg') srcImg: string = "../../../../assets/useruserImg.png";
  isSlideIn: boolean = false; 

  constructor(private router: Router, private cdr: ChangeDetectorRef) {}

  ngOnInit(): void {
    this.srcImg = "../../../../assets/useruserImg.png";
    this.cdr.detectChanges();
  }

  ngAfterViewInit(): void {
     if(this.route === 'post')
      this.post.nativeElement.classList.add('linkStyle');
     else if(this.route === 'topic')
      this.topic.nativeElement.classList.add('linkStyle');
    else if(this.route === 'profile'){
      this.srcImg = `../../../../assets/activeProfile.png`;
   }
   this.cdr.detectChanges();
  }

  goPost(): void {
    this.router.navigate(['/post']);
  }

  goTopic(): void {
    this.router.navigate(['/topic']);
  }

  goProfile(): void {
    this.router.navigate(['/profile']);
  }

  displayMenu(): void{
    this.isSlideIn = true;
  }
}
