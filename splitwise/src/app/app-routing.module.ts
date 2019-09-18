import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ExpenseComponent } from './expense/expense.component';
import { HeaderComponent } from './header/header.component';
import { ActivityComponent } from './activity/activity.component';
import { LoginComponent } from './login/login.component';
import { FriendsComponent } from './friends/friends.component';
import { FriendDetailComponent } from './friends/friend-detail/friend-detail.component';

const routes: Routes = [
  {path:'',component:LoginComponent},
  {path:'expense',component:ExpenseComponent},
  {path:'activity',component:ActivityComponent},
  {path:'friends',component:FriendsComponent},
  {path:'detail/:name',component:FriendDetailComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
