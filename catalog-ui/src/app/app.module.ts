import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { CatalogComponent } from './catalog/catalog.component';
import {FormsModule} from "@angular/forms";
import { TeacherListComponent } from './catalog/teacher-list/teacher-list.component';
import { TeacherComponent } from './catalog/teacher-list/teacher/teacher.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MaterialModule} from "./material.module";
import {HttpClientModule} from "@angular/common/http";
import {UrlService} from "./service/url.service";
import {AddEditTeacherComponent} from "./catalog/add-edit-teacher/add-edit-teacher.component";
import {CatalogTabComponent} from "./catalog/catalog-tab/catalog-tab.component";
import {SubjectTableComponent} from "./catalog/subject-list/subject-table.component";
import {AddEditSubjectComponent} from "./catalog/add-edit-subject/add-edit-subject.component";

@NgModule({
  declarations: [
    AppComponent,
    CatalogComponent,
    TeacherListComponent,
    TeacherComponent,
    AddEditTeacherComponent,
    CatalogTabComponent,
    SubjectTableComponent,
    AddEditSubjectComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    BrowserAnimationsModule,
    MaterialModule,
    HttpClientModule
  ],
  providers: [UrlService, AddEditSubjectComponent, AddEditTeacherComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
