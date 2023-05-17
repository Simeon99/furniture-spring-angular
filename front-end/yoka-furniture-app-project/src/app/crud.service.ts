import { AngularFirestore } from '@angular/fire/firestore';
import { Injectable } from '@angular/core';
import { AngularFireDatabase, AngularFireObject } from '@angular/fire/database';
import { Namestaj } from './models/namestaj.model';

export interface Artikal {
  key: string;
  naziv: string;
  opis: string;
  cena: number;
  sirina: number;
  visina: number;
  dubina: number;
  slika: string;
}

@Injectable({
  providedIn: 'root'
})
export class CrudService {

  studentRef?: AngularFireObject<any>;
  constructor(private db: AngularFireDatabase) { }

  createNewArtikal(){

  }

  AddStudent(student: Artikal) {
   /* this
    this.studentsRef.push({
      key: student.key,
      naziv: student.naziv,
      opis: student.opis,
      cena: student.cena,
      sirina: student.sirina,
      visina: student.visina,
      dubina: student.dubina,
      slika: student.slika
    })*/
  }

  create(artikal:any, key:any) {
     this.db.list('/namestaj/komode').push(artikal);
    //return this.db.list('korpa/artikli');
  }
  updatNamestaj(artikal:any){
    let artikalObj ={
      naziv: artikal.naziv,
      opis: artikal.opis,
      cena: artikal.cena,
      sirina: artikal.sirina,
      visina: artikal.visina,
      dubina: artikal.dubina,
      slika: artikal.slika
    }
    this.db.list('/namestaj/komode').update(artikal.key ,artikalObj);
  }

  deleteNamestaj(key:any){
    this.db.list('/namestaj/komode').remove(key);
    
  }

}
