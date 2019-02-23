package com.addres.book.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public class Adress {

	private static final DateFormat format = new SimpleDateFormat("dd/MM/yy");
	private static final String SEPARATOR = ", ";

	private final String name;
	private final String gender;
	private final Date dateOfBirth;

	private Adress(String name, String gender, Date date) {
		this.name = name;
		this.gender = gender;
		this.dateOfBirth = date;
	}
	
	public static Adress from(String line) throws ParseException {
		Adress result = null;
		if (line != null && !line.trim().isEmpty()) {
			String[] fields = line.split(SEPARATOR);
			result = new Adress(fields[0], fields[1], format.parse(fields[2]));
		}
		return result;
	}

	public Long ageDifferenceInDays(Adress compare) {
		Calendar myDate = Calendar.getInstance();
		myDate.setTime(getDateOfBirth());

		Calendar dateToCompare = Calendar.getInstance();
		dateToCompare.setTime(compare.getDateOfBirth());

		return ChronoUnit.DAYS.between(myDate.toInstant(), dateToCompare.toInstant());
	}

	public String getName() {
		return name;
	}

	public String getGender() {
		return gender;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	@Override
	public String toString() {
		return "Adress [name=" + name + ", gender=" + gender + ", dateOfBirth=" + dateOfBirth + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Adress other = (Adress) obj;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
