package com.mipres;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MipresApplication {

	public static void main(String[] args) {
		SpringApplication.run(MipresApplication.class, args);
	}

}
