import { Component, OnInit ,Inject,Renderer2} from '@angular/core';
import { DOCUMENT } from '@angular/common';
import { Customer } from '../customer';
import {CustomerService} from '../customer.service';
import {Router} from '@angular/router';
import { Transaction } from '../transaction';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  private customers:Customer[];
  private customer:Customer;
  private accountBool:Boolean= true;
  private panBool:Boolean = true;
  private aadharBool:Boolean=true;
  private phoneBool:Boolean=true;
  private userBool:Boolean=true;

  constructor(private _customerService:CustomerService,private _router:Router) { }
  details:Boolean = true;
  ngOnInit() {
      this._customerService.getCustomer().subscribe((customers)=>{
        console.log(customers);
        this.customers=customers;
      },(error)=>{
        console.log(error)
      })

      this.customer= this._customerService.getter();


  }

  processForm(username,password){
      for(let c of this.customers){
        console.log(c);
        if(c.username==username && c.password==password){
          this.details=true
          this._router.navigate(['/showById']);
          this._customerService.setter(c);
          break;
        }
        else{
          this.details=false;
          this._router.navigate(['/']);
          console.log("Invalid Details");
        }
      }
  }

  showLogin(signup,login){
      signup.style.display="none";
      login.style.display="block";
  }

  showSignup(signup,login){
    signup.style.display="block";
    login.style.display="none";
  }

  makeAccount(accountNumber,name,pan,aadhar,phone,amount,username,password){
    let valid:boolean
    let id:Number
    for(let c of this.customers){
        id = c.userId;
    if(c.username == username){
      console.log("Username Already Taken");
      valid = false;
      this.userBool= false;
      break;
    }
    else if(c.accountNumber == accountNumber){
      console.log("Account Number Already Taken");
      valid = false;
      this.accountBool=false;
      break;
    }
    else if(c.panNumber == pan){
      console.log("Pan Number Already Taken");
      valid = false;
      this.panBool=false;
      break;
    }
    else if(c.aadharNumber == aadhar){
      console.log("Aadhar Number Already Taken");
      valid = false;
      this.aadharBool=false;
      break;
    }
    else if(c.phoneNo == phone){
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
    console.log(accountNumber + " "+name+" "+pan+" "+aadhar+" "+phone+" "+amount+" "+username+ " "+password);
    
    this.customer={"userId":undefined,
    "accountNumber":accountNumber,
    "customerName":name,
    "panNumber":pan,
    "aadharNumber":aadhar,
    "phoneNo":phone,
    "amount":amount,
    "username":username,
    "password":password,
    "transaction":[
      {
        "transactionId":undefined,
        "amount":undefined,
        "type":undefined,
        "userId":undefined,
        "date1":undefined
      }
    ]
  }

console.log(this.customer);

     this._customerService.addCustomer(this.customer).subscribe((customer)=>{
       console.log(customer);
     },(error)=>{
       console.log(error);
     })
     
     this._router.navigate(['/'])
      this._customerService.getCustomer().subscribe((customer)=>{
       this.customers=customer;
        console.log(this.customers);
     },(error)=>{
       console.log(error);
     })

     this.customers.push(this.customer);
     this._customerService.setCustomerList(this.customers);
     
    this.customers=this._customerService.getCustomerList();
    console.log("Final List: "+this.customers);
    }
    else{
      console.log("Not Inserted")
    }



}
}
