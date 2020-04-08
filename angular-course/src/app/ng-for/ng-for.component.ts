import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-ng-for',
  templateUrl: './ng-for.component.html',
  styleUrls: ['./ng-for.component.css']
})
export class NgForComponent implements OnInit {

  names = [
    "Rayan", "Carol", "Nivia", "Brigitte", "Denis", "Neide", "Guydia"
  ]

  cities = [
    {name: "São Paulo",       uf: "SP"},
    {name: "Paraná",          uf: "PR"},
    {name: "Bahia",           uf: "BA"},
    {name: "Santa Catarina",  uf: "SC"}
  ]

  constructor() { }

  ngOnInit(): void {
  }

}
