import { Injectable } from '@angular/core';
import { AngularFireDatabase, AngularFireList } from '@angular/fire/database';
import { AngularFirestore } from '@angular/fire/firestore';
import { Namestaj } from './models/namestaj.model';

@Injectable({
  providedIn: 'root'
})
export class KorpaService {
  idNamestaja: any;
  subscription: any;
  listaKomoda?: AngularFireList<Namestaj>;
  constructor(private db: AngularFireDatabase, private dbf: AngularFirestore) { 
    
  }


  getAllFromCart(korpaId: string):AngularFireList<Namestaj>{
   // this.listaKomoda = this.db.list('/korpa/'+korpaId+'artikli');
    console.log("IDDDD:", this.listaKomoda );
    
    return this.db.list('/korpa/'+korpaId+'/artikli');
  }

  create() {
    return this.db.list('/korpa').push({
      dataCreated: new Date().getTime()
    })
    //return this.db.list('korpa/artikli');
  }

  getCart(korpaId: string) {
    return this.db.object('/korpa/' + korpaId);
  }

  getOrCreateKorpa(artikal: any) {
    let korpaId = localStorage.getItem('korpaId');
    if (!korpaId) {
       this.create().then(result => {
        localStorage.setItem('korpaId', result.key);
        let korpaIdnew = localStorage.getItem('korpaId');
        this.db.list('/korpa/' + korpaIdnew + '/artikli' ).set(artikal.key, artikal);
      });
    }
    else {
      console.log("Idddda" + korpaId);
      this.db.list('/korpa/' + korpaId + '/artikli' ).set(artikal.key, artikal);
      
    }
  }

 /* async addToCart(artikal: any) {
    let korpaId = await this.getOrCreateKorpa();
    let kolicinaA$ = this.db.object('/korpa/' + korpaId + '/artikli/' + artikal.key + '/kolicina');
    let trenutnaKolicina: any;
    kolicinaA$.snapshotChanges().subscribe(k => {
      console.log(k.payload.val());
      trenutnaKolicina = k.payload.val();

    })

    let item$ = await this.db.list('/korpa/' + korpaId + '/artikli' ).set(artikal.key, artikal);
    /*this.subscription = item$.snapshotChanges().subscribe(item => {

      item$.push({ artikal })
    })*/

  //}

  ukloniNamestaj(key:any, korpaId:any){
    console.log("Kljucc"+key);
    
    this.db.list('/korpa/'+korpaId+'/artikli').remove(key);
  }

  kreirajPorudzbinu(adresa:any,artikli:any){
    this.db.list('/porudzbine').push(
     {adresa,artikli}
    )
  }

  


}
