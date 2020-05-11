import { NO_ERRORS_SCHEMA } from "@angular/core";
import { AlimentaireComponent } from "./alimentaire.component";
import { ComponentFixture, TestBed } from "@angular/core/testing";

describe("AlimentaireComponent", () => {

  let fixture: ComponentFixture<AlimentaireComponent>;
  let component: AlimentaireComponent;
  beforeEach(() => {
    TestBed.configureTestingModule({
      schemas: [NO_ERRORS_SCHEMA],
      providers: [
      ],
      declarations: [AlimentaireComponent]
    });

    fixture = TestBed.createComponent(AlimentaireComponent);
    component = fixture.componentInstance;

  });

  it("should be able to create component instance", () => {
    expect(component).toBeDefined();
  });
  
});
