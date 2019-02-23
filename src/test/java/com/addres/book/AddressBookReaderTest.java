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
	public void shouldReadAdressBook() throws URISyntaxException {
		// given
		Path path = Paths.get(ClassLoader.getSystemResource(AddressBookReader.ADDRESS_BOOK_DEFAULT).toURI());

		// when
		List<Adress> result = AddressBookReader.read(path);

		// then
		assertThat(result, not(result.isEmpty()));
		assertThat(result.size(), is(5));
	}

}
