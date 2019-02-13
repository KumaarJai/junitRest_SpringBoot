package com.test.junity.services;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.test.junity.models.Book;
import com.test.junity.models.OutputEntity;

public class abc {

	@ParameterizedTest
	@ValueSource(ints = { 2,4,6,8 })
	void palindromes(int n) {
		assertTrue(n%2==0);
	}

	@ParameterizedTest
	@ValueSource(classes = {Book.class, OutputEntity.class})
	void palindromes(Book b, OutputEntity out) {
		assertTrue(b.getName() == out.getName());
		assertThat(b.getName(), equalTo("Ajay"));
	}

}
