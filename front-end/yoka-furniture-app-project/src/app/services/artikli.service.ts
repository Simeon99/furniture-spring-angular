import { Injectable } from '@angular/core';
import {Namestaj} from '../models/namestaj.model';
import { AngularFireDatabase, AngularFireList, AngularFireObject } from '@angular/fire/database';
@Injectable({
  providedIn: 'root'
})
export class ArtikliService {

  listaKomoda: AngularFireList<Namestaj>;
  
  
  constructor(private db :AngularFireDatabase) { 

    this.listaKomoda = db.list('/namestaj/komode');

  }

  getAllKomode(): AngularFireList<Namestaj> {
    return this.listaKomoda;
  }

  get(artikalID:any):AngularFireObject<Namestaj>{
    
    return this.db.object('/products/'+artikalID);
  }

  

}
