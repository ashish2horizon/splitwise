import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ExpenseService } from 'src/app/expense/expense.service';
import {FlashMessagesService} from 'angular2-flash-messages';
import { Activity } from 'src/app/model/activity';

@Component({
  selector: 'app-friend-detail',
  templateUrl: './friend-detail.component.html',
  styleUrls: ['./friend-detail.component.css']
})
export class FriendDetailComponent implements OnInit {
 details:Activity[]=[];
 detail:Activity[]=[]; 
  constructor(
    private router:Router,
    private route: ActivatedRoute,
    private expenseService:ExpenseService
  ) { }

  ngOnInit() {
    const param = this.route.snapshot.paramMap.get('name');
    if (param) {
      const name = param;
      this.getDetail(name);
    }
  }

  getDetail(name:string){
    this.expenseService.getDetail(name).subscribe((res)=>{
      this.details=res as Activity[];
      this.details.forEach((act)=>{
        if(act.owesTo==name){
          this.detail.push(act);
        }
      });
      console.log(this.details);
      console.log(this.detail);
    });

  }

  onBack(): void {
    this.router.navigate(['/friends']);
  }

}
