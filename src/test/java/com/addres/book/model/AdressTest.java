package com.addres.book.model;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

public class AdressTest {

	private final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yy");

	@Test
	public void shouldBuildAdressFromLine() throws ParseException {
		// given
		final String nameOnTheLine = "Paul Robinson";
		final String genderOnTheLine = "Male";
		final String dateOnTheLine = "15/01/85";
		final String separator = ", ";

		String line = nameOnTheLine + separator + genderOnTheLine + separator + dateOnTheLine;

		// when
		Adress result = Adress.from(line);

		// then
		assertThat(result.getName(), is(nameOnTheLine));
		assertThat(result.getGender(), is(genderOnTheLine));
		assertThat(result.getDateOfBirth(), is(DATE_FORMAT.parse(dateOnTheLine)));
	}

	@Test
	public void shouldCountsHowManyDaysOlder() throws ParseException {
		// given
		Adress bill = Adress.from("Bill McKnight, Male, 16/03/77");
		Adress paul = Adress.from("Paul Robinson, Male, 15/01/85");
		Long ageDiferenceInDays = 2862l;

		// when
		Long result = bill.ageDifferenceInDays(paul);

		// then
		assertThat(result, is(ageDiferenceInDays));
	}
}
