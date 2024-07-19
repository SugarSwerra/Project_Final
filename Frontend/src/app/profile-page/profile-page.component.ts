import { Component } from '@angular/core';
import {UserService} from "../service/user/user.service";
import {UserDTO} from "../model/UserDTO";
import {Router} from "@angular/router";
import {FormsModule, NgForm} from "@angular/forms";
import {UserUpdateDTO} from "../model/UserUpdateDTO";

@Component({
  selector: 'app-profile-page',
  standalone: true,
  imports: [
    FormsModule
  ],
  templateUrl: './profile-page.component.html',
  styleUrl: './profile-page.component.css'
})
export class ProfilePageComponent {
  constructor(private userService: UserService, private router: Router) {
  }

  nome = "";
  cognome = "";
  email = "";

  userUpdateDTO: UserUpdateDTO = new UserUpdateDTO();

  ngOnInit(): void {
    this.userService.findUserByMail(localStorage.getItem("user_email")).subscribe((user: UserDTO) => {
      if (user.nome == null || user.cognome == null || user.email == null) {
        this.nome = "";
        this.cognome = "";
        this.email = "";
      } else {
        this.nome = user.nome;
        this.cognome = user.cognome;
        this.email = user.email;
      }
    })
  }


}


