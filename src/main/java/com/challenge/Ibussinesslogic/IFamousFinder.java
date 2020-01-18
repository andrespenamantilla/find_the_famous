package com.challenge.Ibussinesslogic;

import com.challenge.entities.MeetPeople;
import com.challenge.entities.Person;

import java.util.List;
import java.util.Set;

public interface IFamousFinder {

    boolean thePersonIsFamous(List<MeetPeople> meetPeopleGroup, List<Person> allPeopleInAGroup, Integer knowPerson);
    List<Person> findFamousPeople(String peoplePath, String meetPeoplePath);
    boolean personIsNotKnownByAnyone(Integer knowPerson, List<MeetPeople> meetPeopleGroup);
}
