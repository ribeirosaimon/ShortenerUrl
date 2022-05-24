package br.com.saimon.ShortenerUrl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ShortenerUrlApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShortenerUrlApplication.class, args);
	}

}
