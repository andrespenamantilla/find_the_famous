package com.challenge.entities;

/*
 * @author  Andŕes Peña Mantilla
 * @version 1.0
 * @since   2020-01-16
 * Person Entity contains the DTO info of 'find_celebrity_0x.csv' row
 */
public class Person {
    Integer id;
    String name;

    /**
     * Getters & Setters Methods
     */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
