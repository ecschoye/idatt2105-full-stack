import { test, expect } from "vitest";
import { mount } from "@vue/test-utils";
import CalculatorComponent from "../CalculatorComponent.vue";

test("Test that numbers get entered", async () => {
  const wrapper = mount(CalculatorComponent);
  const button7 = wrapper.find("#button-7");
  await button7.trigger("click");
  const button2 = wrapper.find("#button-2");
  await button2.trigger("click");
  const button4 = wrapper.find("#button-4");
  await button4.trigger("click");
  const result = wrapper.find("#result");
  expect(result.text()).toBe("724");
  expect(result.text()).not.toBe("247");
  expect(result.text()).not.toBe("472");
});

test("Do operation", async () => {
  const wrapper = mount(CalculatorComponent);
  const button7 = wrapper.find("#button-7");
  await button7.trigger("click");
  const operator = wrapper.find("#button-add");
  await operator.trigger("click");
  const button4 = wrapper.find("#button-4");
  await button4.trigger("click");
  const equals = wrapper.find("#button-equals");
  await equals.trigger("click");
  const result = wrapper.find("#result");
  expect(result.text()).toBe("11.00");
});
