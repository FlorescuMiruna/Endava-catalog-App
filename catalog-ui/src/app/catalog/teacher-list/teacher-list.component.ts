import { Component, OnInit } from '@angular/core';
import {Teacher} from "../../model/teacher.model";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {UrlService} from "../../service/url.service";
import {AddEditTeacherComponent} from "../add-edit-teacher/add-edit-teacher.component";
import {MatDialog} from "@angular/material/dialog";

@Component({
  selector: 'app-teacher-list',
  templateUrl: './teacher-list.component.html',
  styleUrls: ['./teacher-list.component.css']
})
export class TeacherListComponent implements OnInit {
  teachers: Teacher[] = [];

  constructor(private dialog:MatDialog, private httpClient: HttpClient, private urlService: UrlService) { }

  ngOnInit(): void {
    this.getTeachers();
  }

  getTeachers() {
    this.httpClient.get<Teacher[]>(environment.baseUrl + environment.teacherListUrl, this.urlService.getRequestOptions()).subscribe(
      response => {
        this.teachers = response;
      },
      () => {
        console.log("Something went wrong when retrieving the list of teachers.")
      }
    )
  }

  onTeacherListChanged() {
    this.getTeachers();
  }

  addTeacher() {
    const dialogRef = this.dialog.open(AddEditTeacherComponent, {
      data: {teacher: new Teacher(), isEditMode: false}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.getTeachers();
    });
  }
}
