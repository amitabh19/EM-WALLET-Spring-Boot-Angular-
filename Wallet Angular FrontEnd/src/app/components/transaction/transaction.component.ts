import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/user';
import { UserService } from 'src/app/shared_service/user.service';
import { Route } from '@angular/compiler/src/core';
import { Router } from '@angular/router';
import { Trans } from 'src/app/Trans';

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
      let t = new Trans();
      t.sid = this.user.id;
      t.rid = this.usr.id;
      t.amount = this.amount;
      this.userService.performTrans(t).subscribe( x => this.error = x.toString());
      this.userService.getUsers().subscribe((data: any[])=>{
        console.log(data);
        this.users = data;
  
      }
      ) 
      //this.user = this.userService.getter();
      this.success = "transaction sucessfull";
      this.userService.getUserById(this.user.id).subscribe( x=> {this.user=x});

    }
    else{
      this.error = "user not found or insufficient balance or wrong amount";
      this.router.navigate(['trans']);
    }
  }
  
}
