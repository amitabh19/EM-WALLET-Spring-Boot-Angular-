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
  username: string;
  password: string;
  user = new User();
  constructor(private userService: UserService, private router: Router) { }

  ngOnInit() {

    this.userService.getUsers().subscribe((data: any[]) => {
      console.log(data);
      this.users = data;

    })
  }

  processForm() {
    this.user = this.users.find(x => x.username == this.username);
    if (this.username == "admin" && this.password == "admin") {
      this.router.navigate(['lu']);
    }
    else {
      if (this.user.password == this.password) {
        console.log("logged in");
        this.userService.setter(this.user);
        this.router.navigate(['uops']);
      }

      else {
        console.log("wrong password");
      }
    }
  }

}
