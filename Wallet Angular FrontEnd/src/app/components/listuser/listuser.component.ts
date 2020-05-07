import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/user';
import { UserService } from 'src/app/shared_service/user.service';
import { Router } from '@angular/router';
import { Transaction } from 'src/app/Transaction';

@Component({
  selector: 'app-listuser',
  templateUrl: './listuser.component.html',
  styleUrls: ['./listuser.component.css']
})
export class ListuserComponent implements OnInit {

  public users:User[];
  public user:User;
  public trans:Transaction[];
  public seeT:boolean;
  constructor(private userService: UserService, private router:Router) { }

  ngOnInit() {
    this.userService.getUsers().subscribe((data: any[])=>{
      console.log(data);
      this.users = data;

    },(error)=>{
      console.log(error);
    }
    ) 
    

  }
  getUser(id:number)
  {
    this.userService.getUserById(id).subscribe(u => {this.user = u},(error)=>{
      console.log(error);
    });
  }

  deleteUser(user)
  {
    if (confirm("Do you want to delete this account?")) {
      this.userService.deleteUserById(user.id).subscribe(
        (u) => {
          this.users.splice(this.users.indexOf(user,1));
        },
        
      )
    } else {
      console.log("no");
    }
    
  }

  updateUser(user)
  {
    this.userService.setter(user);
    this.router.navigate(['/op']);
  }

  newUser()
  {
    this.router.navigate(['/sign']);
  }

  seeTransactions(user)
  {
    this.userService.setter(user);
    this.router.navigate(['/tlist']);

  }
}
