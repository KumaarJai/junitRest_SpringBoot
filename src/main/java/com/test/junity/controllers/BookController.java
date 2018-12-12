package com.test.junity.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.junity.models.Book;
import com.test.junity.services.LibraryService;

@RestController
@RequestMapping("lib")
public class BookController {
	
	@Autowired
	LibraryService service;
	
	@GetMapping("/all")
	public List<Book> allBooks(){
		return service.getAllBooks();
	}
	
	@GetMapping("/book/{id}")
	public Book getBook(@PathVariable("id") int id){
		return service.getBookById(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteBook(@PathVariable("id") int id){
		service.deleteBook(id);
		return "Book deleted successfully...";
	}
	
	@PostMapping("/book")
	public Book addOrUpdateBook(@RequestBody Book book){
		service.addOrUpdateBook(book);
		return book;
	}
}
