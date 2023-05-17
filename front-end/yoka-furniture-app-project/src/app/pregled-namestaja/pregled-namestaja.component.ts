import { ArtikliComponent } from './../artikli/artikli.component';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ArtikliService } from '../services/artikli.service';
import { Subscription } from 'rxjs';
import { map } from 'rxjs/operators';
import { Namestaj } from './../models/namestaj.model';
import { KorpaService } from '../korpa.service';
import { AngularFireDatabase } from '@angular/fire/database';


@Component({
  selector: 'app-pregled-namestaja',
  templateUrl: './pregled-namestaja.component.html',
  styleUrls: ['./pregled-namestaja.component.css']
})
export class PregledNamestajaComponent implements  OnDestroy {

  artikli?:any[];
  trenutniArtikal:any;
  subscription?: Subscription;
  id: any;
  constructor( 

    private route: ActivatedRoute,
    private artikliSevis :ArtikliService,
    private korpaServis :KorpaService,
    private db: AngularFireDatabase
  ) {

     this.id = this.route.snapshot.paramMap.get('id');
    if(this.id) this.subscription = this.artikliSevis.getAllKomode().snapshotChanges().pipe(
      map(changes=>
        
        changes.map( c =>
          ({  key: c.payload.key, ...c.payload.val()  })
          )
        
        )
    ).subscribe(data => {
      this.artikli = data;
    })
    
    
   }
  ngOnDestroy(): void {
    this.subscription?.unsubscribe();
  }
  

  addToCart(artikal: Namestaj){

    this.korpaServis.getOrCreateKorpa(artikal);

   /* let korpaId =   localStorage.getItem('korpaId');
    if(!korpaId) {
      this.korpaServis.create().then( result =>{
        localStorage.setItem('korpaId', result.key);
      });



    }  
    else{

    }*/
   // this.korpaServis.create().push({ artikal, kolicina: 1});
  }


}
