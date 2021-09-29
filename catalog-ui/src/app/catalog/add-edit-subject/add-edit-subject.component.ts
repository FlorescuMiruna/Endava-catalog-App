import { Component, OnInit } from '@angular/core';
import {Subject} from "../../model/subject.model";
import {MatDialogRef} from "@angular/material/dialog";
import {NgForm} from "@angular/forms";
import {environment} from "../../../environments/environment";
import {UrlService} from "../../service/url.service";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-add-edit-subject',
  templateUrl: './add-edit-subject.component.html',
  styleUrls: ['./add-edit-subject.component.css']
})
export class AddEditSubjectComponent implements OnInit {
  subject: Subject = new Subject('', '', 0, false, 0, 0);
  isEditMode: boolean = false;
  creditPointsRegex = '^([1-9]|1\\d|20)$';


  constructor(private urlService:UrlService, private httpClient:HttpClient, public dialogRef: MatDialogRef<AddEditSubjectComponent>) { }

  ngOnInit(): void {
  }

  onSubjectSubmit(subjectForm: NgForm) {
    if(!subjectForm.valid){
      return;
    }
    console.log(subjectForm.form.value.optional);
    const transientSubject = new Subject(subjectForm.form.value.id, subjectForm.form.value.name, subjectForm.form.value.creditPoints,
      Boolean(JSON.parse(subjectForm.form.value.optional)), subjectForm.form.value.coursePercent, subjectForm.form.value.seminaryPercent);

    /*teacherForm.*/
    console.log(transientSubject);

    if(this.isEditMode) {
      this.httpClient.put(environment.baseUrl + environment.updateSubjectUrl.replace('{id}', this.subject.id + ''), transientSubject,
        this.urlService.getRequestOptions())
        .subscribe(
          response => {
            console.log("Subject successfully updated.");
            this.close();
          },
          () => {
            console.log("Error when updating a subject.");
          })
    } else {
      this.httpClient.post(environment.baseUrl + environment.saveSubjectUrl, transientSubject, this.urlService.getRequestOptions()).subscribe(
        response => {
          console.log("Subject successfully saved.");
          this.close();
        },
        () => {
          console.log("Error when saving a subject.");
        }
      )
    }
  }

  close() {
    this.dialogRef.close();
  }

  computePercentage(subjectForm: NgForm) {
    console.log(subjectForm);

    let coursePercent = Number(subjectForm.form.value.coursePercent);
    let seminaryPercent = Number(subjectForm.form.value.seminaryPercent);
    if (!isNaN(coursePercent)) {
     subjectForm.form.controls['coursePercent'].setErrors({'incorrect': true})
    }
    if (!isNaN(seminaryPercent)) {
      subjectForm.form.controls['seminaryPercent'].setErrors({'incorrect': true})
    }
    console.log(coursePercent + seminaryPercent === 100);
    if(coursePercent + seminaryPercent === 100) {
       subjectForm.form.controls['coursePercent'].setErrors(null)
      subjectForm.form.controls['seminaryPercent'].setErrors(null)
    } else {
      subjectForm.form.controls['coursePercent'].setErrors({'incorrect': true})
      subjectForm.form.controls['seminaryPercent'].setErrors({'incorrect': true})
    }
  }

}
