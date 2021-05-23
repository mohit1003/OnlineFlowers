import { Flower } from './../../_model/Flower';
import { Component, OnInit } from '@angular/core';
import { FileService } from 'src/app/_service/FileService';

@Component({
  selector: 'app-view',
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.css']
})
export class ViewComponent implements OnInit {

  constructor(private fileService: FileService) { }

  flowers: Flower[] = [];
  base64Data: any;
  retrieveResonse: any;
  retrievedImage: any;

  ngOnInit(): void {
    this.getAllPhotos();
  }

  getAllPhotos() {
    this.fileService.getAllPhotos().subscribe((data) => {
      this.flowers = Object.assign(data);

      const reader = new FileReader();


      console.log(this.flowers);
       this.flowers.forEach((photo) => {
        this.retrieveResonse = photo.photo;
        reader.onload = (e) => photo.photo = e.target!.result;
        // this.base64Data = this.retrieveResonse.picByte;
        // this.retrievedImage = 'data:image/jpeg;base64,' + this.base64Data;
        // console.log(this.retrieveResonse.picByte);
        // photo.photo = this.retrievedImage;
      })
      // console.log(this.flowers);
    }, err => {
      console.log(err);
    })
  }

}
