package com.siloam.home;

import com.example.postgrestest.PostgreSQLRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan
public class HomeApplication {
	public static void main(String[] args) {

		SpringApplication.run(HomeApplication.class, args);

	}

}
