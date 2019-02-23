package com.addres.book.service;

import static java.util.Comparator.comparing;
import static java.util.function.BinaryOperator.minBy;

import java.util.List;
import org.springframework.stereotype.Service;
import com.addres.book.model.Adress;

@Service
public class AdressServiceImpl implements AdressService {

	public Long countByGender(String gender, List<Adress> adresses) {
		if (adresses != null) {
			return adresses.stream().filter(adress -> adress.getGender().equals(gender)).count();
		}
		return null;
	}

	public Adress findOldest(List<Adress> adresses) {
		if (adresses != null) {
			return adresses.stream().reduce(minBy(comparing(Adress::getDateOfBirth))).orElse(null);
		}
		return null;
	}

	public Adress findByName(String name, List<Adress> adresses) {
		if (adresses != null) {
			return adresses.stream().filter(adress -> name.equals(adress.getName())).findAny().orElse(null);
		}
		return null;
	}

}
