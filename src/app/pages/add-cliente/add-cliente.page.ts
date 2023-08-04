import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { NavController, ToastController } from '@ionic/angular';
import { User } from 'src/app/model/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-add-cliente',
  templateUrl: './add-cliente.page.html',
  styleUrls: ['./add-cliente.page.scss'],
})
export class AddClientePage implements OnInit {

  titlePage: string;
  userService: UserService;
  formGroup: FormGroup;
  user: User | undefined;
  
  constructor(private activatedRoute: ActivatedRoute,
    private formBuilder: FormBuilder,
    private toastController: ToastController,
    private navController: NavController) { 

    this.userService = new UserService();

    
    let id = this.activatedRoute.snapshot.params['id'] | 0;

    if (id > 0) {
      this.user = this.userService.getById(id);
      if (this.user) this.navController.navigateBack('/client-list');
      this.titlePage = "Cliente"
    } else {
      this.titlePage = "Cadastro de Cliente"
      
    }

    this.formGroup = this.formBuilder.group({
      'name':[this.user?.name, Validators.compose([
        Validators.required
      ])],
      'cpf':[this.user?.cpf, Validators.compose([
        Validators.required
      ])],
      'phone':[this.user?.phone, Validators.compose([
        Validators.required
      ])],
      'password':[this.user?.password, Validators.compose([
        Validators.required
      ])],
    });
      
  }

  ngOnInit() {
  }

  bt_save(){
    let name = this.formGroup.value.name;
    let cpf = this.formGroup.value.cpf;
    let phone = this.formGroup.value.phone;
    let password = this.formGroup.value.password;

    let user = new User();
    user.name = name;
    user.cpf = cpf;
    user.phone = phone;
    user.password = password;
    user.permission = false;
    
    if (this.userService.save(user)) {
      this.showMessage("Salvo com sucesso");
      this.navController.navigateBack("/home");
    } else {
      this.showMessage("Houve um erro!");
    }
  }

  async showMessage(str: string){
    const toast = await this.toastController.create({
      message: str,
      duration: 1500
    })
    toast.present();
  }

}
