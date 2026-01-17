package com.codexdei.springboot.jpa.springboot_jpa;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.codexdei.springboot.jpa.springboot_jpa.entities.Person;
import com.codexdei.springboot.jpa.springboot_jpa.repositories.PersonRepository;

@SpringBootApplication
// Se implementa CommandLineRUnner para que se pueda manejar como aplicacion de
// consola
public class SpringbootJpaApplication implements CommandLineRunner {

	@Autowired
	private PersonRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

/* 		list();
		findOne();
		create(); 
		update(); */
		delete();
	}

	// @Transactional envuelve todo el metodo en una transaccion, es decir todas
	// deben ejecutarse correctamente
	// Para que se aplique, si una falla no se ejecuta ninguna
	// Se usa de esta manera para Consultas, es decir busquedas de informacion,
	// listar, select, etc
	@Transactional(readOnly = true)
	public void findOne() {

		// usando metodo con query personalizado
		repository.findOne(3L).ifPresent(p -> System.out.println(p));
		repository.findOneName("Dix").ifPresent(p -> System.out.println(p));
		// repository.findOneLikeName("ey").ifPresent(p -> System.out.println(p));
		// usando metodo gestionado por nombre de springboot
		// repository.findById(1L).ifPresent(p -> System.out.println(p));
		// repository.findByName("Robin").ifPresent(p -> System.out.println(p));
		repository.findByNameContaining("Robin").ifPresent(p -> System.out.println(p));
	}

	// @Transactional envuelve todo el metodo en una transaccion, es decir todas
	// deben ejecutarse correctamente
	// Para que se aplique, si una falla no se ejecuta ninguna
	// Se usa de esta manera para Consultas, es decir busquedas de informacion,
	// listar, select, etc
	@Transactional(readOnly = true)
	public void list() {

		// Listar toda la tabla persons
		// List<Person> persons = (List<Person>) repository.findAll();
		// List<Person> programinList = repository.findByProgrammingLanguage("Java");
		// List<Person> nombreLenguageLista =
		// repository.buscarPorNameYLenguajeProgramacion("Yorking","Java");
		List<Object[]> apellidoLenguageLista = repository.obtenerDatosPersona();

		// programinList.stream().forEach(System.out::println);
		// nombreLenguageLista.stream().forEach(System.out::println);
		apellidoLenguageLista.stream().forEach(p -> System.out.println("Apellido: " + p[0] + " Lenguage: " + p[1]));
	}

	// @Transactional envuelve todo el metodo en una transaccion, es decir todas
	// deben ejecutarse correctamente
	// Para que se aplique, si una falla no se ejecuta ninguna
	// A diferencia de las consultas se usa sin 'readOnly' porque en este caso no
	// son consultas, sino
	// operaciones que modifican las tablas
	@Transactional
	public void create() {

		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter name:");
		String name = scanner.next();

		System.out.println("Enter lastname:");
		String lastname = scanner.next();

		System.out.println("Enter programming Language:");
		String programmigLanguage = scanner.next();

		scanner.close();

		Person person = new Person(null, name, lastname, programmigLanguage);
		Person newPerson = repository.save(person);

		repository.findById(newPerson.getId()).ifPresent(System.out::println);
	}

	@Transactional
	public void update() {

		Scanner scanner = new Scanner(System.in);

		repository.findAll().forEach(System.out::println);
		System.out.println("Enter the ID to search:");
		Long id = scanner.nextLong();
		//limpiar buffer
		scanner.nextLine();

		Optional<Person> persOptional = repository.findById(id);

		if (persOptional.isPresent()) {

			Person person = persOptional.orElseThrow();

			System.out.println(person);

			System.out.println("Enter the number of the date to update:");
			System.out.println("1. Name");
			System.out.println("2. Lastname");
			System.out.println("3. Programming Language");
			System.out.print("Enter number:");
			int optionNumber = scanner.nextInt();
			//limpiar buffer
			scanner.nextLine();
			
			switch (optionNumber) {

				case 1 -> {

					System.out.println("Enter new name:");
					String name = scanner.nextLine();
					person.setName(name);
					repository.save(person);
				}
				case 2 -> {

					System.out.println("Enter new lastname:");
					String lastname = scanner.nextLine();
					person.setLastname(lastname);
					repository.save(person);
				}
				case 3 -> {

					System.out.println("Enter new programming language:");
					String programmingLanguage = scanner.nextLine();
					person.setProgrammingLanguage(programmingLanguage);
					repository.save(person);
				}

				default -> {
					System.out.println("The entered option is incorrect");
				}
			}
			
			System.out.println(person);
		
		}else{

			System.out.println("The ID entered does not exist");
		}
		scanner.close();
	}

	@Transactional
	public void delete(){

		Scanner scanner = new Scanner(System.in);

		repository.findAll().forEach(System.out::println);

		System.out.println("Enter the ID to delete:");
		Long id = scanner.nextLong();
		repository.deleteById(id);

		repository.findAll().forEach(System.out::println);

		scanner.close();
	}

}
