package com.challenge.famousfinder;

import com.challenge.Ibussinesslogic.IFamousFinder;
import com.challenge.Iutils.ICSVReadFile;
import com.challenge.entities.MeetPeople;
import com.challenge.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/*
 * @author  Andŕes Peña Mantilla
 * @version 1.0
 * @since   2020-01-16
 *
 * FamousFinder class provides the methods for validate that person is famous or not,
 * person is not known by anyone, and find famous people on a datasource
 */

@Component
public class FamousFinder implements IFamousFinder {

    @Autowired
    ICSVReadFile csvReadFile;

    /*
     * This method help to validate if a person is famous or not
     *
     * @param  meetPeopleGroup  contains the info of find_celebrity_knows_file_0x.csv
     * @param allPeopleInAGroup contains the info of find_celebrity_file_0x.csv
     * @param knowPerson is the index of the person for validate that he/she is famous or not
     * @return true is a famous or false is not a famous
     * @see   boolean
     * */

    public boolean thePersonIsFamous(List<MeetPeople> meetPeopleGroup, List<Person> allPeopleInAGroup, Integer knowPerson) {

        AtomicReference<Person> personAux = new AtomicReference<Person>();

        AtomicReference<Integer> count = new AtomicReference<>(0);

        Integer nMinusOnePeople = 0;

        allPeopleInAGroup.forEach(p -> {
            if (p.getId() == knowPerson) {
                personAux.set(p);
            }
        });

        nMinusOnePeople = allPeopleInAGroup.size() - 1;

        allPeopleInAGroup.forEach(person -> {
            meetPeopleGroup.forEach(personKnowsPerson -> {
                if (personKnowsPerson.getIdPerson() == person.getId() && personKnowsPerson.getKnownPerson() == knowPerson) {
                    count.getAndSet(count.get() + 1);
                    System.out.println(personKnowsPerson.getIdPerson() + " " + personKnowsPerson.getKnownPerson());
                }
            });
        });

        if (count.get() == nMinusOnePeople && personIsNotKnownByAnyone(knowPerson, meetPeopleGroup)) {
            System.out.println("The person is famous");
            return true;
        } else {
            System.out.println("The person is not famous");
            return false;
        }
    }

    /*
     * This returns if a person doesn't know anyone
     *
     * @param  knowPerson is the id of the person that he/she doesn't know anyone
     * @param meetPeopleGroup contains info that said whow knows who
     * @return true for the knows someone ,false if the person doesn't know anyone
     * @see   boolean
     * */

    public boolean personIsNotKnownByAnyone(Integer knowPerson, List<MeetPeople> meetPeopleGroup) {
        AtomicBoolean personIsNotKnownByAnyone = new AtomicBoolean(true);
        meetPeopleGroup.forEach(p -> {
            if (p.getIdPerson() == knowPerson) {
                personIsNotKnownByAnyone.set(false);
            }
        });
        return personIsNotKnownByAnyone.get();
    }


    /*
     * This returns the famous person on a group
     *
     * @param  meetPeoplePath contains the path of find_celebrity_knows_file_0x.csv
     * @param peoplePath contains the path of find_celebrity_file_0x.csv
     * @return list of a famous people (only one Person)
     * @see   List<Person>
     * */
    public List<Person> findFamousPeople(String peoplePath, String meetPeoplePath) {

        AtomicBoolean isFamous = new AtomicBoolean(false);

        List<Person> people = (List<Person>) csvReadFile.CSVRecordsToEntitiesList(Paths.get(peoplePath), Person.class);
        List<MeetPeople> meetPeopleGroup = (List<MeetPeople>) csvReadFile.CSVRecordsToEntitiesList(Paths.get(meetPeoplePath), MeetPeople.class);

        List<Person> famousPeople = new ArrayList<>();

        people.forEach(p -> {
            isFamous.set(thePersonIsFamous(meetPeopleGroup, people, p.getId()));
            if (isFamous.get()) {
                famousPeople.add(p);
            }
        });

        return famousPeople;
    }

}
