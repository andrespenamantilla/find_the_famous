package com.challenge.entities;


/*
 * @author  Andŕes Peña Mantilla
 * @version 1.0
 * @since   2020-01-16
 *
 * MeetPeople Entity contains the DTO info of 'find_celebrity_knows_0x.csv' row
 */
public class MeetPeople {
    private Integer idPerson;
    private Integer knownPerson;

    /**
     * Getters & Setters Methods
     */

    public Integer getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Integer idPerson) {
        this.idPerson = idPerson;
    }

    public Integer getKnownPerson() {
        return knownPerson;
    }

    public void setKnownPerson(Integer knownPerson) {
        this.knownPerson = knownPerson;
    }
}
