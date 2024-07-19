import {Component, Input} from '@angular/core';
import {NgForOf} from "@angular/common";
import {CorsoDto} from "../model/CorsoDto";
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-Weather',
  standalone: true,
  imports: [
    NgForOf,
    RouterLink
  ],
  templateUrl: './weather.component.html',
  styleUrl: './weather.component.css'
})
export class WeatherComponent {

  constructor() {
  }





}
