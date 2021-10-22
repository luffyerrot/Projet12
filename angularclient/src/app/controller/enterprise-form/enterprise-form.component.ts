import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EnterpriseService } from 'src/app/services/enterprise.service';

@Component({
  selector: 'app-enterprise-form',
  templateUrl: './enterprise-form.component.html',
  styleUrls: ['./enterprise-form.component.css']
})
export class EnterpriseFormComponent implements OnInit {

  form!: FormGroup;
  submitted = false;

  constructor(private enterpriseService: EnterpriseService, private formBuilder: FormBuilder) {}

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
        email: [
          '',
          [
            Validators.required,
            Validators.email
          ]
        ],
        password: [
          '',
          [
            Validators.required,
            Validators.minLength(6),
            Validators.maxLength(40)
          ]
        ],
        siret: [
          '',
          [
            Validators.required,
            Validators.pattern("^[0-9]*$"),
            Validators.minLength(14),
            Validators.maxLength(14)
          ]
        ],
        number: [
          '',
          [
            Validators.required,
            Validators.pattern("^[0-9]*$"),
            Validators.minLength(1),
            Validators.maxLength(3)
          ]
        ],
        adress: [
          '',
          [
            Validators.required,
            Validators.minLength(6),
            Validators.maxLength(30)
          ]
        ],
        country: [
          '',
          [
            Validators.required,
            Validators.minLength(4),
            Validators.maxLength(16)
          ]
        ],
        postcode: [
          '',
          [
            Validators.required,
            Validators.pattern("^[0-9]*$"),
            Validators.minLength(5),
            Validators.maxLength(5)
          ]
        ],
        phone_number: [
          '',
          [
            Validators.required,
            Validators.pattern("^[0-9]*$"),
            Validators.minLength(10),
            Validators.maxLength(10)
          ]
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
      this.enterpriseService.saveEnterprise(this.form.value, this.form.value);
    }
  }
}