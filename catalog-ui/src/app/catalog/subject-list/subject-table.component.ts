import { Component, OnInit } from '@angular/core';
import {Subject} from "../../model/subject.model";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {UrlService} from "../../service/url.service";
import {MatTableDataSource} from "@angular/material/table";
import {MatDialog} from "@angular/material/dialog";
import {AddEditSubjectComponent} from "../add-edit-subject/add-edit-subject.component";

@Component({
  selector: 'app-subject-list',
  templateUrl: './subject-table.component.html',
  styleUrls: ['./subject-table.component.css']
})
export class SubjectTableComponent implements OnInit {
  dataSource = new MatTableDataSource<Subject>();
  displayedColumns: string[] = ['actions', 'name', 'creditPoints', 'isOptional', 'coursePercent', 'seminaryPercent'];

  constructor(private httpClient: HttpClient, private urlService: UrlService, public dialog:MatDialog) { }

  ngOnInit(): void {
    this.getSubjects();
  }

  getSubjects(){
    this.httpClient.get<Subject[]>(environment.baseUrl +environment.subjectListUrl,  this.urlService.getRequestOptions()).subscribe(
      response => {
        console.log("Subjects");
        console.log(response);
        this.dataSource.data = response;
      },
    () => {
        console.log("Error when retrieving the list of subjects.");
    }
    )
  }

  addSubject() {
    const dialogRef = this.dialog.open(AddEditSubjectComponent, {
      data: {teacher: new Subject('', '', 0, true, 0, 0), isEditMode: false}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.getSubjects();
    });
  }

  deleteSubject() {
    alert("Not Implemented!");
  }

  editSubject() {
    alert("Not Implemented!");
  }
}
