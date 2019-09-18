import { Injectable } from '@angular/core';
import { Expense } from '../model/expense';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { User } from '../model/user';
import {tokenNotExpired} from 'angular2-jwt';

@Injectable({
  providedIn: 'root'
})
export class ExpenseService {
  readonly url='http://localhost:8080/details';
  readonly baseURL='http://localhost:8080/expense';
  readonly getURL='http://localhost:8080/user';
  readonly loginURL='http://localhost:8080/login';
  readonly activity='http://localhost:8080/activity';
  readonly friends='http://localhost:8080/friends';
  readonly friends2='http://localhost:8080/friends2';
  firstName:string="";
  constructor(private http: HttpClient) {
    this.getName();
   }

  addExpense(expense:Expense){
    console.log(expense);
    return this.http.post(this.baseURL,expense);
  }

  getExpense(){
    return this.http.get(this.activity);
  }

  login(user:User){
    console.log(user);
    return this.http.post(this.loginURL,user);
  }

  storeUserData(user:User){
    localStorage.setItem('user', JSON.stringify(user));
  }

  getUsers(){
    return this.http.get(this.getURL);
  }

  logout(){
   // this.firstName="";
    localStorage.clear();
  }

  getDetails(){
    let user=JSON.parse(localStorage.getItem('user'));
    console.log(user);
    return this.http.post(this.friends,user);
  }

  getDetails2(){
    let user=JSON.parse(localStorage.getItem('user'));
    console.log(user);
    return this.http.post(this.friends2,user);
  }

  getDetail(name:string){
    let user=JSON.parse(localStorage.getItem('user'));
    return this.http.post(this.url,user);
  }

  getProfile(){
    let user=JSON.parse(localStorage.getItem('user'));
    return user;
  }

  loggedIn(){
    let user=JSON.parse(localStorage.getItem('user')) || [];
    if(user.firstName!=" " || user.firstName!=undefined)
    return true;
    else
    return false;
    }

    getName(){
      let a = JSON.parse(localStorage.getItem('user')) || [];
      this.firstName=a.firstName;
    }

}


