package com.test.junity.services;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class HomeServiceTest {
	HomeService service = new HomeService();

	@Test
	public void testAdd() {
		assertThat(service.add(5, 7)).isEqualTo(12);
	}
	
	@Test
	public void testSub() {
		assertThat(service.sub(5, 7)).isEqualTo(2);
	}

}
