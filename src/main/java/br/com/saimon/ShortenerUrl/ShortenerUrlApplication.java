package br.com.saimon.ShortenerUrl;

import br.com.saimon.ShortenerUrl.domain.User;
import br.com.saimon.ShortenerUrl.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;
@EnableWebMvc
@SpringBootApplication
public class ShortenerUrlApplication {
	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(ShortenerUrlApplication.class, args);
	}

	@Bean
	public void createAdminUser(){
		String encriptedPassword = this.passwordEncoder().encode("pass");
		User adminUser = User.builder()
				.name("adminUser")
				.username("adminUser")
				.password(encriptedPassword)
				.roles(Arrays.asList(User.ROLE.ADMIN))
				.createdAt(new Date())
				.lastLogin(null)
				.loginCount(0L)
				.build();
		Optional<User> admin = userRepository.findByUsername("adminUser");
		if (admin.isEmpty()){
			userRepository.save(adminUser);
		}
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

}
