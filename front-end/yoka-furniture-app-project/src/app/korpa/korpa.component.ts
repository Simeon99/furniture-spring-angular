import { Component, OnInit } from '@angular/core';
import { AngularFireDatabase } from '@angular/fire/database';
import { KorpaService } from '../korpa.service';
import { map } from 'rxjs/operators';
import { ArtikliService } from '../services/artikli.service';
import { Namestaj } from '../models/namestaj.model';

@Component({
  selector: 'app-korpa',
  templateUrl: './korpa.component.html',
  styleUrls: ['./korpa.component.css']
})
export class KorpaComponent  {
  listaNamestaja?: any[] = [];
  listaOdabranihNamestaja?: any[];
  lista?: any[] ;
  korpaId?:any; 
  brojArtikala?:number;
  ukupnaCena:number = 0;
  korpaPuna = false;
  constructor(private db: AngularFireDatabase, private ks:KorpaService, private artikliService: ArtikliService) { 
    this.korpaId = localStorage.getItem('korpaId');
    if(this.korpaId != null){
      
     /* this.korpaPuna = true;
      db.list('/korpa/' + this.korpaId+'/artikli').snapshotChanges().subscribe(r =>{
        console.log("rezultat",r);
        this.listaNamestaja = r;
        console.log("lista namestaja"+this.listaNamestaja);
        if(this.listaNamestaja.length > 0 )*/
        this.preuzimanjeNamestajaKorpa();
        //this.preuzimanjeNamestaja();
        
      //})
      
    }
    
    
   // db.list(ks.getCart(korpaId))
  }

 /* async preuzimanjeNamestaja(){
 this.artikliService.getAllKomode().snapshotChanges().pipe(
      map(changes=>
        
        changes.map( c =>
          ({ 
             key: c.payload.key, ...c.payload.val()  })
          )
        
        )
    ).subscribe(data => {
      this.lista = [];
      this.listaOdabranihNamestaja = data;
      console.log("Size",this.listaOdabranihNamestaja.length);
      
      this.listaOdabranihNamestaja?.forEach((e, index) => {
        console.log("Eventttt",e.key);
        
        if(this.listaNamestaja?.some(ev => ev.key == e.key)){
          console.log("Usloooooo",e);
          this.lista?.push(e);
          this.ukupnaCena =this.ukupnaCena + e.cena;
          
          console.log("KKKKKK",this.lista);
   
        }else {
          
         // this.listaOdabranihNamestaja?.splice(index ,1)
          
 
        }
      })
   
    })
    
    

    
  }*/

  ukloniArtikal(key:any){
    console.log("Usoooo",key);
    
    this.ks.ukloniNamestaj(key, this.korpaId);
    this.lista?.forEach(e =>{
      if(e.key == key){
        console.log("Jednako jeeee");
        this.ukupnaCena = this.ukupnaCena - e.cena;
      }
    })
   // this.ukupnaCena = this.ukupnaCena - 
  }

  submit(f:any){
    console.log("Lista",this.lista);
    
    console.log("Ad",f.value);
    this.ks.kreirajPorudzbinu(f.value,this.lista);
    this.lista = [];
    this.ukupnaCena = 0;
    
  }
   preuzimanjeNamestajaKorpa(){
   // console.log("Uslooooo");
    
    this.ks.getAllFromCart(this.korpaId).snapshotChanges().pipe(
      map(changes=>
        
        changes.map( c =>
          ({  key: c.payload.key, ...c.payload.val(), isEdited: false })
          )
        
        )
    ).subscribe(data => {
      console.log("aaaa",data);
      console.log("aIddddaaa",this.korpaId);
      
      this.lista = data;
      this.brojArtikala = this.lista.length;
      this.ukupnaCenaArt();
    })
  }

  ukupnaCenaArt(){
    this.ukupnaCena = 0;
    this.lista?.forEach(e =>{
      this.ukupnaCena = this.ukupnaCena + e.cena;
    })
  }

  createPorudzbina(){
    console.log();
    
  }
}
