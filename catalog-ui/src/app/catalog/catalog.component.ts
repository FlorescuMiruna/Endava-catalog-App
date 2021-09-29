import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {MatDialog} from "@angular/material/dialog";
import {AddEditTeacherComponent} from "./add-edit-teacher/add-edit-teacher.component";

@Component({
  selector: 'app-catalog',
  templateUrl: './catalog.component.html',
  styleUrls: ['./catalog.component.css']
})
export class CatalogComponent implements OnInit {
  catalogName: string = '';
  @Input() universityName: string = '';
  @Output() changeUniversityNameEvent = new EventEmitter();

  constructor() { }

  ngOnInit(): void {
    this.catalogName = 'Mate-Info';
  }

  onInputEvent(event: any): void {
    this.catalogName = event.target.value;
  }

  changeUniversityName(event: any) {
    this.changeUniversityNameEvent.emit(event.target.value);
  }


}
