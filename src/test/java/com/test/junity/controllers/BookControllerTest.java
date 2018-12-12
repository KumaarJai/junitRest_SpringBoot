package com.test.junity.controllers;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeout;

import java.time.Duration;

import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import com.test.junity.models.Book;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
public class BookControllerTest extends BaseTest {

	public static final String ALL = "lib/all";
	public static final String GET_BOOK1 = "lib/book/1";
	public static final String ADD_BOOK = "lib/book";
	public static final String DELETE_BOOK1 = "lib/delete/1";
	
	public static final String DUMMY_BOOK = "{\"name\":\"TestBook\",\"author\":\"Junit5\",\"description\":\"My Testing Book\",\"numPages\":\"250\"}";

	
	@Test
	@DisplayName("Checking Endoint status for endpoint URI: host:port/junity/lib/all")
	public void checkEndpointStatusFor_GetAllBooks() {
		RequestSpecification request = RestAssured.given();
		Response response = request.get(HOST_ROOT + ALL);

		assertEquals(HttpStatus.SC_OK, response.getStatusCode());
		assertTimeout(Duration.ofMillis(2000), () -> { response.getContentType(); } );	
	}
	
	@Test
	@DisplayName("Checking Endoint status for endpoint URI: host:port/junity/lib/all")
	public void checkEndpointStatusFor_AddUpdateBook() {
		RequestSpecification request = RestAssured.given();
		Response response = request.post(HOST_ROOT + ADD_BOOK, new Book());

		assertEquals(HttpStatus.SC_OK, response.getStatusCode());
		assertTimeout(Duration.ofMillis(2000), () -> { response.getContentType(); } );	
	}
	
	

	@Test
	public void checkSchemaValidityFor_GetAllBooks() {
		prepareGet(ALL)
		.assertThat()
		.body(matchesJsonSchemaInClasspath("schemas/BookSchema.json"));
	}






}