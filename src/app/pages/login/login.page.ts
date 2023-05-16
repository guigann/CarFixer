import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NavController, ToastController } from '@ionic/angular';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage implements OnInit {
  
  userService: UserService;
  formGroup: FormGroup;

  constructor(private formBuilder: FormBuilder,
    private toastController: ToastController,
    private navController: NavController) { 
      
    this.userService = new UserService();

    this.formGroup = this.formBuilder.group({
      'cpf':['', Validators.compose([
        Validators.required
      ])],
      'password':['', Validators.compose([
        Validators.required
      ])],
    });
  }

  ngOnInit() {
    this.userService.logoutUser();
  }

  bt_login(){
    let cpf = this.formGroup.value.cpf;
    let password = this.formGroup.value.password;
    
    let user = this.userService.checkUserLogin(cpf, password);

    if (user) {
      this.userService.setLogged(user);

      this.navController.navigateRoot("/home");
    } else {
      this.showMessage("There was an error in login");
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
