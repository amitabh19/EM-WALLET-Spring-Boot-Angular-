import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/user';
import { UserService } from 'src/app/shared_service/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-ops',
  templateUrl: './user-ops.component.html',
  styleUrls: ['./user-ops.component.css']
})
export class UserOpsComponent implements OnInit {

  public users:User[];
  mobileNo:string;
  password:string;
  balance:string;
  user = new User();
  usr = new User();
  constructor(private userService:UserService, private router:Router) { }

  ngOnInit() {

    
      this.usr = this.userService.getter();
      this.userService.getUserById(this.usr.id).subscribe(x => this.user=x);
    }
    deleteAccount()
    {
      if (confirm("Do you want to delete this account?"))
      {
      this.userService.deleteUserById(this.user.id).subscribe(
        (u) => {
          this.users.splice(this.users.indexOf(this.user,1));
        },
        
      )
      console.log("done");
      this.router.navigate(['']);
    }
    else{
      console.log("no");
    }
      
    }

    updateUser(user)
    {

    }

    SendMoney()
    {
      this.userService.setter(this.user);
      this.router.navigate(['trans']);
    }
    depositMoney()
    {
      this.userService.setter(this.user);
      this.router.navigate(['depo']);
    }

    withdraw()
    {
      this.userService.setter(this.user);
      this.router.navigate(['with']);
    }

    translist()
    {
      this.userService.setter(this.user);
      this.router.navigate(['tlist']);
    }
  }