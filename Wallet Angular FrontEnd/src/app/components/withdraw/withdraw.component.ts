import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/shared_service/user.service';
import { Router } from '@angular/router';
import { User } from 'src/app/user';
import { Transaction } from 'src/app/Transaction';

@Component({
  selector: 'app-withdraw',
  templateUrl: './withdraw.component.html',
  styleUrls: ['./withdraw.component.css']
})
export class WithdrawComponent implements OnInit {

  public users:User[];
  balance:string;
  usr = new User();
  user = new User();
  amount:number;
  error:string;
  success:string;
  tran= new Transaction();
  
  constructor(private userService:UserService, private router:Router) { }

  ngOnInit() {

    this.userService.getUsers().subscribe((data: any[])=>{
      console.log(data);
      this.users = data;

    }
    ) 
      this.user = this.userService.getter();
    }

  processForm()
  {
    if(this.amount<=this.user.balance && this.amount>0)
    {
      this.tran.amount=this.amount;
      this.tran.recieverId = this.user.id;
      this.tran.tType = "self withdraw";
      this.tran.txnId ="";
      this.tran.tTime = "";
      this.tran.updatedPersonBal=0;
      this.tran.senderId = this.user.id;
      console.log(this.tran);
      this.userService.addTrans(this.tran).subscribe(x => console.log(x));
      
      this.success = "transaction successful";
       
    }
    else{
      this.success = "insufficient balance";
    }   
  }

  refresh()
  {
    
    this.userService.setter(this.user);
    this.router.navigate(['uops']);

  }

}
