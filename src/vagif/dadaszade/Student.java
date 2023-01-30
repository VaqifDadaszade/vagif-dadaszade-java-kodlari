package vagif.dadaszade;

import java.time.LocalDate;

//burdakı dəyişən adları bazadakı sütun adlarıdır

public class Student {
	private Integer id;
	private String name;
	private String surname;
	private String phone;
	private String adress;
	private String school;
	private String place_og_birth;
	private String favourite_book;
	private LocalDate birth_day;
	private String nationality;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getPlace_og_birth() {
		return place_og_birth;
	}
	public void setPlace_og_birth(String place_og_birth) {
		this.place_og_birth = place_og_birth;
	}
	public String getFavourite_book() {
		return favourite_book;
	}
	public void setFavourite_book(String favourite_book) {
		this.favourite_book = favourite_book;
	}
	public LocalDate getBirth_day() {
		return birth_day;
	}
	public void setBirth_day(LocalDate birth_day) {
		this.birth_day = birth_day;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	
	public Student(Integer id, String name, String surname, String phone, String adress, String school,
			String place_og_birth, String favourite_book, LocalDate birth_day, String nationality) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.phone = phone;
		this.adress = adress;
		this.school = school;
		this.place_og_birth = place_og_birth;
		this.favourite_book = favourite_book;
		this.birth_day = birth_day;
		this.nationality = nationality;
	}
	
	
	
	

}