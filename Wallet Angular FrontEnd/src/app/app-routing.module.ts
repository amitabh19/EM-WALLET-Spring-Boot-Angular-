import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ListuserComponent } from './components/listuser/listuser.component';
import { UserFormComponent } from './components/user-form/user-form.component';
import { MainComponent } from './components/main/main.component';
import { UserOpsComponent } from './components/user-ops/user-ops.component';
import { AuthenticationComponent } from './components/authentication/authentication.component';
import { TransactionComponent } from './components/transaction/transaction.component';
import { DepositComponent } from './components/deposit/deposit.component';
import { WithdrawComponent } from './components/withdraw/withdraw.component';
import { TransListComponent } from './components/trans-list/trans-list.component';
import { SignupComponent } from './components/signup/signup.component';


const routes: Routes = [
  {path:'lu', component:ListuserComponent},
  {path:'uf', component:UserFormComponent},
  {path:'', component:MainComponent},
  {path:'uops', component:UserOpsComponent},
  {path:'au', component:AuthenticationComponent},
  {path:'trans', component:TransactionComponent},
  {path:'depo', component:DepositComponent},
  {path:'with', component:WithdrawComponent},
  {path:'tlist', component:TransListComponent},
  {path:'sign', component:SignupComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule 
{
  
}
