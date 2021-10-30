import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Booking } from 'src/app/model/booking';

@Component({
  selector: 'app-booking-enterprise-info',
  templateUrl: './booking-enterprise-info.component.html',
  styleUrls: ['./booking-enterprise-info.component.css']
})
export class BookingEnterpriseInfoComponent implements OnInit {

  bookings: Booking[] = []

  bookingUrl: string;

  constructor(private http: HttpClient) {
    this.bookingUrl = 'http://localhost:8080/booking/';
  }

  ngOnInit(): void {
    this.http.get<Booking[]>(this.bookingUrl + 'enterprise').subscribe(data => {
      this.bookings = data;
    });
  }
}