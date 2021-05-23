import { Flower } from './../../_model/Flower';

import { FileService } from './../../_service/FileService';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-add-flower',
  templateUrl: './add-flower.component.html',
  styleUrls: ['./add-flower.component.css']
})
export class AddFlowerComponent implements OnInit {

  constructor(private fileService: FileService) { }

  flower: Flower = {};
  name!: string;
  description!: any;
  category: any;
  flowerImage!:File
  price!: string;

  ngOnInit(): void {}

  addFlower() {
    this.flower={
      name:this.name,
      image:this.flowerImage,
      description:this.description,
      category:this.category,
      price:this.price

    }
    console.log(this.flower);

    this.fileService.postPhoto(this.flower).subscribe((data) =>{
      console.log(data);
    }, err=> {
      console.log(err);
    })
  }

}
