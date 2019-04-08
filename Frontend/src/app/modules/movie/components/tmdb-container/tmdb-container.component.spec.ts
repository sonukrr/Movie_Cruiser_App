import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TmdbContainerComponent } from './tmdb-container.component';

describe('TmdbContainerComponent', () => {
  let component: TmdbContainerComponent;
  let fixture: ComponentFixture<TmdbContainerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TmdbContainerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TmdbContainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
