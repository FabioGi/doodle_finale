import { NO_ERRORS_SCHEMA } from "@angular/core";
import { ReunionComponent } from "./reunion.component";
import { ComponentFixture, TestBed } from "@angular/core/testing";

describe("ReunionComponent", () => {

  let fixture: ComponentFixture<ReunionComponent>;
  let component: ReunionComponent;
  beforeEach(() => {
    TestBed.configureTestingModule({
      schemas: [NO_ERRORS_SCHEMA],
      providers: [
      ],
      declarations: [ReunionComponent]
    });

    fixture = TestBed.createComponent(ReunionComponent);
    component = fixture.componentInstance;

  });

  it("should be able to create component instance", () => {
    expect(component).toBeDefined();
  });
  
});
