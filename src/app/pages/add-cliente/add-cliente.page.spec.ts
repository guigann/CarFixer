import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AddClientePage } from './add-cliente.page';

describe('AddClientePage', () => {
  let component: AddClientePage;
  let fixture: ComponentFixture<AddClientePage>;

  beforeEach(async(() => {
    fixture = TestBed.createComponent(AddClientePage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
