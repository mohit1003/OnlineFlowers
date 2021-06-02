import { Component, OnInit } from '@angular/core';
import { FileService } from 'src/app/_service/FileService';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {

  constructor(private fileService:FileService) { }

  ngOnInit(): void {
  }

  logout(){
    this.fileService.logout();
  }

}
