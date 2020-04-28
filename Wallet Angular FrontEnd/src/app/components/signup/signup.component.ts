import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/user';
import { UserService } from 'src/app/shared_service/user.service';
import { Router } from '@angular/router';
import { Form, FormGroupName } from '@angular/forms';
import { timeout } from 'rxjs/operators';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  users:User[];
  user=new User();
  name:string;
  id:number;
  accountBool:Boolean= true;
  phoneBool:Boolean=true;
  userBool:Boolean=true;
  emailBool:Boolean=true;
  mobile:string;
  user1 = new User();
  constructor(private userService:UserService, private router:Router) { }

  ngOnInit() {
    this.userService.getUsers().subscribe((data: any[])=>{
      console.log(data);
      this.users = data;

    }
    ) 
  }

 
  makeAccount(name,mobileNo,amount,username,password,email,dob){
    let valid:boolean
    let id:Number
    for(let c of this.users){
        id = c.id;
    if(c.username == username){
      console.log("Username Already Taken");
      valid = false;
      this.userBool= false;
      break;
    }
    else if(c.emailId == email){
      console.log("Email Number Already Taken");
      valid = false;
      this.emailBool=false;
      break;
    }
    
    
    else if(c.mobileNo == mobileNo){
      console.log("Phone Number Already Taken");
      valid = false;
      this.phoneBool=false;
      break;
    }
    else{
    valid=true;
    }
  }
  if(valid==true){

  console.log(email + " "+name+" "+" "+mobileNo+" "+amount+" "+username+ " "+password);
  this.user.id=undefined;
  this.user.accNo=undefined;
  this.user.name=name;
  this.user.mobileNo=mobileNo;
  this.user.balance=amount;
  this.user.username=username;
  this.user.password=password;
  this.user.dob=dob;
  this.user.emailId=email;
  this.mobile=mobileNo;

  this.userService.addCustomer(this.user).subscribe(x => console.log(x));
  //this.userService.setter(this.user);
  //console.log(this.user);
  console.log("inserted");
  //timeout(3000);
  //this.users.push(this.user);
  //this.userService.getUserById(this.user.id).subscribe(x => this.user=x);
  
  this.userService.username = this.user.username;
  
  this.router.navigate(['au']);
    }
    else{
      console.log("Not Inserted")
    }



} 
}
