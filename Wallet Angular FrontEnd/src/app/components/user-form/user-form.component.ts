import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/user';
import { UserService } from 'src/app/shared_service/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css']
})
export class UserFormComponent implements OnInit {

  user:User;
  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(){
    this.user = this.userService.getter();
  }
  processForm(){
    if(this.user.id==undefined){
       this.userService.createUser(this.user).subscribe((user)=>{
         console.log(user);
         this.router.navigate(['/']);
       },(error)=>{
         console.log(error);
       });
    }else{
       this.userService.updateUser(this.user).subscribe((user)=>{
         console.log(user);
         this.router.navigate(['/']);
       },(error)=>{
         console.log(error);
       });
    }
  }

}
