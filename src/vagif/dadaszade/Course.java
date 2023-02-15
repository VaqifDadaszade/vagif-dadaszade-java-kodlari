package vagif.dadaszade;

public class Course {

	private Integer id;
	private String name;
	private Integer time;

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

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

	public Course(Integer id, String name, Integer time) {
		super();
		this.id = id;
		this.name = name;
		this.time = time;
	}
	public Course(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		return name + " Course";
	}

}
