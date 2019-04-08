import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MovieDialogComponent } from './movie-dialog.component';

describe('MovieDialogComponent', () => {
  let component: MovieDialogComponent;
  let fixture: ComponentFixture<MovieDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MovieDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MovieDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
