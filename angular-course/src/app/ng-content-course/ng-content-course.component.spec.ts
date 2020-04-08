import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NgContentCourseComponent } from './ng-content-course.component';

describe('NgContentCourseComponent', () => {
  let component: NgContentCourseComponent;
  let fixture: ComponentFixture<NgContentCourseComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NgContentCourseComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NgContentCourseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
