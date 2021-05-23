import { Flower } from './../../_model/Flower';
import { Component, OnInit } from '@angular/core';
import { FileService } from 'src/app/_service/FileService';

@Component({
  selector: 'app-add-flower',
  templateUrl: './add-flower.component.html',
  styleUrls: ['./add-flower.component.css']
})
export class AddFlowerComponent implements OnInit {

  constructor(private fileService: FileService) { }

  flower: Flower = {};
  name!: string;
  description!: string;
  category!: string;
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

    this.fileService.postPhoto(this.flower).subscribe((data) =>{
      console.log(data);
    }, err=> {
      console.log(err);
    })
  }

}
