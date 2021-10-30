import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Categorie } from 'src/app/model/categorie';
import { CategorieService } from 'src/app/services/categorie.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-restrictions',
  templateUrl: './user-restrictions.component.html',
  styleUrls: ['./user-restrictions.component.css']
})
export class UserRestrictionsComponent implements OnInit {

  form!: FormGroup;
  categorieForm!: FormGroup;
  submitted = false;
  numbers: number[] = [];
  categories: Categorie[] = [];
  userCategories = { id: '', username: '', email: '', linkimg: '', password: '', categories: [{
    id: '', name: '', description: ''
  }]};

  constructor(private userService: UserService, private categorieService: CategorieService) {}

  ngOnInit(): void {
    this.userService.findMe().subscribe(data => {
      this.userCategories = data;
      this.categorieService.findAll().subscribe(data => {
        this.categories = data;
        for (let j = 0; j < this.userCategories.categories.length; j++) {
          for (let i = 0; i < this.categories.length; i++) {
            if (this.userCategories.categories[j].id == this.categories[i].id) {
              this.numbers.push(i);
            }
          }
        }
      });
    });
  }

  addCategorie(categorie: Categorie) {
    this.userService.addCategorie(categorie);
  }

  deleteCategorie(id: string) {
    this.userService.deleteCategorie(id);
  }
}
