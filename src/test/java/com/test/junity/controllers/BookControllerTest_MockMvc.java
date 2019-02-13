
/*
 * Author: Ajay Kumar Rabidas
 * Desc: (In Server Unit testing) Testing in Stand-Alone Mode, no web application context required. (Junit + Mokito) using MockMvc
 */

package com.test.junity.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.junity.models.Book;
import com.test.junity.services.LibraryService;

@RunWith(MockitoJUnitRunner.class)
public class BookControllerTest_MockMvc {

	private MockMvc mockMvc;

	// controller to be tested should be InjectMocks
	@InjectMocks 
	BookController api;

	
	// any service or Repository used by controller should be Mocked
	@Mock 
	LibraryService service;

	
	// Used to convert object to json string
	private JacksonTester<List<Book>> bookListJson;
	private JacksonTester<Book> bookJson;

	
	
	// Demo objects to be used as I/O to and from API endpoints
	Book book;
	List<Book> bookList;

	
	/*
	 * Gets called once as soon as this class is loaded for testing,
	 * the contents of method : initialSetup() here is applicable to all methods annotated as @Test in this class
	*/
	@Before
	public void initialSetup() {
		JacksonTester.initFields(this, new ObjectMapper());			// initialize JacksonTester
		mockMvc = MockMvcBuilders.standaloneSetup(api).build();		//initialize MockMvc with StandAlone Setup

		// Create dummy IO objects
		bookList = new ArrayList<>();
		book = new Book();
		book.setId(1);
		book.setName("TestBook");
		book.setAuthor("Junit5");
		book.setDescription("Dummy book");
		book.setNumPages("200");
		bookList.add(book);
	}

	
	// Testing GET method---------------------------------------------------------------------------------------------------
	
	
	
	@Test
	@DisplayName("Testing for endpoint: GET : lib/all")					// returns list of books
	public void testEndpoint_getAllBooks_positive() throws Exception {

		given(service.getAllBooks()).willReturn(bookList);
		MockHttpServletResponse response = mockMvc
				.perform(MockMvcRequestBuilders.get("/lib/all").accept(MediaType.APPLICATION_JSON)).andReturn()
				.getResponse();

		assertThat(response.getStatus()).isEqualTo(HttpStatus.SC_OK);
		assertThat(response.getContentAsString()).isEqualTo(bookListJson.write(bookList).getJson());
	}

	
	void abc() {
		System.out.println();
	}
	
	
	
	
	@Test
	@DisplayName("Positive Testing for endpoint: GET : /book/{id}")		// Return a book if id matches
	public void testEndpoint_getBook_positive() throws Exception {

		given(service.getBookById(anyInt())).willReturn(Optional.of(book).get());
		MockHttpServletResponse response = mockMvc
				.perform(MockMvcRequestBuilders.get("/lib/book/1").accept(MediaType.APPLICATION_JSON)).andReturn()
				.getResponse();

		assertThat(response.getStatus()).isEqualTo(HttpStatus.SC_OK);
		assertThat(response.getContentAsString()).isEqualTo(bookJson.write(book).getJson());
	}

	@Test
	@DisplayName("Negative Testing for endpoint: GET : /book/{id}")		// Return empty book object because id didn't match
	public void testEndpoint_getBook_negative() throws Exception {
		given(service.getBookById(any())).willReturn(null); 
		MockHttpServletResponse response = mockMvc
				.perform(MockMvcRequestBuilders.get("/lib/book/2").accept(MediaType.APPLICATION_JSON)).andReturn()
				.getResponse();

		assertThat(response.getStatus()).isEqualTo(HttpStatus.SC_OK);
		assertThat(response.getContentAsString()).isNotEqualTo(bookJson.write(book).getJson());
		assertThat(response.getContentAsString()).isEqualTo("null");
		assertThat(response.getContentAsString()).isEmpty();
	}

	
	
	
	// Testing POST method---------------------------------------------------------------------------------------------------
	
	
	@Test
	@DisplayName("Positive Testing for endpoint: POST : /book")
	public void testEndpoint_addOrUpdateBook_positive() throws Exception {	//Returns a book object with auto-generated id after saving a new book without id

		when(service.addOrUpdateBook(any(Book.class))).thenAnswer(new Answer<Book>() {	// Mocking save method
			@Override
			public Book answer(InvocationOnMock invocation) throws Throwable {
				Object[] arguments = invocation.getArguments();
				if (arguments != null && arguments.length > 0 && arguments[0] != null) {
					Book b = (Book) arguments[0];
					b.setId(1);		//mock a book object with auto generated id to be returned as answer
					return b;
				}
				return null;
			}
		});
		

		MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders
						.post("/lib/book").contentType(MediaType.APPLICATION_JSON)
						.content(bookJson.write(book).getJson()).accept(MediaType.APPLICATION_JSON))
						.andReturn().getResponse();

		Book bTemp = (Book) book.clone();
		bTemp.setId(1);		// clone existing book object in bTemp and set an id, so that bTemp matches mocked response/answer from the API
		
		assertThat(response.getStatus()).isEqualTo(HttpStatus.SC_OK);
		assertThat(response.getContentAsString()).isNotEmpty();
		assertThat(response.getContentAsString()).isEqualTo(bookJson.write(bTemp).getJson());
	}
	
	
	

}
