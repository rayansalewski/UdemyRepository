import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-ng-container-course',
  templateUrl: './ng-container-course.component.html',
  styleUrls: ['./ng-container-course.component.css']
})
export class NgContainerCourseComponent implements OnInit {

  typeAccount = "noRole";

  users = [
    {login:"Rayan",   role:"admin", date: new Date("03/11/2020")},
    {login:"Carol",   role:"user", date: new Date("03/24/2020")},
    {login:"Gustavo", role:"admin", date: new Date("03/18/2020")},
    {login:"Carlos",  role:"user", date: new Date("03/26/2020")}
  ];

  constructor() { }

  ngOnInit(): void {
  }

}
