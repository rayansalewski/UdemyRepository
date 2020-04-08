import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NgSwitchCourseComponent } from './ng-switch-course.component';

describe('NgSwitchCourseComponent', () => {
  let component: NgSwitchCourseComponent;
  let fixture: ComponentFixture<NgSwitchCourseComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NgSwitchCourseComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NgSwitchCourseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
