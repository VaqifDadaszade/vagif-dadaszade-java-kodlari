package vagif.dadaszade;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javax.management.Notification;

import org.controlsfx.control.Notifications;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class StudentsController implements Initializable {
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
	private ComboBox<String> studentNationality;
	@FXML
	private ListView<String> myListView;

	@FXML
	private void saveStudentToDatabase() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_FX", "vagif", "2012");
			Statement st = conn.createStatement();
			String name = studentRegisterName.getText();
			if (name.trim().equals("")) {
				Utility.showMessage("Warning", "Do not leave the name blank !", Pos.TOP_CENTER, 4);
				return;
			}
			String surname = studentRegisterSurName.getText();
			if (surname.trim().equals("")) {
				Utility.showMessage("Warning", "Do not leave the surname blank !", Pos.TOP_CENTER, 4);
				return;
			}
			String phone = studentRegisterPhone.getText();
			if (phone.trim().equals("")) {
				Utility.showMessage("Warning", "Do not leave the phone blank !", Pos.TOP_CENTER, 4);
				return;
			}
			String adress = studentRegisterAdress.getText();
			if (adress.trim().equals("")) {
				Utility.showMessage("Warning", "Do not leave the adress blank !", Pos.TOP_CENTER, 4);
				return;
			}
			String school = studentRegisterSchool.getText();
			if (school.trim().equals("")) {
				Utility.showMessage("Warning", "Do not leave the school blank !", Pos.TOP_CENTER, 4);
				return;
			}
			String POB = studentRegisterPOB.getText();
			if (POB.trim().equals("")) {
				Utility.showMessage("Warning", "Do not leave the place of birth blank !", Pos.TOP_CENTER, 4);
				return;
			}
			String FB = studentRegisterFBook.getText();
			if (FB.trim().equals("")) {
				Utility.showMessage("Warning", "Do not leave the favourite book blank !", Pos.TOP_CENTER, 4);
				return;
			}
			String nationality = studentNationality.getSelectionModel().getSelectedItem();
			LocalDate birthday = studentBirthDay.getValue();
			LocalDate nowdate = LocalDate.now();
			if (birthday.isAfter(nowdate)) {
				Utility.showMessage("Warning", "Do not leave the birthday blank !", Pos.TOP_CENTER, 4);
				return;
			}
			st.executeUpdate(
					"insert into students(name,surname,phone,adress,school,place_og_birth,favourite_book,birth_day,nationality) values('"
							+ name + "','" + surname + "','" + phone + "','" + adress + "','" + school + "','" + POB
							+ "','" + FB + "','" + birthday + "','" + nationality + "');");

			ResultSet rs = st.executeQuery("select * from students order by id desc");
			while (rs.next()) {
				String name1 = rs.getString("name");
				String surname1 = rs.getString("surname");
				String phone1 = rs.getString("phone");
				String adress1 = rs.getString("adress");
				String school1 = rs.getString("school");
				String POB1 = rs.getString("place_og_birth");
				String FB1 = rs.getString("favourite_book");
				Date d = rs.getDate("birth_day");
				String nationality1 = rs.getString("nationality");
				LocalDate birthday1 = null;
				if (d == null) {

				} else {
					birthday = d.toLocalDate();
				}
				System.out.printf(
						"Name: %s," + "SurName: %s," + "Phone: %s," + "Adress: %s," + "School: %s,"
								+ "Place_Of_Birth: %s," + "Favourite_Book: %s," + "Birth_Day: %s" + "Nationality: ",
						name1, surname1, phone1, adress1, school1, POB1, FB1, birthday1, nationality1);
				System.out.println();
			}

			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		{
			studentNationality.getItems().add("AZ");
			studentNationality.getItems().add("RUS");
			studentNationality.getItems().add("TUR");
			studentNationality.getItems().add("ENG");
			studentNationality.getItems().add("GER");
			studentNationality.getItems().add("USA");
			studentNationality.getItems().add("JAP");
		}
	}

	@FXML
	private void onAddToListView() {
		String name1 = studentRegisterName.getText();
		myListView.getItems().add(name1);
	}

	@FXML
	private void deleteName() {
		int selectedIndex = myListView.getSelectionModel().getSelectedIndex();
		if (selectedIndex == -1) {
			Utility.showMessage("Warning", "Select from the list", Pos.TOP_CENTER, 5);
			return;
		}
		myListView.getItems().remove(selectedIndex);
	}
	@FXML
	private void selectName() {
		String selectName= myListView.getSelectionModel().getSelectedItem();
		Utility.showMessage("You selected this from the table", selectName, Pos.TOP_CENTER, 4);
	}
	@FXML
	private void editTable() {
		String name1=studentRegisterName.getText();
		int selectedIndex = myListView.getSelectionModel().getSelectedIndex();
		if(selectedIndex<0) {
			Utility.showMessage("Warning", "You edit this from the table", Pos.TOP_CENTER, 4);
			myListView.getItems().set(selectedIndex, name1);
		}
	}
}
