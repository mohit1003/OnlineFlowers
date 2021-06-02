import { User } from './../_model/User';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FileService } from '../_service/FileService';

import * as alertify from 'alertifyjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  registerForm: FormGroup;
  loginForm: FormGroup;
  user: User;
  token: string;


  constructor(private fb: FormBuilder, private fileService: FileService,
    private router: Router) {
    this.initializeRegisterForm();
    this.initializeLoginForm();

  }

  initializeLoginForm(){
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.pattern('^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$') ] ],
      password: ['', [Validators.required ]]
    })
  }

  initializeRegisterForm() {
    this.registerForm = this.fb.group({
      email: ['', [Validators.required, Validators.pattern('^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$') ] ],
      firstName: ['', [ Validators.required, Validators.minLength(2), Validators.maxLength(30) ]],
      lastName: ['', [ Validators.required, Validators.minLength(2), Validators.maxLength(30) ]],
      password: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(18) ]],
      confirmPassword: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(18)]],
      contact: ['', [ Validators.required, Validators.minLength(10), Validators.maxLength(10) ]],
      title: ['',  Validators.required],
      city: ['',  [ Validators.required, Validators.maxLength(30)] ],
      country: ['',  [ Validators.required, Validators.maxLength(30) ]],
      address: ['', [ Validators.required, Validators.minLength(5), Validators.maxLength(45) ]]
    }, { validator: this.passwordMatchValidator })
  }

  passwordMatchValidator(g: FormGroup){
    return g.get('password').value === g.get('confirmPassword').value ? null : { mismatch: true };
  }

  registerUser() {
    this.user = Object.assign({}, this.registerForm.value);
    // console.log(this.user);
    this.fileService.registerUser(this.user).subscribe(userRegistered => {
      alertify.success("User is registered successfully")
      this.registerForm.reset();
    }, error =>{
      console.log(error);
      alertify.error("User registration failed"+ error.message)
    })
  }

  login() {
    this.user = Object.assign({}, this.loginForm.value);
    // console.log(this.user);
    this.fileService.getToken(this.user).subscribe(token => {
      localStorage.setItem('token', JSON.stringify(token));
      if(this.fileService.redirectUrl !== undefined) {
        this.router.navigateByUrl(this.fileService.redirectUrl);
      }
      alertify.success('Login success')
    }, error =>{
      console.log(error);
      alertify.error("Invalid email/password")
    })

  }


  ngOnInit(): void {
  }

}
