import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ExpenseService } from '../expense/expense.service';
import {FlashMessagesService} from 'angular2-flash-messages';
import { User } from '../model/user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm:FormGroup;
 // email:string;
 // password:string;
  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private expenseService:ExpenseService,
              private flashMessage:FlashMessagesService
              ) { 
      this.loginForm = this.formBuilder.group({
        'email': ['',[Validators.required, Validators.email]],
        'password': ['',Validators.required]
      });
  }

  get f() { return this.loginForm.controls; }

  ngOnInit() {
  }

  validateLogin(){
    if (this.loginForm.invalid) {
      return;
  }
    this.expenseService.login(this.loginForm.value).subscribe(data=> {
      var flashMessage=this.flashMessage;
      let user=data as User;
      console.log("res");
      console.log(user.email);
        if(user.email===this.loginForm.controls.email.value){
          console.log('if');
          this.flashMessage.show('You are now logged in', {
            cssClass: 'alert-success',
            timeout: 1000});
            this.expenseService.storeUserData(user);
            this.expenseService.getName();
           this.router.navigate(['/expense']);
        
         
      } 
      else{
        console.log("else");
        this.flashMessage.show("Invalid email and password", {
          cssClass: 'alert-danger',
          timeout: 1000});
        this.router.navigate(['']);
      }
      });
    }
}
