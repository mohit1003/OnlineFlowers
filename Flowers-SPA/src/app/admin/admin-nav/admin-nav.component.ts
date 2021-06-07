import { BsModalRef } from 'ngx-bootstrap/modal';
import { Component, OnInit } from '@angular/core';

import { FileService } from 'src/app/_service/FileService';


@Component({
  selector: 'app-admin-nav',
  templateUrl: './admin-nav.component.html',
  styleUrls: ['./admin-nav.component.css']
})
export class AdminNavComponent implements OnInit {
  modalRef!: BsModalRef;
  constructor(private fileService: FileService) { }

  ngOnInit(): void {
  }

  logout(){
    this.fileService.logout();
  }

}
