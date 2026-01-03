package com.codexdei.springboot.jpa.springboot_jpa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//Se implementa CommandLineRUnner para que se pueda manejar como aplicacion de consola
public class SpringbootJpaApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


	}

}
