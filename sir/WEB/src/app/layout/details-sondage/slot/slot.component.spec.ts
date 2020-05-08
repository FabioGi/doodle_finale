import { NO_ERRORS_SCHEMA } from "@angular/core";
import { SlotComponent } from "./slot.component";
import { ComponentFixture, TestBed } from "@angular/core/testing";

describe("SlotComponent", () => {

  let fixture: ComponentFixture<SlotComponent>;
  let component: SlotComponent;
  beforeEach(() => {
    TestBed.configureTestingModule({
      schemas: [NO_ERRORS_SCHEMA],
      providers: [
      ],
      declarations: [SlotComponent]
    });

    fixture = TestBed.createComponent(SlotComponent);
    component = fixture.componentInstance;

  });

  it("should be able to create component instance", () => {
    expect(component).toBeDefined();
  });
  
});
