import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AgendamentoPage } from './agendamento.page';

describe('AgendamentoPage', () => {
  let component: AgendamentoPage;
  let fixture: ComponentFixture<AgendamentoPage>;

  beforeEach(async(() => {
    fixture = TestBed.createComponent(AgendamentoPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
