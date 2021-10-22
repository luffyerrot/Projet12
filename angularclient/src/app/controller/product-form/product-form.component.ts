import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { EnterpriseService } from 'src/app/services/enterprise.service';

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.css']
})
export class ProductFormComponent implements OnInit {

  form!: FormGroup;
  submitted = false;

  constructor(private enterpriseService: EnterpriseService, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.form = this.formBuilder.group(
      {
        name: [
          '',
          [
            Validators.required,
            Validators.minLength(4),
            Validators.maxLength(20)
          ]
        ],
        linkimg: [
          '/assets/images/products/unknow.png'
        ],
        description: [
          '',
          [
            Validators.required,
            Validators.minLength(10),
            Validators.maxLength(255)
          ]
        ]
      }
    );
  }

  onSubmit() {
    this.submitted = true;
    if (this.form.valid) {
      this.enterpriseService.saveProduct(this.form.value);
    }
  }
}