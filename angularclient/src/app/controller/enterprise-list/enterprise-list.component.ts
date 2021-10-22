import { Component, OnInit } from '@angular/core';
import { Enterprise } from 'src/app/model/enterprise';
import { EnterpriseService } from 'src/app/services/enterprise.service';

@Component({
  selector: 'app-enterprise-list',
  templateUrl: './enterprise-list.component.html',
  styleUrls: ['./enterprise-list.component.css']
})
export class EnterpriseListComponent implements OnInit {

  enterprises: Enterprise[] = [];

  constructor(private enterpriseService: EnterpriseService) {}

  ngOnInit(): void {
    this.enterpriseService.findAll().subscribe(data => {
      this.enterprises = data;
    });
  }
}