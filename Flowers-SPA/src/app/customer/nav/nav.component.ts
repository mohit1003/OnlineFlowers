import { FileService } from 'src/app/_service/FileService';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {

  @Input() shoppingCartCount: number;

  constructor(private fileService:FileService) { }
  isLoggedIn: boolean = false;
  ngOnInit(): void {
    if(this.fileService.isLoggedIn()){
      this.isLoggedIn = true;
    }
  }

  logout(){
    this.fileService.logout();
  }

}
