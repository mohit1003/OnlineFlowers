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

  onFileChanged(event:any) {
    this.flowerImage = event.target.files[0];
    console.log(this.flowerImage);
  }

  addFlower() {
    const flower = new FormData();
    flower.append('flowerImage', this.flowerImage, this.flowerImage.name);
    flower.append('description', this.description);
    flower.append('category', this.category);
    flower.append('price', this.price);

    console.log(this.price);

    this.fileService.postPhoto(flower).subscribe((data) =>{
      console.log(data);
    }, err=> {
      console.log(err);
    })
  }

}
