import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { EnterpriseService } from 'src/app/services/enterprise.service';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-enterprise-info',
  templateUrl: './enterprise-info.component.html',
  styleUrls: ['./enterprise-info.component.css']
})
export class EnterpriseInfoComponent implements OnInit {

  form!: FormGroup;
  inputdisabled = true;
  submitted = false;
  enterprise = {id: '', name: '', email: '', linkimg: '', password: '', enterpriseInformations: {
    id: '', siret: '', adress: '', country: '', number: '', postcode: '', description: '', phone_number: ''
  }};

  constructor(private enterpriseService: EnterpriseService, private loginService: LoginService, private formBuilder: FormBuilder) {}

  ngOnInit(): void {
    this.loginService.findEnterprise().subscribe(data => {
      this.enterprise = data;
      this.form = this.formBuilder.group(
        {
          id: [
            this.enterprise.id
          ],
          linkimg: [
            this.enterprise.linkimg
          ],
          password: [
            this.enterprise.password
          ],
          name: [
            this.enterprise.name,
            [
              Validators.required,
              Validators.minLength(4),
              Validators.maxLength(20)
            ]
          ],
          email: [
            this.enterprise.email,
            [
              Validators.required,
              Validators.email
            ]
          ],
          siret: [
            this.enterprise.enterpriseInformations.siret,
            [
              Validators.required,
              Validators.pattern("^[0-9]*$"),
              Validators.minLength(14),
              Validators.maxLength(14)
            ]
          ],
          number: [
            this.enterprise.enterpriseInformations.number,
            [
              Validators.required,
              Validators.pattern("^[0-9]*$"),
              Validators.minLength(1),
              Validators.maxLength(3)
            ]
          ],
          adress: [
            this.enterprise.enterpriseInformations.adress,
            [
              Validators.required,
              Validators.minLength(6),
              Validators.maxLength(30)
            ]
          ],
          country: [
            this.enterprise.enterpriseInformations.country,
            [
              Validators.required,
              Validators.minLength(4),
              Validators.maxLength(16)
            ]
          ],
          postcode: [
            this.enterprise.enterpriseInformations.postcode,
            [
              Validators.required,
              Validators.pattern("^[0-9]*$"),
              Validators.minLength(5),
              Validators.maxLength(5)
            ]
          ],
          phone_number: [
            this.enterprise.enterpriseInformations.phone_number,
            [
              Validators.required,
              Validators.pattern("^[0-9]*$"),
              Validators.minLength(10),
              Validators.maxLength(10)
            ]
          ],
          description: [
            this.enterprise.enterpriseInformations.description,
            [
              Validators.required,
              Validators.minLength(10),
              Validators.maxLength(255)
            ]
          ]
        }
      );
    });
  }

  onSubmit() {
    this.submitted = true;
    if (this.form.valid) {
      this.inputdisabled = true;
      this.enterpriseService.updateEnterprise(this.form.value, this.form.value);
    }
  }

  onUpdate() {
    this.inputdisabled = false;
  }
}
