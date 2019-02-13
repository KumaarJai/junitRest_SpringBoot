package com.test.junity.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.test.junity.models.Book;
import com.test.junity.services.LibraryService;

public class BookController_ModuleTest{

	@InjectMocks
	BookController api;
	
	@Mock
	LibraryService service;
	
	Book book;
	List<Book> books;
	
	@BeforeEach
	void setUp()throws Exception{
		MockitoAnnotations.initMocks(this);
		books = new ArrayList<>();
		book = new Book();
		book.setId(1);
		book.setName("TestBook");
		book.setAuthor("Junit5");
		book.setDescription("Dummy book");
		book.setNumPages("200");
		
		books.add(book);
	}
	
	@Test
	public void testEndpoint_getAllBooks() {
		when(service.getAllBooks()).thenReturn(books);
		List<Book> booksList = api.allBooks();
		
		assertNotNull(booksList);
		assertEquals(1, booksList.size());
		assertEquals("TestBook", booksList.get(0).getName());
		
	}
	
	

}
