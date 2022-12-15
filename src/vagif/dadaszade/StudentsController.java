package vagif.dadaszade;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

import javax.management.Notification;

import org.controlsfx.control.Notifications;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class StudentsController {
	@FXML
	private TextField studentRegisterName;
	@FXML
	private TextField studentRegisterSurName;
	@FXML
	private TextField studentRegisterPhone;
	@FXML
	private TextField studentRegisterAdress;
	@FXML
	private TextField studentRegisterSchool;
	@FXML
	private TextField studentRegisterPOB;
	@FXML
	private TextField studentRegisterFBook;
	@FXML
	private DatePicker studentBirthDay;
	@FXML
	private void saveStudentToDatabase() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection
					("jdbc:mysql://localhost:3306/student_FX", "vagif", "2012");
			Statement st = conn.createStatement();
			String name=studentRegisterName.getText();
			if(name.trim().equals("")) {
				Utility.showMessage("Warning", "Do not leave the name blank !", Pos.TOP_CENTER, 4);
				return;
			}
			String surname=studentRegisterSurName.getText();
			if(surname.trim().equals("")) {
				Utility.showMessage("Warning", "Do not leave the surname blank !", Pos.TOP_CENTER, 4);
				return;
			}
			String phone=studentRegisterPhone.getText();
			if(phone.trim().equals("")) {
				Utility.showMessage("Warning", "Do not leave the phone blank !", Pos.TOP_CENTER, 4);
				return;
			}
			String adress=studentRegisterAdress.getText();
			if(adress.trim().equals("")) {
				Utility.showMessage("Warning", "Do not leave the adress blank !", Pos.TOP_CENTER, 4);
				return;
			}
			String school=studentRegisterSchool.getText();
			String POB=studentRegisterPOB.getText();
			String FB=studentRegisterFBook.getText();
			LocalDate birthday =studentBirthDay.getValue();
			LocalDate nowdate=LocalDate.now();
			if(birthday.isAfter(nowdate)) {
				Utility.showMessage("Warning", "Do not leave the birthday blank !", Pos.TOP_CENTER, 4);
				return;
			}
			st.executeUpdate("insert into students(name,surname,phone,adress,school,place_og_birth,favourite_book,birth_day) values('"+name+"','"+surname+"','"+phone+"','"+adress+"','"+school+"','"+POB+"','"+FB+"','"+birthday+"');");
			
			ResultSet rs = st.executeQuery("select * from students");
			while(rs.next()) {
				String name1= rs.getString("name");
				String surname1= rs.getString("surname");
				String phone1= rs.getString("phone");
				String adress1= rs.getString("adress");
				String school1= rs.getString("school");
				String POB1= rs.getString("place_og_birth");
				String FB1= rs.getString("favourite_book");
				Date d =rs.getDate("birth_day");
				LocalDate birthday1= null;
				if(d == null) {
					
				}else {
					birthday=d.toLocalDate();
				}
				System.out.printf("Name: %s,"
						+ "SurName: %s,"
						+ "Phone: %s,"
						+ "Adress: %s,"
						+ "School: %s,"
						+ "Place_Of_Birth: %s,"
						+ "Favourite_Book: %s,"
						+ "Birth_Day: ",name1,surname1,phone1,adress1,school1,POB1,FB1,birthday1);
				System.out.println();
			}
			
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
