import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/user';
import { UserService } from 'src/app/shared_service/user.service';
import { Router } from '@angular/router';
import { Transaction } from 'src/app/Transaction';

@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.css']
})
export class TransactionComponent implements OnInit {

  public users:User[];
  mobileNo:string;
  password:string;
  balance:string;
  usr = new User();
  user = new User();
  amount:number;
  rmobileNo:string;
  error:string;
  success:string;
  tran= new Transaction();
  date:Date;
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
    console.log(this.usr);
    this.usr = this.users.find(x => x.mobileNo == this.rmobileNo);
    console.log(this.usr);
    if(this.usr!=null  && this.amount<=this.user.balance && this.amount>0)
    {
    
      this.tran.amount=this.amount;
      this.tran.recieverId = this.usr.id;
      this.tran.tType = "debit";
      this.tran.txnId ="";
      this.tran.tTime = "";
      this.tran.updatedPersonBal=0;
      this.tran.senderId = this.user.id;
      console.log(this.tran);
     
      this.userService.addTrans(this.tran).subscribe(x => console.log(x));
      this.userService.getUsers().subscribe((data: any[])=>{
        console.log(data);
        this.users = data;
  
      }
      ) 
      this.success = "transaction sucessful";
      this.userService.getUserById(this.user.id).subscribe( x=> {this.user=x});

    }
    else{
      this.success = "user not found or insufficient balance or wrong amount";
      this.router.navigate(['trans']);
    }
  }
  refresh()
  {
    
    this.userService.setter(this.user);
    this.router.navigate(['uops']);

  }

  
}
