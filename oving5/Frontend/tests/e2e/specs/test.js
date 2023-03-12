// https://docs.cypress.io/api/table-of-contents

describe("My First Test", () => {
  it("Visits the app root url", () => {
    cy.visit("http://10.22.0.12:8081/#/contact");
    cy.contains("h2", "Form");
  });
});

describe("Can not submit form", () => {
  it("Visits the app root url", () => {
    cy.visit("http://10.22.0.12:8081/#/contact");
    cy.contains("h2", "Form");
    cy.get("#name").should("have.value", "");
    cy.get("#email").should("have.value", "");
    cy.get("#feedback").should("have.value", "");
    cy.get("#submitBtn").should("be.disabled");
  });
});

describe("Partially filled out form should not be submitable", () => {
  it("Visits the app root url", () => {
    cy.visit("http://10.22.0.12:8081/#/contact");
    cy.get("#name").type("Cypress");
    cy.get("#email").should("have.value", "");
    cy.get("#feedback").type("Cypress test");
    cy.get("#submitBtn").should("be.disabled");
  });
});

describe("Fill out form and submit", () => {
  it("Visits the app root url", () => {
    cy.visit("http://10.22.0.12:8081/#/contact");
    cy.contains("h2", "Form");
    cy.get("#name").type("Cypress");
    cy.get("#email").type("Cypress@mail.com");
    cy.get("#feedback").type("Cypress test");

    cy.get("#submitBtn").click();
    cy.get("#successId").contains("Success!");
  });
});
