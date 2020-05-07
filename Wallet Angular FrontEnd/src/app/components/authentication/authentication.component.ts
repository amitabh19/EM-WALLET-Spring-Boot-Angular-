import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/user';
import { UserService } from 'src/app/shared_service/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-authentication',
  templateUrl: './authentication.component.html',
  styleUrls: ['./authentication.component.css']
})
export class AuthenticationComponent implements OnInit {

  users: User[];
  usr = new User();
  username: string;
  password: string;
  error:string;
  user = new User();
  user1 = new User();
  tusername:string;
  constructor(private userService: UserService, private router: Router) { }

  ngOnInit() {

    this.tusername = this.userService.username;
    this.userService.getUsers2().then((data: any[]) => {
      console.log(data);
      this.users = data;
    })
    
  }

  processForm() {
    this.user = this.users.find(x => x.username == this.username);
    if(this.user == null)
    {
      this.userService.getUsers2().then((data: any[]) => {
        this.users = data;
      });
      this.user = this.users.find(x => x.username==this.tusername);
       
    }
    
    if (this.username == "admin" && this.password == "admin") {
      this.router.navigate(['lu']);
    }
    else if(this.user==null)
    {
      this.error="User does not exist. Please signUp or enter the correct username";
    }
    else {
      if (this.user.password == this.password) {
        console.log("logged in");
        this.userService.setter(this.user);
        this.router.navigate(['uops']);
      }

      else {
        this.error = "Wrong password. Try Again";
        console.log("wrong password");
      }
    }
  }

}
