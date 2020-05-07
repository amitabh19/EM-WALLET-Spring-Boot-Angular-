import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/user';
import { UserService } from 'src/app/shared_service/user.service';
import { Router } from '@angular/router';
import {  FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  users:User[];
  user=new User();
  //name:string;
  id:number;
  accountBool:Boolean= true;
  phoneBool:Boolean=true;
  userBool:Boolean=true;
  emailBool:Boolean=true;
  mobile:string;
  user1 = new User();
  signupForm : FormGroup;
  constructor(private userService:UserService, private router:Router,private fb: FormBuilder) { }

  ngOnInit() {
    
    this.signupForm = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(20)]],
      email: ['', [Validators.required, Validators.email]],
      amount: ['', [Validators.required, Validators.min(0)]],
      mobileNo: ['',[Validators.required,Validators.pattern("[0-9]*"), Validators.minLength(10), Validators.maxLength(10)]],
      dob: [''],
      username: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(10)]],
      password: ['',[Validators.required, Validators.minLength(8), Validators.maxLength(20)]]

    });

    this.userService.getUsers().subscribe((data: any[])=>{
      console.log(data);
      this.users = data;

    }
    );
  }
  onSubmit()
  {
    let valid:boolean
    let id:Number
    for(let c of this.users){
        id = c.id;
    if(c.username == this.signupForm.value.username){
      console.log("Username Already Taken");
      valid = false;
      this.userBool= false;
      break;
    }
    else if(c.emailId == this.signupForm.value.email){
      console.log("Email Number Already Taken");
      valid = false;
      this.emailBool=false;
      break;
    }
    
    
    else if(c.mobileNo == this.signupForm.value.mobileNo){
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

  console.log(this.signupForm.value.email + " "+name+" "+" "+this.signupForm.value.mobileNo+" "+this.signupForm.value.amount+" "+this.signupForm.value.username+ " "+this.signupForm.value.password);
  this.user.id=undefined;
  this.user.accNo=undefined;
  this.user.name=this.signupForm.value.name;
  this.user.mobileNo=this.signupForm.value.mobileNo;
  this.user.balance=this.signupForm.value.amount;
  this.user.username=this.signupForm.value.username;
  this.user.password=this.signupForm.value.password;
  this.user.dob=this.signupForm.value.dob;
  this.user.emailId=this.signupForm.value.email;
  this.mobile=this.signupForm.value.mobileNo;

  this.userService.addCustomer(this.user).subscribe(x => console.log(x));
  console.log("inserted");
  this.userService.username = this.user.username;
  
  this.router.navigate(['au']);
    }
    else{
      console.log("Not Inserted")
    }



} 
 }
