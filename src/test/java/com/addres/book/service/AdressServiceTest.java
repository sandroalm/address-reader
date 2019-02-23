package com.addres.book.service;

import static com.addres.book.model.Adress.from;
import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.text.ParseException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.addres.book.model.Adress;

public class AdressServiceTest {

	private static final String WES_JACKSON = "Wes Jackson, Male, 14/08/74";
	private static final String SARAH_STONE = "Sarah Stone, Female, 20/09/80";
	private static final String GEMMA_LANE = "Gemma Lane, Female, 20/11/91";
	private static final String PAUL_ROBINSON = "Paul Robinson, Male, 15/01/85";
	private static final String BILL_MC_KNIGHT = "Bill McKnight, Male, 16/03/77";
	private List<Adress> adresses;
	private AdressService adressService = new AdressServiceImpl();

	@Before
	public void setUp() throws ParseException {
		adresses = asList(from(BILL_MC_KNIGHT), from(PAUL_ROBINSON), from(GEMMA_LANE), from(SARAH_STONE),
				from(WES_JACKSON));
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
		Adress oldest = from(WES_JACKSON);

		// when
		Adress result = adressService.findOldest(adresses);

		// then
		assertThat(result, is(oldest));
	}

	@Test
	public void shouldFindByName() throws ParseException {
		// given
		Adress bill = from(BILL_MC_KNIGHT);
		String nameToFind = "Bill McKnight";

		// when
		Adress result = adressService.findByName(nameToFind, adresses);

		// then
		assertThat(result, is(bill));
	}

}
