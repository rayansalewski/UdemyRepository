import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NgTemplateCourseComponent } from './ng-template-course.component';

describe('NgTemplateCourseComponent', () => {
  let component: NgTemplateCourseComponent;
  let fixture: ComponentFixture<NgTemplateCourseComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NgTemplateCourseComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NgTemplateCourseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
