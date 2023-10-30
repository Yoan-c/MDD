import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-button',
  templateUrl: './button.component.html',
  styleUrls: ['./button.component.scss']
})
export class ButtonComponent implements OnInit {

  @Input() label!: string;
  @Output() buttonEvent = new EventEmitter<string>();
  
  constructor() { }

  ngOnInit(): void {
  }

  onClick(): void {
    this.buttonEvent.emit(this.label);
  }

}
