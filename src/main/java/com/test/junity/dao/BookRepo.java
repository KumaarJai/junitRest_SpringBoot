package com.test.junity.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.test.junity.models.Book;

@Repository
public interface BookRepo extends CrudRepository<Book, Integer> {
	
}
