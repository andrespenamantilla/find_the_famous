package com.challenge;

import com.challenge.Ibussinesslogic.IFamousFinder;
import com.challenge.Iservices.IFamousFinderService;
import com.challenge.Iutils.ICSVReadFile;
import com.challenge.entities.MeetPeople;
import com.challenge.entities.Person;
import com.challenge.utils.CSVFiles;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.nio.file.Paths;
import java.util.List;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration
@SpringBootTest
class ChallengeApplicationTests {

	@Autowired
	private ICSVReadFile csvReadFile;

	@Autowired
	private IFamousFinder iFamousFinder;

	@Autowired
	IFamousFinderService famousFinderService;

	@Test
	void validateWhenAPersonIsFamous() {

		List<Person> people = (List<Person>) csvReadFile.CSVRecordsToEntitiesList(Paths.get(csvReadFile.locatePathFile(CSVFiles.find_celebrity_01)), Person.class);
		List<MeetPeople> meetPeopleGroup = (List<MeetPeople>) csvReadFile.CSVRecordsToEntitiesList(Paths.get(csvReadFile.locatePathFile(CSVFiles.find_celebrity_knows_01)), MeetPeople.class);

		// Then
		boolean isFamous= iFamousFinder.thePersonIsFamous(meetPeopleGroup,people,4);

		// Assert
		Assert.assertTrue(isFamous);
	}


	@Test
	void validateWhenAPersonIsNotFamous() {
		List<Person> people = (List<Person>) csvReadFile.CSVRecordsToEntitiesList(Paths.get(csvReadFile.locatePathFile(CSVFiles.find_celebrity_01)), Person.class);
		List<MeetPeople> meetPeopleGroup = (List<MeetPeople>) csvReadFile.CSVRecordsToEntitiesList(Paths.get(csvReadFile.locatePathFile(CSVFiles.find_celebrity_knows_01)), MeetPeople.class);

		// Then
		boolean isFamous= iFamousFinder.thePersonIsFamous(meetPeopleGroup,people,2);

		// Assert
		Assert.assertTrue(!isFamous);
	}

	@Test
	void validateThatAFamousExistInCSVDatasource(){
		// Then
		List<Person> famous = famousFinderService.famousFinder(csvReadFile.locatePathFile(CSVFiles.find_celebrity_01), csvReadFile.locatePathFile(CSVFiles.find_celebrity_knows_01));

		// Assert
		Assert.assertTrue(!famous.isEmpty());
		Assert.assertTrue(famous.size() ==1);
		Assert.assertTrue(famous.get(0).getName().equals("SCARLETT JOHANSSON"));
	}

	@Test
	void validateThatAFamousNotExistInCSVDatasource(){
		// Then
		List<Person> famous = famousFinderService.famousFinder(csvReadFile.locatePathFile(CSVFiles.find_celebrity_02), csvReadFile.locatePathFile(CSVFiles.find_celebrity_knows_02));

		// Assert
		Assert.assertTrue(famous.isEmpty());
	}

	@Test
	void validateWhenTheCSVAreEmpty() {

		List<Person> people = (List<Person>) csvReadFile.CSVRecordsToEntitiesList(Paths.get(csvReadFile.locatePathFile(CSVFiles.find_celebrity_03)), Person.class);
		List<MeetPeople> meetPeopleGroup = (List<MeetPeople>) csvReadFile.CSVRecordsToEntitiesList(Paths.get(csvReadFile.locatePathFile(CSVFiles.find_celebrity_knows_03)), MeetPeople.class);

		// Assert
		Assert.assertTrue(people.isEmpty());
		Assert.assertTrue(meetPeopleGroup.isEmpty());
	}
}
