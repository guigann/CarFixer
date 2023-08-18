import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AddAgendaPage } from './add-agenda.page';

describe('AddAgendaPage', () => {
  let component: AddAgendaPage;
  let fixture: ComponentFixture<AddAgendaPage>;

  beforeEach(async(() => {
    fixture = TestBed.createComponent(AddAgendaPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
