import { DataService } from 'src/app/_service/dataService';
import { Flower } from './../../_model/Flower';
import { Component, Input, OnInit } from '@angular/core';

import { Router } from '@angular/router';

@Component({
  selector: 'app-edit-view',
  templateUrl: './edit-view.component.html',
  styleUrls: ['./edit-view.component.css']
})
export class EditViewComponent implements OnInit {

  constructor(private dataService: DataService, private router: Router) { }

  flowers: Flower[] = [];
  selectedflowers: Flower[] = [];
  selectedImage!: File;

  onFileChanged(event:any) {
    this.selectedImage = event.target.files[0];
    // console.log(this.selectedImage);
  }

  ngOnInit(): void {
    this.dataService.currentState.subscribe(flowers =>{
      this.flowers =flowers;
    })
    // console.log(this.flowers[0].name)
    if(this.flowers.length == 0) {
      this.router.navigateByUrl("/view");
    }
  }

  updateFormSubmit(updateForm:any) {
    console.log(updateForm);
  }



}
