import { trigger, state, style, transition, animate } from '@angular/animations';
import { AfterViewInit, Component, ElementRef, Input, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
  animations: [
    trigger('slideAnimation', [
      state('void', style({ transform: 'translateX(-100%)',opacity: 0, 'z-index': 0 })), // État initial
      state('slide-in', style({ transform: 'translateX(0)', opacity: 1, 'z-index': 1 })), // État final
      transition('void => slide-in', animate('1s')), // Transition pour l'entrée
      transition('slide-in => void', animate('1s')), // Transition pour la sortie
    ])
  ]
})
export class HeaderComponent implements AfterViewInit {

  @Input() route: string = 'post';
  @ViewChild('post') post: ElementRef = new ElementRef(null);
  @ViewChild('topic') topic: ElementRef = new ElementRef(null);
  isSlideIn: boolean = false;

  constructor(private router: Router) {
   }

  ngAfterViewInit(): void {
     if(this.route === 'post')
      this.post.nativeElement.classList.add('linkStyle');
     else if(this.route === 'topic')
      this.topic.nativeElement.classList.add('linkStyle');
     else if(this.route === 'profile'){
        let profileImg: HTMLImageElement = document.querySelector("#profileImg")!;
        profileImg!.src = `../../../../assets/activeProfile.png?v=${Math.random()}`
        
     }
      
  }

  goPost() {
    this.router.navigate(['/post']);
  }

  goTopic() {
    this.router.navigate(['/topic']);
  }

  goProfile() {
    this.router.navigate(['/profile']);
  }

  displayMenu(){
    // let menu = document.querySelector("#menuMobile")!;
    // console.log(menu);
    // menu.classList.add('slide-in')
    this.isSlideIn = true;
  }
}
