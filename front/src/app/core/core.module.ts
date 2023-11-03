import { NgModule } from '@angular/core';
import { HeaderComponent } from './components/header/header.component';
import { ButtonComponent } from './components/button/button.component';
import { CommonModule } from '@angular/common';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@NgModule({
  declarations: [HeaderComponent, ButtonComponent],
  imports: [
    CommonModule
  ],
  exports : [HeaderComponent, ButtonComponent],
  providers: [],
})
export class CoreModule {}
