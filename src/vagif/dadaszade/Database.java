package vagif.dadaszade;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class Database {
	public static ArrayList<Student> forSearchStudents(String query) {
		Connection conn = null;
		ArrayList<Student> students = new ArrayList<Student>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_FX", "vagif", "2012");
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from students " + query);

			while (rs.next()) {
				int id = rs.getInt("id");
				String name1 = rs.getString("name");
				String surname1 = rs.getString("surname");
				String phone1 = rs.getString("phone");
				String unvan1 = rs.getString("adress");
				String school1 = rs.getString("school");
				String POB1 = rs.getString("place_og_birth");
				String FB1 = rs.getString("favourite_book");
				Date d = rs.getDate("birth_day");
				String nationality1 = rs.getString("nationality");
				String lang1=rs.getString("langs");
				LocalDate birthday1 = null;
				if (d == null) {

				} else {
					birthday1 = d.toLocalDate();
				}
				Student s = new Student(id, name1, surname1, phone1, unvan1, school1, POB1, FB1, birthday1,
						nationality1, lang1);
				students.add(s);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return students;
	}

	public static ArrayList<Course> loadCourse(String query) {
		Connection conn = null;
		ArrayList<Course> courses = new ArrayList<Course>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_FX", "vagif", "2012");
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from courses " + query);

			while (rs.next()) {
				Integer id = rs.getInt("id");
				String name1 = rs.getString("name");
				Course c=new Course(id,name1);
				courses.add(c);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return courses;
	}
	
	public static ArrayList<Course> loadStudentsCourse(Integer studentId) {
		Connection conn = null;
		ArrayList<Course> courses = new ArrayList<Course>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_FX", "vagif", "2012");
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select c.id,c.name,sc.course_length,sc.student_id from courses c join students_courses sc on c.id=sc.course_id where sc.student_id="+studentId+";");

			while (rs.next()) {
				int id = rs.getInt(1);
				String name1 = rs.getString(2);
				Integer time1=rs.getInt(3);
				Course c = new Course(id, name1,time1);
				c.setTime(time1);;
				courses.add(c);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return courses;
	}


	public static void addCourse(int studentId, int courseId, int courseLengthNumber) {

		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_FX", "vagif", "2012");
			Statement st = conn.createStatement();
			st.executeUpdate("insert into students_courses (student_id,course_id,course_length) values(" + studentId
					+ "," + courseId + "," + courseLengthNumber + ");");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
