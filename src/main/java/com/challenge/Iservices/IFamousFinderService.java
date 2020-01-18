package com.challenge.Iservices;

import com.challenge.entities.Person;

import java.util.List;

public interface IFamousFinderService {
    List<Person> famousFinder(String csvPathPeople, String csvPathMeetPeople);
}
