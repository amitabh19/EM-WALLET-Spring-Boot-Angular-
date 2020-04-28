import { Component, OnInit } from '@angular/core';
import { Transaction } from 'src/app/Transaction';
import { User } from 'src/app/user';
import { UserService } from 'src/app/shared_service/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-trans-list',
  templateUrl: './trans-list.component.html',
  styleUrls: ['./trans-list.component.css']
})
export class TransListComponent implements OnInit {

  tran:Transaction
  trans:Transaction[];
  users:User[];
  user:User;
  use:User;
  constructor(private userService:UserService, private router:Router) { }

  ngOnInit(){
    this.user=this.userService.getter();
    this.userService.getTransById(this.user.id).subscribe(x => {this.trans=x;console.log(this.trans);} );
    this.userService.getUsers().subscribe((data: any[])=>{this.users = data;}) 
        
  }

  getName(id:Number)
  {
    this.use = this.users.find(x => x.id == id);
    return this.use.name;
  }
  getMob(id:Number)
  {
    this.use = this.users.find(x => x.id == id);
    return this.use.mobileNo;
  }

  sortTime()
  {
    this.trans.sort((n1,n2) => {
      if (n1.tTime > n2.tTime) {
          return 1;
      }
  
      if (n1.tTime < n2.tTime) {
          return -1;
      }
  
      return 0;
  });
    
  }
}

