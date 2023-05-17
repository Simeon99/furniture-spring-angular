import { Namestaj } from './../models/namestaj.model';
import { Component, OnInit } from '@angular/core';
import { AngularFireDatabase} from '@angular/fire/database';

import {ArtikliService} from '../services/artikli.service'
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-artikli',
  templateUrl: './artikli.component.html',
  styleUrls: ['./artikli.component.css']
})
export class ArtikliComponent  {

  images = ['assets/images/komode/Drvena komoda 1.jpg','assets/images/komode/Drvena komoda 2.jpg'];
  list: any[]= ["artikal1", "artikal2", "artikal3","artikal4"];
  
  currentNamestaj?: Namestaj;

  listaKomoda?: any[];
  listaStolica?: any[];

  constructor(private db :AngularFireDatabase, private artikliService: ArtikliService) {

   }
   ngOnInit(): void {
    this.preuzimanjeNamestaja();
  }
    preuzimanjeNamestaja(){
      this.artikliService.getAllKomode().snapshotChanges().pipe(
        map(changes=>
          
          changes.map( c =>
            ({  key: c.payload.key, ...c.payload.val()  })
            )
          
          )
      ).subscribe(data => {
        this.listaKomoda = data;
      })
    }

    setActiveTutorial(namestaj: Namestaj, index: number): void {
      this.currentNamestaj = namestaj;
      
    }
 
}


/*
    db.list('/namestaj/komode').snapshotChanges().subscribe(listaNamestaja =>{
      this.listaKomoda = listaNamestaja;
      console.log(this.listaKomoda);
    });
    db.list('/namestaj/stolice').snapshotChanges().subscribe(listaStolica =>{
     this.listaStolica = listaStolica;
     console.log(this.listaStolica);
   });
*/