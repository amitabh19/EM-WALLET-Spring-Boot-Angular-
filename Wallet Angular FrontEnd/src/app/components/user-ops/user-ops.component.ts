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
  
  constructor(private userService:UserService, private router:Router) { }

  ngOnInit() {

      this.user = this.userService.getter();
    }
    deleteAccount()
    {
      this.userService.deleteUserById(this.user.id).subscribe(
        (u) => {
          this.users.splice(this.users.indexOf(this.user,1));
        },
        
      )
      console.log("done");
      this.router.navigate(['']);
      
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