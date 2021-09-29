import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  universityNameParent: string = 'Politehnica';

  onUniversityNameChanged(universityName: string) {
    this.universityNameParent = universityName;
  }
}
