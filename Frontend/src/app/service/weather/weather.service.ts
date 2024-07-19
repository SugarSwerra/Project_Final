import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Router} from "@angular/router";
import {LoginRequest} from "../../model/loginRequest";
import {catchError, Observable, retry, throwError} from "rxjs";
import {WeatherRequest} from "../../model/WeatherRequest";

@Injectable({
  providedIn: 'root'
})
export class WeatherService {

  constructor(private http: HttpClient, private router: Router) {
  }

  private handleError(error: HttpErrorResponse) {
    if (error.status === 0) {
      console.error('An error occurred: ' +error.error);
    } else {
      console.error(`Backend returned error: ${error.status}, body was: `, error.error);
    }
    return throwError(() => new Error('Something went wrong'));
  }

  getWeather(weather: WeatherRequest): Observable<string> {
    return this.http.get<string>("http://localhost:8080/api/weather").pipe(retry(3)).pipe(
      catchError(this.handleError.bind(this))
    );



  }

}
