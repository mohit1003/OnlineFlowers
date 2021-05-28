import { FileService } from './FileService';
import { Flower } from './../_model/Flower';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class DataService {
  constructor(private fileService: FileService) {}
  private flowersSubject= new BehaviorSubject<Flower[]>([]);

  currentState = this.flowersSubject.asObservable();

  changeState(flowers: Flower[]) {
    this.flowersSubject.next(flowers);
  }


}
