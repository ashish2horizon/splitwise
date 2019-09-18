import { Component, OnInit } from '@angular/core';
import { ExpenseService } from '../expense/expense.service';
import { Router } from '@angular/router';
import {FlashMessagesService} from 'angular2-flash-messages';
import { User } from '../model/user';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
user:User={
  firstName:null,
  lastName:null,
  email:null,
  password:null,
  owe:null,
  owed:null
};
name:string=null;

  constructor(
    private router: Router,
    private expenseService:ExpenseService,
    private flashMessage:FlashMessagesService 
  ) { }

  ngOnInit() {
    this.getProfile();
  }

  onLogoutClick(){
    this.expenseService.logout();
    this.flashMessage.show('You are logged out',{
      cssClass:'alert-success',
      timeout:1000
    })
    this.router.navigate(['']);
  //  return false;
  }

  getProfile(){
   this.user=this.expenseService.getProfile();
  }

}
