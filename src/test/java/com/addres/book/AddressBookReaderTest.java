package com.addres.book;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Test;

import com.addres.book.model.Adress;;

public class AddressBookReaderTest {

	@Test
	public void shouldReadAdressBookFromDefaulFile() throws URISyntaxException {
		// given
		Path path = Paths.get(ClassLoader.getSystemResource(AddressBookReader.ADDRESS_BOOK_DEFAULT).toURI());

		// when
		List<Adress> result = AddressBookReader.read(path);

		// then
		assertThat(result, not(result.isEmpty()));
		assertThat(result.size(), is(5));
	}

	@Test
	public void shouldReadAdressBookFromArgs() throws URISyntaxException {
		// given

		String[] args = { "Bill McKnight, Male, 16/03/77", "Paul Robinson, Male, 15/01/85",
				"Gemma Lane, Female, 20/11/91", "Sarah Stone, Female, 20/09/80", "Wes Jackson, Male, 14/08/74" };
		// when
		List<Adress> result = AddressBookReader.read(args);

		// then
		assertThat(result, not(result.isEmpty()));
		assertThat(result.size(), is(5));
	}

}
