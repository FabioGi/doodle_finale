import { NO_ERRORS_SCHEMA } from "@angular/core";
import { StatutCreatorComponent } from "./statut-creator.component";
import { ComponentFixture, TestBed } from "@angular/core/testing";

describe("StatutCreatorComponent", () => {

  let fixture: ComponentFixture<StatutCreatorComponent>;
  let component: StatutCreatorComponent;
  beforeEach(() => {
    TestBed.configureTestingModule({
      schemas: [NO_ERRORS_SCHEMA],
      providers: [
      ],
      declarations: [StatutCreatorComponent]
    });

    fixture = TestBed.createComponent(StatutCreatorComponent);
    component = fixture.componentInstance;

  });

  it("should be able to create component instance", () => {
    expect(component).toBeDefined();
  });
  
});
