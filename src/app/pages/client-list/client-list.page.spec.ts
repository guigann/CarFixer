import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ClientListPage } from './client-list.page';

describe('ClientListPage', () => {
  let component: ClientListPage;
  let fixture: ComponentFixture<ClientListPage>;

  beforeEach(async(() => {
    fixture = TestBed.createComponent(ClientListPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
