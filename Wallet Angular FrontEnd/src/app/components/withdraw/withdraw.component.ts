import { Component, OnInit } from '@angular/core';
import { Trans } from 'src/app/Trans';
import { UserService } from 'src/app/shared_service/user.service';
import { Router } from '@angular/router';
import { User } from 'src/app/user';

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
    if(this.amount<=this.user.balance)
    {
      let t = new Trans();
      t.sid = this.user.id;
      t.rid = this.usr.id;
      t.amount = this.amount;
      this.userService.with(t).subscribe( x => this.error = x.toString());
      
      //this.user = this.userService.getter();
      this.success = "transaction successful";
       
      //this.userService.setter(this.user);
      console.log(this.user.balance);

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
