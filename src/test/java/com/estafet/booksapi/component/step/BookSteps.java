package com.estafet.booksapi.component.step;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.test.context.SpringBootTest;

import com.estafet.booksapi.model.Book;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class BookSteps {

	private Response response;
	private String BASE_URI;
	private int port;
	private String basePath;

	@Given("Base URI is {string}")
	public void setBaseURI(String uri) {
		BASE_URI = uri;
	}

	@Given("Port is {string}")
	public void setPort(String portValue) {
		port = Integer.parseInt(portValue);
	}

	@Given("Base Path is {string}")
	public void setBasePath(String path) {
		basePath = path;
	}

	@When("I make a GET request to {string}")
	public void makeGetRequest(String endpoint) {
		response = given().baseUri(BASE_URI).port(port).basePath(basePath).when().get(endpoint);
	}

	@When("I make a POST request to {string}")
	public void makePostRequest(String endpoint) {
		// This is a dummy payload, replace with a proper book payload
		String bookPayload = "{ \"title\": \"Sample Book\", \"authorId\": 1 }";
		response = given().baseUri(BASE_URI).port(port).basePath(basePath).body(bookPayload).when().post(endpoint);
	}

	@Then("the response status should be {int}")
	public void verifyResponseStatus(int statusCode) {
		response.then().statusCode(statusCode);
	}

	@Then("the response should contain {int} books")
	public void verifyResponseBooksCount(int count) {
		response.then().body("size()", is(count));
	}

	@Then("the response should contain a book with ID {int}")
	public void verifyBookById(int bookId) {
		response.then().body("id", equalTo(bookId));
	}

	@Then("the response is a Book")
	public void theResponseCanBeParsedToABookObject() {
		Book bookResponse = response.as(Book.class);
		assertNotNull(bookResponse);
	}

	@And("the response is a list of Books")
	public void theResponseIsAListOfBooks() {
		List<Book> bookListResponse = Arrays.asList(response.as(Book[].class));
		assertNotNull(bookListResponse);
		assertFalse(bookListResponse.isEmpty());
	}
}
