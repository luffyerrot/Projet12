
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-info',
  templateUrl: './user-info.component.html',
  styleUrls: ['./user-info.component.css']
})
export class UserInfoComponent implements OnInit {
  
  form!: FormGroup;
  inputdisabled = true;
  submitted = false;
  user = {id: '', username: '', email: '', linkimg: '', password: '', userInformations: {
    id: '', adress: '', birthday: '', country: '', firstname: '', name: '', number: '', phone_number: '', postcode: ''
  }};

  constructor(private userService: UserService, private formBuilder: FormBuilder) {}

  ngOnInit(): void {
    this.userService.findMe().subscribe(data => {
      this.user = data;
      this.form = this.formBuilder.group(
        {
          id: [
            this.user.id
          ],
          linkimg: [
            this.user.linkimg
          ],
          password: [
            this.user.password
          ],
          username: [
            this.user.username,
            [
              Validators.required,
              Validators.minLength(4),
              Validators.maxLength(20)
            ]
          ],
          email: [
            this.user.email,
            [
              Validators.required,
              Validators.email
            ]
          ],
          name: [
            this.user.userInformations && this.user.userInformations.name ? this.user.userInformations.name : '',
            [
              Validators.minLength(4),
              Validators.maxLength(20)
            ]
          ],
          firstname: [
            this.user.userInformations && this.user.userInformations.firstname ? this.user.userInformations.firstname : '',
            [
              Validators.minLength(4),
              Validators.maxLength(20)
            ]
          ],
          adress: [
            this.user.userInformations && this.user.userInformations.adress ? this.user.userInformations.adress : '',
            [
              Validators.minLength(6),
              Validators.maxLength(30)
            ]
          ],
          number: [
            this.user.userInformations && this.user.userInformations.number ? this.user.userInformations.number : '',
            [
              Validators.pattern("^[0-9]*$"),
              Validators.minLength(1),
              Validators.maxLength(3)
            ]
          ],
          birthday: [
            this.user.userInformations && this.user.userInformations.birthday ? this.user.userInformations.birthday.substring(0, 10) : ''
          ],
          country: [
            this.user.userInformations && this.user.userInformations.country ? this.user.userInformations.country : '',
            [
              Validators.minLength(4),
              Validators.maxLength(16)
            ]
          ],
          postcode: [
            this.user.userInformations && this.user.userInformations.postcode ? this.user.userInformations.postcode : '',
            [
              Validators.pattern("^[0-9]*$"),
              Validators.minLength(5),
              Validators.maxLength(5)
            ]
          ],
          phone_number: [
            this.user.userInformations && this.user.userInformations.phone_number ? this.user.userInformations.phone_number : '',
            [
              Validators.pattern("^[0-9]*$"),
              Validators.minLength(10),
              Validators.maxLength(10)
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
      this.userService.updateUser(this.form.value, this.form.value);
    }
  }

  onUpdate() {
    this.inputdisabled = false;
  }
}
