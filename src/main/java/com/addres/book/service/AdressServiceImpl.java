package com.addres.book.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.addres.book.model.Adress;

@Service
public class AdressServiceImpl implements AdressService {

	public Long countByGender(String gender, List<Adress> adresses) {
		Long count = null;
		if (adresses != null) {
			count = adresses.stream().filter(adress -> adress.getGender().equals(gender)).count();
		}

		return count;
	}

	public Adress findOldest(List<Adress> adresses) {
		if (adresses != null && !adresses.isEmpty()) {
			Collections.sort(adresses, (a1, a2) -> a1.getDateOfBirth().compareTo(a2.getDateOfBirth()));
			return adresses.get(0);
		}
		return null;
	}

	public Adress findByName(String name, List<Adress> adresses) {
		Adress result = null;
		if (adresses != null) {
			Optional<Adress> optional = adresses.stream().parallel().filter(adress -> adress.getName().equals(name))
					.findAny();
			result = optional.isPresent() ? optional.get() : null;
		}
		return result;

	}

}
