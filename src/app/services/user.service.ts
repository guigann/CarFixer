import { Injectable } from '@angular/core';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor() { 

    // criar logins para poder testar
    let users = [
      {
        id: 1, 
        name: "jose", 
        cpf: "456", 
        phone: "123456", 
        email:"teste@teste", 
        password: "123"
      },
      {
        id: 2, 
        name: "Davi", 
        cpf: "789", 
        phone: "123456", 
        email:"teste@teste", 
        password: "123"
      }];
    localStorage.setItem('users', JSON.stringify(users));

  }

  private _getUsers(): User[]{
    return JSON.parse(localStorage.getItem('users') || '[]');
  }


  checkUserLogin(cpf: string, password: string): User | null {
    let user: User | null = null;

    this._getUsers().forEach((u: User) => {
      if (u.cpf === cpf && u.password === password) {
        user = u;
      }
    });

    return user;
  }

  setLogged(user: User) {
    localStorage.setItem('userLogged', JSON.stringify(user));
  }

  logoutUser(){
    localStorage.removeItem('userLogged');
  }

  getLoggedUser(): User{
    let user: User = JSON.parse(localStorage.getItem('userLogged') || '{}');
    return user;
  }
}
