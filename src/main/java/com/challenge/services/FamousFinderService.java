package com.challenge.services;

import com.challenge.Ibussinesslogic.IFamousFinder;
import com.challenge.Iservices.IFamousFinderService;
import com.challenge.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FamousFinderService implements IFamousFinderService {

    @Autowired
    IFamousFinder famousFinder;

    public List<Person> famousFinder(String csvPathPeople, String csvPathMeetPeople){
        return famousFinder.findFamousPeople(csvPathPeople,csvPathMeetPeople);
    }

}
