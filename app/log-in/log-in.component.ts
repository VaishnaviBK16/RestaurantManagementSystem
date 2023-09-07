import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationServiceService } from '../authentication-service.service';

@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.css']
})
export class LogInComponent {

  username = '';
  password = '';
  invalidLogin = false

  constructor(private router: Router,
    private loginservice:AuthenticationServiceService ) { }

  checkLogin() {
    if (this.loginservice.authenticate(this.username, this.password)
    ) {
      this.router.navigate(['restaurant']);
      console.log("navigate..");
      this.invalidLogin = false;
    } else
      this.invalidLogin = true;
  }

}
