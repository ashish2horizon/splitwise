import { Component, OnInit } from '@angular/core';
import { Expense } from '../model/expense';
import { ExpenseService } from '../expense/expense.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-activity',
  templateUrl: './activity.component.html',
  styleUrls: ['./activity.component.css']
})
export class ActivityComponent implements OnInit {

  expenses:Expense[]=[];

  constructor(private expenseService:ExpenseService,
    private router:Router) { }

  ngOnInit() {
    this.getExpense();
  }

  getExpense(){
    this.expenseService.getExpense().subscribe((res)=>{
      this.expenses=res as Expense[];
      console.log("Activity"+this.expenses);
    });
  }

  goBack(): void {
    this.router.navigate(['expense']);
  }

}
