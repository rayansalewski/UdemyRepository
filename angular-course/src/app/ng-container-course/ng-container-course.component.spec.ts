import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NgContainerCourseComponent } from './ng-container-course.component';

describe('NgContainerCourseComponent', () => {
  let component: NgContainerCourseComponent;
  let fixture: ComponentFixture<NgContainerCourseComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NgContainerCourseComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NgContainerCourseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
