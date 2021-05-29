import { Router } from '@angular/router';
import { Flower } from './../../_model/Flower';
import { Component, OnInit, TemplateRef } from '@angular/core';
import { FileService } from 'src/app/_service/FileService';
import { DomSanitizer } from '@angular/platform-browser';
import { DataService } from 'src/app/_service/dataService';
import { BsModalRef, BsModalService } from 'ngx-bootstrap/modal';

@Component({
  selector: 'app-view',
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.css']
})
export class ViewComponent implements OnInit {

  constructor(private fileService: FileService,
    private sanitizer: DomSanitizer, private dataService: DataService,
    private modalService: BsModalService, private router: Router) { }

  flowers: Flower[] = [];
  flowerToEdit!: Flower;
  flowerImage!:File
  category!: string;
  description!: string
  price!:string;
  name!:string;
  id!:string;

  flagUpdateWithoutImage: boolean = true;

  modalRef!: BsModalRef;


  ngOnInit(): void {
    this.getAllPhotos();
  }

  onFileChanged(event:any) {
    this.flowerImage = event.target.files[0];
    // console.log(this.flowerImage);
  }

  getAllPhotos() {
    this.fileService.getAllPhotos().subscribe((data) => {
      this.flowers = Object.assign(data);


      this.dataService.changeState(this.flowers);
      // this.dataService.currentState.subscribe(flowers =>{
      //  console.log(flowers);
      // })
    }, err => {
      console.log(err);
    })
  }

  delete(id: string) {
    this.fileService.delete(id).subscribe(data => {
      console.log(data);
    }, err => {
      console.log(err);
    })
  }

  updateFlower() {
    if (!this.modalRef) {
      return;
    }
    this.modalRef.hide();
    this.modalRef = null;

    const flower = new FormData();

    if(this.flowerImage !== undefined) {
      flower.append('id', this.flowerToEdit.id);
      flower.append('flowerImage', this.flowerImage, this.flowerImage.name);
      flower.append('name', this.flowerToEdit.name);
      flower.append('description', this.flowerToEdit.description);
      flower.append('category', this.flowerToEdit.category);
      flower.append('price', this.flowerToEdit.price);
      this.flagUpdateWithoutImage = false;
    }


    // console.log(this.price);

    if(this.flagUpdateWithoutImage) {
      this.fileService.putPhotoWhthoutImage(this.flowerToEdit).subscribe((data) =>{
        console.log(data);
        this.router.navigateByUrl('/view');
      }, err=> {
        console.log(err);
      })
    }
    else if(!this.flagUpdateWithoutImage) {
      this.fileService.putPhoto(flower).subscribe((data) =>{
        console.log(data);
      }, err=> {
        console.log(err);
      })
    }
  }

  openEditModel(template: TemplateRef<any>, id:string){
    this.flowerToEdit = Object.assign(this.flowers.filter(flower => flower.id === id));
    this.flowerToEdit = this.flowerToEdit[0];
    this.modalRef = this.modalService.show(template);
  }

}
