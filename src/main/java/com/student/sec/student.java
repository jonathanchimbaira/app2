package com.student.sec;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class student {
    @Id
    private Long id;
    private String name;
    private String surname;

    public student() {
    }

    public student(Long id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
