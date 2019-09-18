import {Injectable} from '@angular/core';
import {Router , CanActivate} from '@angular/router';
import { ExpenseService } from '../expense/expense.service';

@Injectable()
export class AuthGuard implements CanActivate{
    constructor(private expenseService:ExpenseService,
                private router:Router){

    }

    canActivate(){
       if(this.expenseService.loggedIn())
       return true;
       else {
           this.router.navigate(['/login']);
           return false;
       }
        
    }


}