package com.siloam.home;

import com.siloam.home.domain.Constant.SysConstant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class HomeApplicationTests {

	@Test
	void test1() {
		System.out.println(String.format("%010d", 12321));
	}
}
