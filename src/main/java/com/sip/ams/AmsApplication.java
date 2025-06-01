package com.sip.ams;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AmsApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(AmsApplication.class, args);
		new File("logs").mkdirs();
	}

}
