import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-ng-for-form',
  templateUrl: './ng-for-form.component.html',
  styleUrls: ['./ng-for-form.component.css']
})
export class NgForFormComponent implements OnInit {

  name: string = "";
  address: string = "";
  phone: string = "";
  city: string = "";
  age: number = 0;

  cities = [
    { name: "São Paulo", uf: "SP" },
    { name: "Paraná", uf: "PR" },
    { name: "Bahia", uf: "BA" },
    { name: "Santa Catarina", uf: "SC" }
  ]

  clients = [];

  save() {
    this.clients.push(
      { 
        name: this.name,
        address: this.address,
        city: this.city,
        age: this.age,
        phone: this.phone
      }
    );
    this.cancel();
    console.log(this.clients);
  }

  cancel(){
    this.name = "";
    this.address = "";
    this.phone = "";
    this.city = "";
    this.age = 0;
  }

  deleteClient(i: number){
    this.clients.splice(i, 1);
  }

  constructor() { }

  ngOnInit(): void {
  }

}
