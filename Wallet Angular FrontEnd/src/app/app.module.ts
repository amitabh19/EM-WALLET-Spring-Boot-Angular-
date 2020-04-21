import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ListuserComponent } from './components/listuser/listuser.component';
import { UserFormComponent } from './components/user-form/user-form.component';
import { UserService } from './shared_service/user.service';
import { HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import { MainComponent } from './components/main/main.component';
import { AuthenticationComponent } from './components/authentication/authentication.component';
import { UserOpsComponent } from './components/user-ops/user-ops.component';
import { TransactionComponent } from './components/transaction/transaction.component';
import { DepositComponent } from './components/deposit/deposit.component';
import { WithdrawComponent } from './components/withdraw/withdraw.component';
import { TransListComponent } from './components/trans-list/trans-list.component';
import { SignupComponent } from './components/signup/signup.component';
@NgModule({
  declarations: [
    AppComponent,
    ListuserComponent,
    UserFormComponent,
    MainComponent,
    AuthenticationComponent,
    UserOpsComponent,
    TransactionComponent,
    DepositComponent,
    WithdrawComponent,
    TransListComponent,
    SignupComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [UserService],
  bootstrap: [AppComponent]
})
export class AppModule 
{
  
}
