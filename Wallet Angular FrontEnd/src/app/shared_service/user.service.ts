import { Injectable } from '@angular/core';
import {  HttpClient, HttpHeaders, HttpRequest } from '@angular/common/http';
//import {Http,Response,Headers,RequestOptions} from '@angular/http';
import { User } from '../user';
import { Observable } from 'rxjs';
import {  Trans } from '../Trans';
import { Transaction } from '../Transaction';
import { map, catchError } from 'rxjs/operators';
//import { RequestOptions } from 'https';
//import {Http,Response,Headers,RequestOptions} from '@angular/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  baseUrl = "http://localhost:8090/walletBackendBackup/api";
 
  private user = new User();
  username:string;
 
  private options = {
    headers: new HttpHeaders().append('Content-Type', 'application/json'),
  }
  
  constructor(private http: HttpClient){}

  addTrans(t:Transaction)
  {
    return this.http.post(this.baseUrl+"/addTrans",JSON.stringify(t),this.options); 

  }
  
  addCustomer(customer:User){
    return this.http.post(this.baseUrl+"/addCustomer",JSON.stringify(customer),this.options); 
  }

 createUser(user:User){
   
    user.accNo="aaaa";
    console.log(user);
    return this.http.post(this.baseUrl+'/user',JSON.stringify(user),this.options);  
  }
  
  getUsers():Observable<User[]>
  {
    
    return this.http.get<User[]>(this.baseUrl+'/users');
  }

  getUsers2(): Promise<any>
  {
  return this.http.get(this.baseUrl+'/users').toPromise();
  }

  getTransById(id:number):Observable<Transaction[]>
  {
    return this.http.get<Transaction[]>(this.baseUrl+'/trans/'+id);
  }

  getUserById(id:number):Observable<User>
  {
    return this.http.get<User>(this.baseUrl+'/user/'+id);
  }
  
  deleteUserById(id:number)
  {
    return this.http.delete(this.baseUrl+'/user/'+id);
  }

  errorHandler(error:Response){
    return Observable.throw(error||"SERVER ERROR");  
    }

  setter(user:User)
  {
    this.user = user;
  }

  getter(){
    return this.user;
  }   
   updateUser(user:User){

    return this.http.put(this.baseUrl+'/user',JSON.stringify(user));
  }

 
  

}
