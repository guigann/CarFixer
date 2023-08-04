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
        email: "teste@teste",
        password: "123",
        permission: 0 
      },
      {
        id: 2,
        name: "Davi",
        cpf: "789",
        phone: "123456",
        email: "teste@teste",
        password: "123",
        permission: 0
      }];
    localStorage.setItem('users', JSON.stringify(users));

  }


  private _getUsers(): User[] {
    return JSON.parse(localStorage.getItem('users') || '[]');
  }

  getAllCliente(): User[]{
    return this._getUsers().filter((element: User) => element.permission === false); 
  }

  save(user: User): boolean{
    let users = this._getUsers();
  
    if(user.id === 0){
      if(this.isEmailValid(user.email) && this.isCpfValid(user.cpf)){
        user.id = (new Date().getTime() / 1000) * Math.random();
        users.push(user);
      } else {
        return false
      }
    } else{
      let posicao = users.findIndex((elemento: User) => elemento.id == user.id);
      users.splice(posicao, 1, user);
    }

    localStorage.setItem('users', JSON.stringify(users));
    return true;
  }

  delete(id: number){
    let userList = this._getUsers();
    userList = userList.filter((elemento: User) => elemento.id !== id);
    localStorage.setItem('users', JSON.stringify(userList));
  }

  getById(id: number){
    let userList = this._getUsers();

    return userList.find((elemento: User) => elemento.id == id);
  }

  isEmailValid(email: string): boolean{
    let isValid: boolean = true;

    this._getUsers().forEach((u: User) => {
      if (u.email === email) isValid = false;
    });

    return isValid;
  }

  isCpfValid(cpf: string): boolean{
    let isValid: boolean = true;

    this._getUsers().forEach((u: User) => {
      if (u.cpf === cpf) isValid = false;
    });

    return isValid;
  }


  checkUserLogin(cpf: string, password: string): User | null {
    let user: User | null = null;

    this._getUsers().forEach((u: User) => {
      if (u.cpf === cpf && u.password === password && u.permission === true) {
        user = u;
      }
    });

    return user;
  }

  setLogged(user: User) {
    localStorage.setItem('userLogged', JSON.stringify(user));
  }

  logoutUser() {
    localStorage.removeItem('userLogged');
  }

  getLoggedUser(): User {
    let user: User = JSON.parse(localStorage.getItem('userLogged') || '{}');
    return user;
  }

  getClientes(): User[] {

    let user: User[] = JSON.parse(localStorage.getItem('users') || '[]');
    let users: User[] = [];

    user.forEach(u => {
      if (u.permission === 0) {
        users.push(u);
      }
    });

    return users;
  }
  // apagar um getClientes depois
}
