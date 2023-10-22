package com.siloam.home;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HomeApplicationTests {

	@Test
	void test1() {
		System.out.println(String.format("%010d", 12321));
	}
}
