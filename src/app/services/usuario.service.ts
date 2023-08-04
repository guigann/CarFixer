import { Injectable } from '@angular/core';
import { Usuario } from '../model/usuario';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class UsuarioService {

    httpHeaders = {
        headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    }

    url: string = 'http://localhost:8087/api/v1/usuario';

    constructor(private httpClient: HttpClient) { }

    async get() {
        return await this.httpClient.get(this.url).toPromise();
    }

    async getById(id: number) {
        let urlAuxiliar = this.url + "/" + id;
        return await this.httpClient.get(urlAuxiliar).toPromise();
    }

    async getByEmail(email: string) {
        let urlAuxiliar = this.url + "/email/" + email;
        return await this.httpClient.get(urlAuxiliar).toPromise();
    }

    async save(usuario: Usuario) {
        if (usuario.id === 0) {
            return await this.httpClient.post(this.url, JSON.stringify(usuario), this.httpHeaders).toPromise();
        } else {
            return await this.httpClient.put(this.url, JSON.stringify(usuario), this.httpHeaders).toPromise();
        }
    }

    async delete(id: number) {
        let urlAuxiliar = this.url + "/" + id;
        return await this.httpClient.delete(urlAuxiliar).toPromise();
    }  

    async checkEmail(email: string) {
        let urlAuxiliar = this.url + "/email/" + email + '/exists';
        return await this.httpClient.get(urlAuxiliar).toPromise()
    }

    async login(usuario: Usuario) {
        let urlAuxiliar = this.url + "/" + usuario.email + "/" + usuario.senha + '/authenticate';
        return await this.httpClient.get(urlAuxiliar).toPromise()
    }

    static logout() {
        localStorage.removeItem('usuario');
    }

    setLogin(usuario: Usuario) {
        localStorage.setItem('usuario', JSON.stringify(usuario));
    }

    static getLogin() {
        let usuario = JSON.parse(localStorage.getItem('usuario') || 'null');
        return usuario;
    }

    static protect() {
        if (this.getLogin() === null) {
            return false;
        } else
            return true;
    }

}