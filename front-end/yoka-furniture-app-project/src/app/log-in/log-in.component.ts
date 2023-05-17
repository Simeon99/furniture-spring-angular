import { FirebaseAuthService } from './../firebase-auth.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.css']
})
export class LogInComponent implements OnInit{

  isSignedIn = false;
  constructor(public firebaseAuth: FirebaseAuthService) { }
  ngOnInit(): void {
    if(localStorage.getItem('user') != null)
    this.isSignedIn = true;
    else 
    this.isSignedIn = false;
  }

  async onSignup(email:string, password:string){
    
    
    await this.firebaseAuth.signUp(email,password);
    if(this.firebaseAuth.isLogedIn)
    this.isSignedIn = true;
  }
  async onSignin(email:string, password:string){
    
    
    await this.firebaseAuth.signIn(email,password);
    if(this.firebaseAuth.isLogedIn)
    this.isSignedIn = true;
    
  }
  handleLogOut(){
    this.isSignedIn = false
    this.firebaseAuth.logOut();
  }
  

  

}
