import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AchatTicketComponent } from './achat-ticket.component';

describe('AchatTicketComponent', () => {
  let component: AchatTicketComponent;
  let fixture: ComponentFixture<AchatTicketComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AchatTicketComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AchatTicketComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
