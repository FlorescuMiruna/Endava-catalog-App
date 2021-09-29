import {SubjectScoring} from "./subject-scoring.model";

export class Subject {
  id:string;
  name:string;
  creditPoints:number;
  isOptional:boolean;
  subjectScoring: SubjectScoring = new SubjectScoring();


  constructor(id:string, name:string, creditPoints:number, isOptional:boolean, coursePercent:number, seminaryPercent:number) {
    this.id = id;
    this.name = name;
    this.creditPoints = creditPoints;
    this.isOptional = isOptional;
    this.subjectScoring.coursePercent = coursePercent;
    this.subjectScoring.seminaryPercent = seminaryPercent;
  }
}
