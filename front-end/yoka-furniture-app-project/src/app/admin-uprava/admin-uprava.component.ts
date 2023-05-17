import { CrudService } from './../crud.service';
import { ArtikliService } from './../services/artikli.service';
import { Component, OnInit } from '@angular/core';
import { map } from 'rxjs/operators';
import { Namestaj } from '../models/namestaj.model';
@Component({
  selector: 'app-admin-uprava',
  templateUrl: './admin-uprava.component.html',
  styleUrls: ['./admin-uprava.component.css']
})
export class AdminUpravaComponent implements OnInit {

  key: string =" ";
  naziv?: string;
  opis?: string;
  cena?: number;
  sirina?: number;
  visina?: number;
  dubina?: number;
  slika?: string;
  namestaj?: Namestaj;
  listaKomoda?: any[];
  

  constructor(private crudService: CrudService, private artikliService: ArtikliService) { }

  createArtikal(){
    alert("Da li ste sigurni da želite da kreirate ovaj artikal")
  
    let artikal = {

      cena: this.cena,
      dubina: this.dubina,
      naziv: this.naziv,
      opis: this.opis,
      sirina: this.sirina,
      slika: this.slika,
      visina: this.visina,



    };
    


    this.crudService.create(artikal, this.key);
    
   
  }

  ngOnInit(): void {
    this.preuzimanjeNamestaja();
    

  }

  preuzimanjeNamestaja(){
    
    this.artikliService.getAllKomode().snapshotChanges().pipe(
      map(changes=>
        
        changes.map( c =>
          ({  key: c.payload.key, ...c.payload.val(), isEdited: false })
          )
        
        )
    ).subscribe(data => {
      this.listaKomoda = data;
      
    })
  }

  editArtikal(artikal:any){

    artikal.isEdited = true;

  }

  updateArtikal(artikal:any){
    this.crudService.updatNamestaj(artikal);
  }

  deleteArtikal(key:any){
    this.crudService.deleteNamestaj(key);
  }
}
