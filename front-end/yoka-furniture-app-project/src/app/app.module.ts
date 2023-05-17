
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatIconModule} from '@angular/material/icon';
import {MatBadgeModule} from '@angular/material/badge';
import { RouterModule} from '@angular/router';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { environment } from 'src/environments/environment';
import { AngularFireModule } from '@angular/fire';
import { AngularFirestoreModule } from '@angular/fire/firestore';



import { NamestajComponent } from './namestaj/namestaj.component';
import { HeaderComponent } from './header/header.component';
import { HomeComponent } from './home/home.component';
import { KorpaComponent } from './korpa/korpa.component';
import { ArtikliComponent } from './artikli/artikli.component';
import { ONamaComponent } from './o-nama/o-nama.component';
import { BlogComponent } from './blog/blog.component';
import { PorudzbineComponent } from './porudzbine/porudzbine.component';
import { FooterComponent } from './footer/footer.component';
import { LogInComponent } from './log-in/log-in.component';
import { PregledNamestajaComponent } from './pregled-namestaja/pregled-namestaja.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { KorpaService } from './korpa.service';
import { FirebaseAuthService } from './firebase-auth.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AdminUpravaComponent } from './admin-uprava/admin-uprava.component';
import { CrudService } from './crud.service';
import { AngularFireDatabaseModule } from '@angular/fire/database';






@NgModule({
  declarations: [
    AppComponent,
    NamestajComponent,
    HeaderComponent,
    HomeComponent,
    KorpaComponent,
    ArtikliComponent,
    ONamaComponent,
    BlogComponent,
    PorudzbineComponent,
    FooterComponent,
    LogInComponent,
    PregledNamestajaComponent,
    NotFoundComponent,
    AdminUpravaComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatIconModule,
    MatBadgeModule,
    AngularFireModule.initializeApp(environment.firebase),
    AngularFirestoreModule,
    FormsModule,
    ReactiveFormsModule,
    AngularFireDatabaseModule,
    NgbModule ,
    RouterModule.forRoot([
      {path: '', component: HomeComponent},
      {path: 'pregledNamestaja/:id', component: PregledNamestajaComponent},
      {path: 'artikli', component: ArtikliComponent},
      {path: 'korpa', component: KorpaComponent},
      {path: 'oNama', component: ONamaComponent},
      {path: 'blog', component: BlogComponent},
      {path: 'porudzbine', component: PorudzbineComponent},
      {path: 'login', component: LogInComponent},
      {path: 'admin-uprava', component: AdminUpravaComponent},
      {path: '**', component: NotFoundComponent}
      
    ])
  ],
  providers: [

    KorpaService,
    FirebaseAuthService,
    CrudService

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
