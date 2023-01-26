package vagif.dadaszade;

import java.time.LocalDate;

public class Student {
	private Integer id;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getPOB() {
		return POB;
	}

	public void setPOB(String pOB) {
		POB = pOB;
	}

	public String getFB() {
		return FB;
	}

	public void setFB(String fB) {
		FB = fB;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	private String name;
	private String surname;
	private String phone;
	private String address;
	private String school;
	private String POB;
	private String FB;
	private LocalDate birthday;
	private String nationality;
	
	public Student(Integer id,String name, String surname, String phone1, String address, String school, String pOB, String fB,
			LocalDate birthday, String nationality) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.phone = phone1;
		this.address = address;
		this.school = school;
		this.POB = pOB;
		this.FB = fB;
		this.birthday = birthday;
		this.nationality = nationality;
	}
	
	
}
