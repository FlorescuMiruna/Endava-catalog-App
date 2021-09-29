import {Component, Inject, Input, OnInit} from '@angular/core';
import {NgForm} from "@angular/forms";
import {Teacher} from "../../model/teacher.model";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {UrlService} from "../../service/url.service";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-add-edit-teacher',
  templateUrl: './add-edit-teacher.component.html',
  styleUrls: ['./add-edit-teacher.component.css']
})
export class AddEditTeacherComponent implements OnInit {
  @Input() teacher:Teacher = new Teacher();
  isEditMode: boolean = false;
  cnpPattern = '^[0-9]{13}$';
  salaryPattern = '^[0-9]+$';

  constructor(private httpClient: HttpClient, private urlService: UrlService, public dialogRef: MatDialogRef<AddEditTeacherComponent>,
              @Inject(MAT_DIALOG_DATA) public data: {teacher: Teacher, isEditMode: boolean}) { }

  ngOnInit(): void {
    this.isEditMode = this.data.isEditMode;
    const cloneTeacher = new Teacher();
    cloneTeacher.id = this.data.teacher.id;
    cloneTeacher.firstName = this.data.teacher.firstName;
    cloneTeacher.lastName = this.data.teacher.lastName;
    cloneTeacher.birthDate = this.data.teacher.birthDate;
    cloneTeacher.cnp = this.data.teacher.cnp;
    cloneTeacher.salary = this.data.teacher.salary;
    cloneTeacher.employmentDate = this.data.teacher.employmentDate;
    this.teacher = cloneTeacher;
  }

  onTeacherSubmit(teacherForm: NgForm) {
    if(!teacherForm.valid){
      return;
    }

    const transientTeacher = new Teacher();
    transientTeacher.firstName = teacherForm.form.value.firstName;
    transientTeacher.lastName = teacherForm.form.value.lastName;
    transientTeacher.lastName = teacherForm.form.value.lastName;
    transientTeacher.cnp = teacherForm.form.value.cnp;
    transientTeacher.birthDate = teacherForm.form.value.birthDate;
    transientTeacher.salary = teacherForm.form.value.salary;
    transientTeacher.employmentDate = teacherForm.form.value.employmentDate;

    /*teacherForm.*/
    console.log(transientTeacher);

    if(this.isEditMode) {
      this.httpClient.put(environment.baseUrl + environment.updateTeacherUrl.replace('{id}', this.teacher.id + ''), transientTeacher, this.urlService.getRequestOptions())
        .subscribe(
          response => {
            console.log("Teacher successfully updated.");
            this.close();
          },
          () => {
            console.log("Error when updating a teacher.");
          })
    } else {
      this.httpClient.post(environment.baseUrl + environment.saveTeacherUrl, transientTeacher, this.urlService.getRequestOptions()).subscribe(
        response => {
          console.log("Teacher successfully saved.");
          this.close();
        },
        () => {
          console.log("Error when saving a teacher.");
        }
      )
    }
  }

  close() {
    this.dialogRef.close();
  }
}
