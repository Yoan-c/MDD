import { NgModule } from '@angular/core';
import { HeaderComponent } from './components/header/header.component';
import { ButtonComponent } from './components/button/button.component';

@NgModule({
  declarations: [HeaderComponent, ButtonComponent],
  imports: [
  ],
  exports : [HeaderComponent, ButtonComponent],
  providers: [],
})
export class CoreModule {}
