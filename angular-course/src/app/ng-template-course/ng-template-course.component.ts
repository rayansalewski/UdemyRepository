import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-ng-template-course',
  templateUrl: './ng-template-course.component.html',
  styleUrls: ['./ng-template-course.component.css']
})
export class NgTemplateCourseComponent implements OnInit {

  enable: boolean = false;

  constructor() { }

  ngOnInit(): void {
  }

}
