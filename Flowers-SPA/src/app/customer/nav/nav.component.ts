import { FileService } from 'src/app/_service/FileService';
import { Component, Input, OnInit } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {

  @Input() shoppingCartCount: number;

  constructor(private fileService:FileService) { }

  ngOnInit(): void {
  }

  logout(){
    this.fileService.logout();
  }

}
