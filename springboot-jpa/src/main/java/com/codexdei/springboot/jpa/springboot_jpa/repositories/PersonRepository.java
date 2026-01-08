package com.codexdei.springboot.jpa.springboot_jpa.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.codexdei.springboot.jpa.springboot_jpa.entities.Person;
import java.util.List;


//Los datos genericos: el primero hace referencia a la entidad y el segundo a tipo de dato del id, que 
//por lo regular es Long
public interface PersonRepository extends CrudRepository<Person,Long>{

    //Aqui en nombre que demos al metodo si es importante, ya que tiene que ser en ingles, usar preferiblemente
    //el que sugiera el ID, ya que es un estandar e internamente ejecutara el Query, si por ejemplo
    //le colocamos por nombre al metodo buscarPorLenguageProgramacion no lo reconocera, a menos que nosotros
    //creemos el Query con la anotacion @Query como se aprecia en la segunda linea de codigo
    List<Person> findByProgrammingLanguage(String programmingLanguage);
    //Usando Query y digitando nosotros directamente el Query SOLO en ese caso podemos colocarle
    //cualquier nombre
    @Query("select p from Person p where p.name=?1 and p.programmingLanguage=?2")
    List<Person> buscarPorNameYLenguajeProgramacion(String name, String programmingLanguage);   

    //Query para NO obtener el objeto persona completo, sino solo algunos de sus atributos, no es lo ideal
    //por lo regular se obtiene todo y luego mediante codigo gestionamos lo que necesitamos
    @Query("select p.lastname, p.programmingLanguage from Person p")
    List<Object[]> obtenerDatosPersona();

}
