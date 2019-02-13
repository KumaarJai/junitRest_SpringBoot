package com.test.junity.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.time.Duration;
import java.util.List;

import org.apache.http.HttpStatus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.test.junity.models.Book;
import com.test.junity.services.LibraryService;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

class BookController_EndpointTest {

	private static final String CONTEXT_PATH = "/junity";
	public static final String ALL_BOOKS = "lib/all";
	
	
	@InjectMocks
	BookController api;
	
	@Mock
	LibraryService service;
	
	Book book;
	List<Book> books;
	
	
	@BeforeEach
	public void setUp() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
	}
	
	@Test
	@DisplayName("Test endpoint status: "+ALL_BOOKS)
	public void testStatus_getAllBooks() {
		RequestSpecification request = RestAssured.given();
		Response response = request.get(CONTEXT_PATH + ALL_BOOKS);

		assertEquals(HttpStatus.SC_OK, response.getStatusCode());
		assertEquals("application/json", response.getContentType());
		assertTimeout(Duration.ofMillis(2000), () -> { response.getContentType(); } );		
	}
	
	@Test
	@DisplayName("Test endpoint schema validation: "+ALL_BOOKS)
	public void testSchema_getAllBooks() {
		RequestSpecification request = RestAssured.given();
		request.when().get(CONTEXT_PATH+ALL_BOOKS).then().assertThat().body(matchesJsonSchemaInClasspath("schemas/BookSchema.json"));
	}
	

}
