import { Component, OnInit } from '@angular/core';
import { ExpenseService } from '../expense/expense.service';
import { Friends } from '../model/friends';
import { Router } from '@angular/router';

@Component({
  selector: 'app-friends',
  templateUrl: './friends.component.html',
  styleUrls: ['./friends.component.css']
})
export class FriendsComponent implements OnInit {
  friends:Friends;
   map:Map<String,Number>;
   map2:Map<String,Number>;
  constructor(
    private expenseService:ExpenseService,
    private router:Router
  ) { }

  ngOnInit() {
    this.getDetails();
    this.getDetails2();
  }

  getDetails(){
    this.expenseService.getDetails().subscribe((res)=>{
      this.map=res as Map<String,Number>;
      for (const [key, value] of Object.entries(this.friends)) { 
        console.log(key, value);
    }
    });
  }

  getDetails2(){
    this.expenseService.getDetails2().subscribe((res)=>{
      this.map2=res as Map<String,Number>;
      for (const [key, value] of Object.entries(this.friends)) { 
        console.log(key, value);
    }
    });
  }

  onBack(): void {
    this.router.navigate(['/activity']);
  }

}
