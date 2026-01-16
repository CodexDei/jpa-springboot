package com.codexdei.springboot.jpa.springboot_jpa.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.codexdei.springboot.jpa.springboot_jpa.entities.Person;
import java.util.List;
import java.util.Optional;


//Los datos genericos: el primero hace referencia a la entidad y el segundo a tipo de dato del id, que 
//por lo regular es Long
public interface PersonRepository extends CrudRepository<Person,Long>{

    @Query("select p from Person p where p.id=?1")
    Optional<Person> findOne(Long id);
    @Query("select p from Person p where p.name=?1")
    Optional<Person> findOneName(String name);

    //busqueda por coincidencia, es decir empiece por o termine por determinada letra(s) o vocal(s)
    @Query("select p from Person p where p.name like %?1%")
    Optional<Person> findOneLikeName(String name);


    //Aqui en nombre que demos al metodo si es importante, ya que tiene que ser en ingles, usar preferiblemente
    //el que sugiera el ID, ya que es un estandar e internamente ejecutara el Query, si por ejemplo
    //le colocamos por nombre al metodo buscarPorLenguageProgramacion no lo reconocera, a menos que nosotros
    //creemos el Query con la anotacion @Query como se aprecia mas abajo
    List<Person> findByProgrammingLanguage(String programmingLanguage);
    //si usamos el nombre estandar del nombre del metodo ya tiene implementado internamente el Query
    Optional<Person> findByName(String name);
    //buscando por coincidencia, si le agregamos 'Containing' al nombre del metodo es como si hicieramos un
    //like
    Optional<Person> findByNameContaining(String name);
    
    //Usando Query y digitando nosotros directamente el Query SOLO en ese caso podemos colocarle
    //cualquier nombre
    @Query("select p from Person p where p.name=?1 and p.programmingLanguage=?2")
    List<Person> buscarPorNameYLenguajeProgramacion(String name, String programmingLanguage);   

    //Query para NO obtener el objeto persona completo, sino solo algunos de sus atributos, no es lo ideal
    //por lo regular se obtiene todo y luego mediante codigo gestionamos lo que necesitamos
    @Query("select p.lastname, p.programmingLanguage from Person p")
    List<Object[]> obtenerDatosPersona();

}
