import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/shared_service/user.service';
import { Router } from '@angular/router';
import { User } from 'src/app/user';
import { Trans } from 'src/app/Trans';

@Component({
  selector: 'app-deposit',
  templateUrl: './deposit.component.html',
  styleUrls: ['./deposit.component.css']
})
export class DepositComponent implements OnInit {

  public users:User[];
  balance:string;
  usr = new User();
  user = new User();
  amount:number;
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
   
    if(this.amount>0){
      let t = new Trans();
      t.sid = this.user.id;
      t.rid = this.usr.id;
      t.amount = this.amount;
      this.userService.depo(t).subscribe( x => this.error = x.toString());
      this.userService.getUsers().subscribe((data: any[])=>{
        console.log(data);
        this.users = data;
  
      }
      ) 
      //this.user = this.userService.getter();
      this.success = "transaction successful";   
    } 
    else{this.success="invalid amount";}
      
  }
  refresh()
  {
    
    this.userService.setter(this.user);
    this.router.navigate(['uops']);

  }



}
