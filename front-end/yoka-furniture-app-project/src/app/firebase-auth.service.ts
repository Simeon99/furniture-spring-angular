import { Injectable } from '@angular/core';
import {AngularFireAuth} from '@angular/fire/auth'
import { SimpleOuterSubscriber } from 'rxjs/internal/innerSubscribe';
@Injectable({
  providedIn: 'root'
})
export class FirebaseAuthService {
  isLogedIn = false;
  constructor(public firebaseAuth: AngularFireAuth) {
    firebaseAuth.authState.subscribe(x=>{console.log("status:",x);
    })
   }

  async signIn( email:string, password:string){
    console.log("OOOOOOOOOOOOO");
    
    await this.firebaseAuth.signInWithEmailAndPassword(email, password)
    .then(res =>{
      this.isLogedIn=true
      console.log("Emailaaaaaaaaaaaaaaaaa",password);
      localStorage.setItem('user',JSON.stringify(res.user))
    }, rej=>console.log(rej)
    )
    
  }

  async signUp( email:string, password:string){
   
    
    await this.firebaseAuth.createUserWithEmailAndPassword(email, password)
    .then(res =>{
      this.isLogedIn=true;
      localStorage.setItem('user',JSON.stringify(res.user))
    },rej=>console.log(rej))
  }

  logOut(){
    this.firebaseAuth.signOut();
    localStorage.removeItem('user');
  }
  
}
