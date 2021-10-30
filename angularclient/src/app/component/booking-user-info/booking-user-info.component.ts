import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Booking } from 'src/app/model/booking';

@Component({
  selector: 'app-booking-user-info',
  templateUrl: './booking-user-info.component.html',
  styleUrls: ['./booking-user-info.component.css']
})
export class BookingUserInfoComponent implements OnInit {

  bookings: Booking[] = []

  bookingUrl: string;

  constructor(private http: HttpClient) {
    this.bookingUrl = 'http://localhost:8080/booking/';
  }

  ngOnInit(): void {
    this.http.get<Booking[]>(this.bookingUrl + 'user').subscribe(data => {
      this.bookings = data;
    });
  }

}
