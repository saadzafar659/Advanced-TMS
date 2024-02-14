package com.task.management.e2e;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TaskForm {

	@Given("I am on the task form page")
	public void i_am_on_the_task_form_page() {
		System.out.println("Navigating to the task form page");
	}

	@When("I enter the name {string}")
	public void i_enter_the_name(String name) {
		System.out.println("Entering the name: " + name);
	}

	@When("I enter the author {string}")
	public void i_enter_the_author(String author) {
		System.out.println("Entering the author: " + author);
	}

	@When("I enter the price {string}")
	public void i_enter_the_price(String price) {
		System.out.println("Entering the price: " + price);
	}

	@When("I click the Save button")
	public void i_click_the_save_button() {
		System.out.println("Clicking the Save button");
	}

	@Then("I should see a success message")
	public void i_should_see_a_success_message() {
		System.out.println("Verifying the success message");
	}
}
