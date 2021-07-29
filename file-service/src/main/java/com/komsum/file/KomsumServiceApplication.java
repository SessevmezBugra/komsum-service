package com.komsum.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;


@EnableGlobalMethodSecurity(jsr250Enabled = true)
@SpringBootApplication
public class KomsumServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(KomsumServiceApplication.class, args);
	}

}
