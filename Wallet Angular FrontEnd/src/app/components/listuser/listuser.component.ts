import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/user';
import { UserService } from 'src/app/shared_service/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-listuser',
  templateUrl: './listuser.component.html',
  styleUrls: ['./listuser.component.css']
})
export class ListuserComponent implements OnInit {

  public users:User[];
  public user:User;
  //private ser=[];
  constructor(private userService: UserService, private router:Router) { }

  ngOnInit() {
    this.userService.getUsers().subscribe((data: any[])=>{
      console.log(data);
      this.users = data;

    }
    ) 
    this.userService.getUserById(1).subscribe(u => 
      {
        this.user = u
        console.log(this.user);      
      });
    

  }
  getUser(id:number)
  {
    this.userService.getUserById(id).subscribe(u => this.user = u);
  }

  deleteUser(user)
  {
    this.userService.deleteUserById(user.id).subscribe(
      (u) => {
        this.users.splice(this.users.indexOf(user,1));
      },
      
    )
  }

  updateUser(user)
  {
    this.userService.setter(user);
    this.router.navigate(['/op']);
  }

  newUser()
  {
    let user = new User();
    this.userService.setter(user);
    this.router.navigate(['/op']);
  }



}
