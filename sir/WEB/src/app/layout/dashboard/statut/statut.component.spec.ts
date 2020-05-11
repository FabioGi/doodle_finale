import { NO_ERRORS_SCHEMA } from "@angular/core";
import { StatutComponent } from "./statut.component";
import { ComponentFixture, TestBed } from "@angular/core/testing";

describe("StatutComponent", () => {

  let fixture: ComponentFixture<StatutComponent>;
  let component: StatutComponent;
  beforeEach(() => {
    TestBed.configureTestingModule({
      schemas: [NO_ERRORS_SCHEMA],
      providers: [
      ],
      declarations: [StatutComponent]
    });

    fixture = TestBed.createComponent(StatutComponent);
    component = fixture.componentInstance;

  });

  it("should be able to create component instance", () => {
    expect(component).toBeDefined();
  });
  
});
