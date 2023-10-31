import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-button',
  templateUrl: './button.component.html',
  styleUrls: ['./button.component.scss']
})
export class ButtonComponent implements OnInit {

  @Input() label: string = "label";
  @Input() isDisabled: boolean = false;
  @Input() bgColor: string = "white";
  @Input() color: string = "black";
  @Output() buttonEvent = new EventEmitter<string>();
  
  constructor() { }

  ngOnInit(): void {
  }

  get buttonStyles() {
    return {
      'background-color': this.bgColor,
      'color' : this.color,

    }
  }

  onClick(): void {
    this.buttonEvent.emit(this.label);
  }

}
