package com.codexdei.springboot.jpa.springboot_jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.codexdei.springboot.jpa.springboot_jpa.entities.Person;
import com.codexdei.springboot.jpa.springboot_jpa.repositories.PersonRepository;

@SpringBootApplication
//Se implementa CommandLineRUnner para que se pueda manejar como aplicacion de consola
public class SpringbootJpaApplication implements CommandLineRunner{

	@Autowired
	private PersonRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		//Listar toda la tabla persons
		//List<Person> persons = (List<Person>) repository.findAll();
		//List<Person> programinList = repository.findByProgrammingLanguage("Java");
		//List<Person> nombreLenguageLista = repository.buscarPorNameYLenguajeProgramacion("Yorking","Java");
		List<Object[]> apellidoLenguageLista = repository.obtenerDatosPersona();

		//programinList.stream().forEach(System.out::println);
		//nombreLenguageLista.stream().forEach(System.out::println);
		apellidoLenguageLista.stream().forEach(p -> System.out.println("Apellido: " + p[0] + " Lenguage: " + p[1]));
	}

}
