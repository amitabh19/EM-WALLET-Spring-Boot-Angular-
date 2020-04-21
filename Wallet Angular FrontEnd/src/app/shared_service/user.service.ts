import { Injectable } from '@angular/core';
import { HttpHeaders, HttpRequest, HttpClient } from '@angular/common/http';
//import {Http,Response,Headers,RequestOptions} from '@angular/http';
import { User } from '../user';
import { Observable } from 'rxjs';
import {  Trans } from '../Trans';
import { Transaction } from '../Transaction';
import { map, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  baseUrl = "http://localhost:8090/walletBackend/api";
  //private headers = new HttpHeaders({'Content-type':'application/json'});
 /* 
  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }*/
  private user = new User();
  private headers = new Headers({'Content-Type':'application/json'});
  //private options = new RequestOptions({headers:this.headers})
  
  constructor(private http: HttpClient){}

  /*
  addCustomer(customer:User){
    return this.ht.post(this.baseUrl+"/user",JSON.stringify(customer),this.options).pipe(map((response: Response) => response.json()))
    .pipe(catchError(this.errorHandler));
  }
*/
  getUsers():Observable<User[]>
  {
    return this.http.get<User[]>(this.baseUrl+'/users');
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

  createUser(user:User){
   
    user.accNo="aaaa";
    console.log(user);
    //return this.http.get(this.baseUrl+'/createuser'+'/'+ user.name+ '/'+ user.emailId+ '/'+ user.mobileNo+ '/'+ user.balance+ '/'+ user.dob+ '/'+user.accNo+ '/'+ user.username+ '/'+ user.password);
    return this.http.put(this.baseUrl+'/user',JSON.stringify(user));  
  }
   
   updateUser(user:User){

    return this.http.put(this.baseUrl+'/user',JSON.stringify(user));
  }

  performTrans(t:Trans)
  {
    return this.http.get(this.baseUrl+'/user/'+t.sid+'/'+t.rid+'/'+t.amount);
  }

  depo(t:Trans)
  {
    return this.http.get(this.baseUrl+'/depo/'+t.sid+'/'+t.amount);
  }

  with(t:Trans)
  {
    return this.http.get(this.baseUrl+'/with/'+t.sid+'/'+t.amount);
  }
  

}
