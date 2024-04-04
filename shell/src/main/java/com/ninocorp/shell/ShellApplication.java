package com.ninocorp.shell;

import com.ninocorp.core.dummy.DummyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "com.ninocorp.core.dummy")
public class ShellApplication {

	@Autowired
	DummyService dummyService;

	public static void main(String[] args) {
		SpringApplication.run(ShellApplication.class, args);
	}

	@Bean
	public CommandLineRunner logProperty() {
		return args -> System.out.println(dummyService.message());
	}

}
