package com.addres.book.service;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.addres.book.model.Adress;

public class AdressServiceTest {

	private List<Adress> adresses;
	private AdressService adressService = new AdressServiceImpl();

	@Before
	public void setUp() throws ParseException {
		adresses = Arrays.asList(Adress.from("Bill McKnight, Male, 16/03/77"),
				Adress.from("Paul Robinson, Male, 15/01/85"), Adress.from("Gemma Lane, Female, 20/11/91"),
				Adress.from("Sarah Stone, Female, 20/09/80"), Adress.from("Wes Jackson, Male, 14/08/74"));
	}

	@Test
	public void shouldCountGender() {
		// given
		String gender = "Male";
		Long numberOfMalesInTheFile = 3l;

		// when
		Long countByGender = adressService.countByGender(gender, adresses);

		// then
		assertThat(countByGender, is(numberOfMalesInTheFile));
	}

	@Test
	public void shouldFindTheOldest() throws ParseException {
		// given
		Adress oldest = Adress.from("Wes Jackson, Male, 14/08/74");

		// when
		Adress result = adressService.findOldest(adresses);

		// then
		assertThat(result, is(oldest));
	}

	@Test
	public void shouldFindByName() throws ParseException {
		// given
		Adress bill = Adress.from("Bill McKnight, Male, 16/03/77");
		String nameToFind = "Bill McKnight";

		// when
		Adress result = adressService.findByName(nameToFind, adresses);

		// then
		assertThat(result, is(bill));
	}

}
