package com.test.junity.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.junity.dao.BookRepo;
import com.test.junity.models.Book;

@Service
public class LibraryService {
	
	@Autowired
	BookRepo repo;
	
	public List<Book> getAllBooks(){
		List<Book> books = new ArrayList<Book>();
		repo.findAll().forEach(book -> books.add(book));
		return books;
	}
	
	public Book getBookById(int id) {
		return repo.findById(id).get();
	}
	
	public Book addOrUpdateBook(Book b) {
		System.out.println("Adding/Updating book : "+b.toString());
		return repo.save(b);
	}
	
	public void deleteBook(int id) {
		System.out.println("Deleting book ID : "+id);
		repo.deleteById(id);
	}
}
