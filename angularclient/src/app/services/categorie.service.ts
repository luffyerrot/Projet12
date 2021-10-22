import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Categorie } from '../model/categorie';

@Injectable({
  providedIn: 'root'
})
export class CategorieService {

  private categorieUrl: string;

  constructor(private http: HttpClient) {
    this.categorieUrl = 'http://localhost:8080/categorie/';
  }

  public findAll(): Observable<Categorie[]> {
    return this.http.get<Categorie[]>(this.categorieUrl);
  }

  public save(categorie: Categorie) {
    return this.http.put( this.categorieUrl + 'create',categorie);
  }
}