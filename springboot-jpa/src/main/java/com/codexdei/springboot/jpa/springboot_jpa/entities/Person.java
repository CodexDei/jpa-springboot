package com.codexdei.springboot.jpa.springboot_jpa.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "persons")
public class Person {

    private Long id;
    private String name;
    private String lastname;
    private String programmingLanguage;

    
    //Si Una clase entity tiene un constructor con parametros, 
    //siempre debe tener un constructor vacio, porque es el que utiliza JPA
    public Person() {
    }

    public Person(Long id, String name, String lastname, String programmingLanguage) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.programmingLanguage = programmingLanguage;
    }



    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getProgrammingLanguage() {
        return programmingLanguage;
    }
    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }

    
}
