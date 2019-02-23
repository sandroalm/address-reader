package com.addres.book;

import static java.lang.ClassLoader.getSystemResource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.addres.book.model.Adress;
import com.addres.book.service.AdressService;

public class AddressBookReader {

	public static final String ADDRESS_BOOK_DEFAULT = "AddressBook";
	private static final Logger logger = Logger.getLogger(AddressBookReader.class);
	private static final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(
			AppConfig.class);

	public static void main(String[] args) throws URISyntaxException {
		// demonstration
		AdressService service = applicationContext.getBean(AdressService.class);

		List<Adress> adresses = args != null ? read(args)
				: read(Paths.get(getSystemResource(ADDRESS_BOOK_DEFAULT).toURI()));

		Adress bill = service.findByName("Bill McKnight", adresses);
		Adress paul = service.findByName("Paul Robinson", adresses);

		logger.info("Who is the oldest person in the address book? " + service.findOldest(adresses));
		logger.info("How many males are in the address book? " + service.countByGender("Male", adresses));
		logger.info("How many days older is Bill than Paul? " + bill.ageDifferenceInDays(paul));
	}

	public static List<Adress> read(Path path) {
		try (Stream<String> stream = Files.lines(path)) {
			return stream.map(line -> {
				return fromLIne(line);
			}).collect(Collectors.toList());
		} catch (IOException e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	public static List<Adress> read(String[] lines) {
		return Arrays.stream(lines).map(line -> fromLIne(line)).collect(Collectors.toList());
	}

	private static Adress fromLIne(String line) {
		try {
			return Adress.from(line);
		} catch (ParseException e) {
			logger.error("Not possible to parse Adress from line" + line);
		}
		return null;
	}

}
