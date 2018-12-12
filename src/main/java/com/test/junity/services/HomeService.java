package com.test.junity.services;

import org.springframework.stereotype.Service;

@Service
public class HomeService {
	public int add(int a, int b) {
		return a+b;
	}
	
	public int sub(int a, int b) {
		return (a>b)? a-b : b-a;
	}
}
