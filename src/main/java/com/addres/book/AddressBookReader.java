package com.addres.book;

import static java.lang.ClassLoader.getSystemResource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
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

		Path path = findPath(args);

		List<Adress> adresses = AddressBookReader.read(path);
		Adress bill = service.findByName("Bill McKnight", adresses);
		Adress paul = service.findByName("Paul Robinson", adresses);

		logger.info("Who is the oldest person in the address book? " + service.findOldest(adresses));
		logger.info("How many males are in the address book? " + service.countByGender("Male", adresses));
		logger.info("How many days older is Bill than Paul? " + bill.ageDifferenceInDays(paul));
	}

	private static Path findPath(String[] args) throws URISyntaxException {
		String path = args != null && args.length > 0 ? args[0] : ADDRESS_BOOK_DEFAULT;
		return Paths.get(getSystemResource(path).toURI());
	}
	
	public static List<Adress> read(Path path) {
		List<Adress> result = new ArrayList<>();
		try (Stream<String> stream = Files.lines(path)) {
			stream.forEach(line -> {
				try {
					result.add(Adress.from(line));
				} catch (ParseException e) {
					logger.error("Not possible to parse Adress from line" + line);
				}
			});
		} catch (IOException e) {
			logger.error(e.getMessage());
			return null;
		}

		return result;
	}

}
