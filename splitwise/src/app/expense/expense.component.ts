import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormArray } from '@angular/forms';
import { Router } from '@angular/router';
import { ExpenseService } from './expense.service';
import { User } from '../model/user';
import { Expense } from '../model/expense';

@Component({
  selector: 'app-expense',
  templateUrl: './expense.component.html',
  styleUrls: ['./expense.component.css']
})
export class ExpenseComponent implements OnInit {
  expenseForm:FormGroup;
  myForm:FormGroup;
  description:string;
  price:string;
  paidBy:String;
  users:User[]=[];

  dropdownList = [];
  selectedItems = [];
  dropdownSettings = {};

  constructor(private formBuilder: FormBuilder,
    private router: Router,
    private expenseService:ExpenseService,
 //   private flashMessage:FlashMessagesService
    ) { 
this.expenseForm = this.formBuilder.group({
'description': ['',Validators.required],
'price': ['',Validators.required],
'paidBy': ['',Validators.required]
});

this.myForm = this.formBuilder.group({
  "checkbox": this.formBuilder.array([])
});
}

get f() { return this.expenseForm.controls; }

ngOnInit() {
  this.getUsers();

  this.dropdownList = this.users;

  this.dropdownSettings = {
  singleSelection: false,
  idField: '_id',
  textField: 'firstName',
  selectAllText: 'Select All',
  unSelectAllText: 'UnSelect All',
  itemsShowLimit: 5,
  allowSearchFilter: true
};
}



onItemSelect(item: any) {
  this.selectedItems.push(item.firstName);
  console.log("split:"+this.selectedItems);
}
onSelectAll(items: any) {
  items.forEach(element => {
   this.selectedItems.push(element.firstName);
   console.log("split:"+this.selectedItems);
  });
}


addExpense(){
console.log(this.expenseForm.controls.description.value);
let expense:Expense={
  description:this.expenseForm.controls.description.value,
  price:this.expenseForm.controls.price.value,
  paidBy:this.expenseForm.controls.paidBy.value,
  split:this.selectedItems
}
this.expenseService.addExpense(expense).subscribe(data=> { 
}); 
}

getUsers(){
  this.expenseService.getUsers().subscribe(data=> { 
    this.users=data as User[];
    console.log(this.users);
  });

}

}
