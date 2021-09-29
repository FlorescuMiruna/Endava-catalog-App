import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Teacher} from "../../../model/teacher.model";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../../environments/environment";
import {AddEditTeacherComponent} from "../../add-edit-teacher/add-edit-teacher.component";
import {MatDialog} from "@angular/material/dialog";

@Component({
  selector: 'app-teacher',
  templateUrl: './teacher.component.html',
  styleUrls: ['./teacher.component.css']
})
export class TeacherComponent implements OnInit {
  @Input() teacher:Teacher = new Teacher();
  @Output() teacherListChanged = new EventEmitter();

  constructor(public httpClient:HttpClient, private dialog:MatDialog) { }

  ngOnInit(): void {
  }

  deleteTeacher() {
    this.httpClient.delete(environment.baseUrl + environment.deleteTeacherUrl.replace('{id}', this.teacher.id + '')).subscribe(
      response => {
        this.teacherListChanged.next();
      },
      () => {
        console.log("Error when deleting teacher with first name: "+ this.teacher.firstName);
      }
    )
  }

  editTeacher() {
    const dialogRef = this.dialog.open(AddEditTeacherComponent, {
      data: {teacher: this.teacher, isEditMode: true}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
     this.teacherListChanged.next();
    });
  }
}
