import { Flower } from './../../_model/Flower';
import { Component, OnInit } from '@angular/core';
import { FileService } from 'src/app/_service/FileService';
import { DomSanitizer } from '@angular/platform-browser';
import { DataService } from 'src/app/_service/dataService';

@Component({
  selector: 'app-view',
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.css']
})
export class ViewComponent implements OnInit {

  constructor(private fileService: FileService,
    private sanitizer: DomSanitizer, private dataService: DataService) { }

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
      //  this.flowers.forEach((photo) => {
      //   this.retrieveResonse = photo.photo;
      //   // this.base64Data = this.retrieveResonse.picByte;
      //   this.retrievedImage = 'data:image/jpeg;base64,' + this.retrieveResonse;
      //   photo.photo = this.sanitizer.bypassSecurityTrustUrl(this.retrievedImage);
      //   this.dataService.changeState(this.flowers);
      // })
      // console.log(this.flowers);
    }, err => {
      console.log(err);
    })
  }

}
