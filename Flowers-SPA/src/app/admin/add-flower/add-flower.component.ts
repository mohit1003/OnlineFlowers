import { Flower } from './../../_model/Flower';

import { FileService } from './../../_service/FileService';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

import * as alertify from 'alertifyjs';

@Component({
  selector: 'app-add-flower',
  templateUrl: './add-flower.component.html',
  styleUrls: ['./add-flower.component.css'],
})
export class AddFlowerComponent implements OnInit {
  constructor(private fileService: FileService, private fb: FormBuilder) {}

  flower!: Flower;
  name!: string;
  description!: any;
  category: any;
  flowerImage!: File;
  price!: string;

  fileAttached: boolean = false;

  addForm: FormGroup;

  ngOnInit(): void {
    // this.createAddForm();
  }

  // createAddForm() {
  //   this.addForm  = this.fb.group({
  //     name: ['',[ Validators.required]],
  //     price: ['', [ Validators.required]],
  //     description: ['', [ Validators.required]],
  //     category: ['', [ Validators.required]],
  //     image: ['' ,[ Validators.required]]
  //   })
  // }

  onFileChanged(event: any) {
    if (event.target.files && event.target.files[0]) {
      this.flowerImage = event.target.files[0];
      this.fileAttached = true;
    } else {
      this.fileAttached = false;
    }
  }

  addFlower() {
    if (this.fileAttached) {
      const flower = new FormData();
      flower.append('flowerImage', this.flowerImage, this.flowerImage.name);
      flower.append('name', this.name);
      flower.append('description', this.description);
      flower.append('category', this.category);
      flower.append('price', this.price);

      // console.log(this.price);

      this.fileService.postPhoto(flower).subscribe(
        (data) => {
          alertify.success('Uploaded successfully');
        },
        (err) => {
          alertify.error('Error uploading image');
        }
      );
    } else {
      alertify.error('All fields are required');
    }
  }
}
