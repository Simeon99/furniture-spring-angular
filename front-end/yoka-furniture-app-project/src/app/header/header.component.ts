import { AngularFireAuth } from '@angular/fire/auth';
import { FirebaseAuthService } from './../firebase-auth.service';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';

@Component({
  selector: 'header-menu',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  isLogIn?: boolean;
  state?:any;
  @Output() isLogout = new EventEmitter<void>();
  constructor(public firebaseAuth:FirebaseAuthService, public afAuth: AngularFireAuth) {
    afAuth.authState.subscribe(x => {
      if(x !=null){
        this.isLogIn = true
      }else{
        this.isLogIn = false
      }
    } );
    
    
   /* if(this.state != null){

      this.isLogIn = true
    }this.isLogIn = false
    /*if(afAuth.authState.subscribe(x => console.log("Ulgovan korisnik",x)) !=null){
      this.isLogIn = true
    }else this.isLogIn = false*/
   }

  ngOnInit(): void {
  }

  public isMenuCollapsed = true;

  logoput(){
    this.firebaseAuth.logOut()
    this.isLogout.emit();
  }

}
