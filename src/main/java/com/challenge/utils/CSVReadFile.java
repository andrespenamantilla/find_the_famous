package com.challenge.utils;


import com.challenge.Iutils.ICSVReadFile;
import com.challenge.entities.MeetPeople;
import com.challenge.entities.Person;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Component
public class CSVReadFile implements ICSVReadFile {

    /*
     * Read a file a returns a type of list depending on the instance of the class
     *
     * @param  path is the path od the file
     * @param classType is the class that are sended for inicialize the type of the array
     * @return list depending of the class type
     * @see   List<?>
     * */

    public List<?> CSVRecordsToEntitiesList(Path path, Class<?> classType) {

        try {
            CSVParser csvParser = null;

            if (classType == Person.class) {
                csvParser = CSVParser.parse(path, Charset.defaultCharset(), CSVFormat.DEFAULT.withHeader("ID", "NAME"));
                return itetareCSVRecords(classType, csvParser.getRecords());
            }
            if (classType == MeetPeople.class) {
                csvParser = CSVParser.parse(path, Charset.defaultCharset(), CSVFormat.DEFAULT.withHeader("ID_PERSON", "KNOWN_PERSON"));
                return itetareCSVRecords(classType, csvParser.getRecords());

            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<?> itetareCSVRecords(Class<?> classType, List<CSVRecord> records) {
        List<Person> people = new ArrayList<>();
        List<MeetPeople> knownPeople = new ArrayList<>();

        records.stream().skip(1L).forEach(csvRecord -> {
            if (classType == Person.class) {
                Person person = new Person();
                person.setId(Integer.parseInt(csvRecord.get(0)));
                person.setName(csvRecord.get(1));
                people.add(person);
            }
            if (classType == MeetPeople.class) {
                MeetPeople personCanMeetAFamous = new MeetPeople();
                personCanMeetAFamous.setIdPerson(Integer.parseInt(csvRecord.get(0)));
                personCanMeetAFamous.setKnownPerson(Integer.parseInt(csvRecord.get(1)));
                knownPeople.add(personCanMeetAFamous);
            }

        });
        if (classType == Person.class) {
            return people;
        }
        if (classType == MeetPeople.class) {
            return knownPeople;
        }
        return null;
    }

    /*
     * Returns the path of a csv file
     *
     * @param csvFile is the name of the csvFIle
     * @return path is the complete path of the file
     * @see   String
     * */

    public String locatePathFile(String csvFile) {
        File file = new File(getClass().getClassLoader().getResource(csvFile).getFile());
        String path = file.getPath();
        return path;
    }
}
