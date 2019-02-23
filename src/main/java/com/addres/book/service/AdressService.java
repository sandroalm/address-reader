package com.addres.book.service;

import java.util.List;

import com.addres.book.model.Adress;

public interface AdressService {

	Long countByGender(String gender, List<Adress> adresses);

	Adress findOldest(List<Adress> adresses);

	Adress findByName(String string, List<Adress> adresses);

}
