import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent implements OnInit{

  user:User = new User();
  userArray:User[] = [];

  constructor(private loginService:LoginService, private route:Router){}

  onSignup() {
    const isUserExist =this.userArray.find(m=> m.email == this.user.email);
    if(isUserExist == undefined){
      alert("Account created ")
      this.userArray.push(this.user);
      localStorage.setItem('signupUsers',JSON.stringify(this.userArray)); 
      console.log(this.userArray); 
    }else{
      alert("username exists")
      console.log(this.userArray)
    }
    this.user = new User();   
    }

  onLogin() {
    this.loginService.onLogin(this.user).subscribe((res:any)=>{
      console.log('res',res);
      localStorage.setItem('token',res.token);
      this.route.navigateByUrl('/employees');
    })

    /* const isUserExist =this.userArray.find(m=> m.userId == this.user.userId && m.password == this.user.password);
    if(isUserExist != undefined){
      alert("user login succesfully")
    }else{
      alert("wrong credentials")
    }
    this.user = new User(); */

    }


  ngOnInit(): void {
    const localData = localStorage.getItem('signupUsers');
    if(localData != null){
      this.userArray = JSON.parse(localData);
    }
  }


}
