import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: '', loadChildren: () => import('./feature/auth/auth.module').then(m => m.AuthModule) },
  {path : 'post', loadChildren: () => import('./feature/post/post.module').then(m => m.PostModule)},
  {path : 'topic', loadChildren: () => import('./feature/topic/topic.module').then(m => m.TopicModule)},
  {path : 'profile', loadChildren: () => import('./feature/profile/profile.module').then(m => m.ProfileModule)},
  {path: '**', redirectTo : '/'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
