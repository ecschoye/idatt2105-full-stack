import { test, expect } from "vitest";
import { mount } from "@vue/test-utils";
import ContactComponent from "../ContactComponent.vue";
import { createStore } from "vuex";

test("Test that information gets entered and submitted", async () => {
  const store = createStore({
    state: {
      name: "",
      email: "",
    },
    mutations: {
      SET_NAME(state, name) {
        state.name = name;
      },
      SET_EMAIL(state, email) {
        state.email = email;
      },
    },
  });

  const wrapper = mount(ContactComponent, {
    global: {
      plugins: [store],
    },
  });

  await wrapper.find("#name").setValue("Test Name");
  await wrapper.find("#email").setValue("test@mail.com");
  await wrapper.find("#feedback").setValue("This is feedback");
  const submitButton = wrapper.find("#submitBtn");
  expect(submitButton.exists()).toBe(true);
  await submitButton.trigger("click");
  await new Promise((resolve) => setTimeout(resolve, 0));
  expect(wrapper.find("#name").element.value).toBe("Test Name");
  expect(wrapper.find("#email").element.value).toBe("test@mail.com");
  expect(store.state.name).toBe("Test Name");
  expect(store.state.email).toBe("test@mail.com");
});
