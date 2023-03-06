package vagif.dadaszade;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class StudentsTableController implements Initializable {
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
	private ImageView ProgLanIMG;
	@FXML
	private TableView<Student> studentsTable;
	@FXML
	private TableColumn<Student, String> idColumn;
	@FXML
	private TableColumn<Student, String> nameColumn;
	@FXML
	private TableColumn<Student, String> surnameColumn;
	@FXML
	private TableColumn<Student, Integer> phoneColumn;
	@FXML
	private TableColumn<Student, String> addressColumn;
	@FXML
	private TableColumn<Student, String> schoolColumn;
	@FXML
	private TableColumn<Student, String> pobColumn;
	@FXML
	private TableColumn<Student, String> fbColumn;
	@FXML
	private TableColumn<Student, LocalDate> birthDayColumn;
	@FXML
	private TableColumn<Student, String> nationalityColumn;
	@FXML
	private TableColumn<Student, String> langColumn;
	@FXML
	private Button newStudent;
	@FXML
	private TextField searchText;
	@FXML
	private CheckBox langENG, langGER, langRUS;

	private boolean updateMode = false;
	private int selectedStudentId = 0;

	@FXML
	private void saveStudentToDatabase() {
		if (updateMode) {

			Connection conn = null;

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_FX", "vagif", "2012");
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

				ResultSet rs = st.executeQuery("select * from students order by id desc");

				ArrayList<Student> students = new ArrayList<Student>();

				while (rs.next()) {
					Integer id = rs.getInt("id");
					String name1 = rs.getString("name");
					String surname1 = rs.getString("surname");
					String phone1 = rs.getString("phone");
					String address1 = rs.getString("adress");
					String school1 = rs.getString("school");
					String POB1 = rs.getString("place_og_birth");
					String FB1 = rs.getString("favourite_book");
					Date d = rs.getDate("birth_day");
					String nationality1 = rs.getString("nationality");
					String lang1 = rs.getString("langs");
					LocalDate birthday1 = null;
					if (d == null) {

					} else {
						birthday1 = d.toLocalDate();
					}

					Student s = new Student(id, name1, surname1, phone1, address1, school1, POB1, FB1, birthday1,
							nationality1, lang1);
					students.add(s);
				}

				ObservableList<Student> list = FXCollections.observableArrayList();
				list.addAll(students);
				studentsTable.setItems(list);

				st.executeUpdate("update students set name='" + name + "',surname='" + surname + "',phone='" + phone
						+ "',adress='" + adress + "',school='" + school + "',place_og_birth='" + POB
						+ "',favourite_book='" + FB + "',birth_day='" + birthday + "',nationality='" + nationality
						+ "' where id ='" + selectedStudentId);

				updateMode = false;
				newStudent.setText("New Student!");

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			loadStudent();
		} else {
			Connection conn = null;

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_FX", "vagif", "2012");
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
				boolean phoneValidation = Pattern.compile("[0-9]{3}-[0-9]{3}-[0-9]{2}-[0-9]{2}").matcher(phone)
						.matches();
				if (!phoneValidation) {
					Utility.showMessage("Warning", "Write the phone in the correct form !", Pos.TOP_CENTER, 4);
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

				String langs = "";
				if (langENG.isSelected()) {
					langs += "English";
				}
				if (langGER.isSelected()) {
					langs += "Germany";
				}
				if (langRUS.isSelected()) {
					langs += "Russian";
				}

				langs = langs.trim();

				st.executeUpdate(
						"insert into students(name,surname,phone,adress,school,place_og_birth,favourite_book,birth_day,nationality,langs) values('"
								+ name + "','" + surname + "','" + phone + "','" + adress + "','" + school + "','" + POB
								+ "','" + FB + "','" + birthday + "','" + nationality + "','" + langs + "');");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			loadStudent();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		studentRegisterName.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e) {
				TextField tf=(TextField) e.getSource();
				if(tf.getText().length()>=15) {
					if(tf.getText().length()>=15) {
						tf.setText(tf.getText().substring(0, 15));
					}
					e.consume(); //Hadisəni ləğv edən metod
				}
				if(e.getCharacter().matches("[0-9]")) {
					
				}else {
					e.consume();
				}
			}
		});
		
		{
			studentNationality.getItems().add("AZE");
			studentNationality.getItems().add("RUS");
			studentNationality.getItems().add("TUR");
			studentNationality.getItems().add("ENG");
			studentNationality.getItems().add("GER");
			studentNationality.getItems().add("USA");
			studentNationality.getItems().add("JAP");

			idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
			nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
			surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
			phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
			addressColumn.setCellValueFactory(new PropertyValueFactory<>("adress"));
			schoolColumn.setCellValueFactory(new PropertyValueFactory<>("school"));
			pobColumn.setCellValueFactory(new PropertyValueFactory<>("place_og_birth"));
			fbColumn.setCellValueFactory(new PropertyValueFactory<>("favourite_book"));
			birthDayColumn.setCellValueFactory(new PropertyValueFactory<>("birth_day"));
			nationalityColumn.setCellValueFactory(new PropertyValueFactory<>("nationality"));
			langColumn.setCellValueFactory(new PropertyValueFactory<>("langs"));

			loadStudent();
			studentsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		}
	}

	private void loadStudent() {
		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_FX", "vagif", "2012");
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from students order by id desc");
			ArrayList<Student> students = new ArrayList<Student>();

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
				String lang1 = rs.getString("langs");
				LocalDate birthday1 = null;
				if (d == null) {

				} else {
					birthday1 = d.toLocalDate();
				}
				Student s = new Student(id, name1, surname1, phone1, unvan1, school1, POB1, FB1, birthday1,
						nationality1, lang1);
				students.add(s);
			}
			ObservableList<Student> list = FXCollections.observableArrayList();
			list.addAll(students);
			studentsTable.setItems(list);
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

	@FXML
	private void deleteStudent() {
		List<Student> s = studentsTable.getSelectionModel().getSelectedItems();
		if (s.size() == 0) {
			Utility.showMessage("Warning", "Select a student from the list !", Pos.TOP_CENTER, 4);
			return;
		} else {
			Alert alert = new Alert(AlertType.CONFIRMATION, "Delete information?", ButtonType.YES, ButtonType.NO);
			alert.showAndWait();
			if (alert.getResult() == ButtonType.YES) {
				for (Student student : s) {
					Integer selectedID = student.getId();
					Utility.insertUpdateAndDel("delete from students where id=" + selectedID);
				}
			}
			loadStudent();
		}
	}

	@FXML
	private void editStudent() {
		Student s = studentsTable.getSelectionModel().getSelectedItem();
		if (s == null) {
			Utility.showMessage("Warning", "Select a student from the list !", Pos.TOP_CENTER, 4);
		} else {
			studentRegisterName.setText(s.getName());
			studentRegisterSurName.setText(s.getSurname());
			studentRegisterPhone.setText(s.getPhone());
			studentRegisterAdress.setText(s.getAdress());
			studentRegisterSchool.setText(s.getSchool());
			studentRegisterPOB.setText(s.getPlace_og_birth());
			studentRegisterFBook.setText(s.getFavourite_book());
			studentBirthDay.setValue(s.getBirth_day());
			studentNationality.setValue(s.getNationality());
			updateMode = true;
			selectedStudentId = s.getId();
			newStudent.setText("Save");
		}
	}

	@FXML
	private void searchStudents(KeyEvent event) {
		String search = searchText.getText();

		ObservableList<Student> list = FXCollections.observableArrayList();
		list.addAll(Database.forSearchStudents("where name like '%" + search + "'"));
		studentsTable.setItems(list);
	}

	@FXML
	private void addCourse(ActionEvent event) {
		Student std = studentsTable.getSelectionModel().getSelectedItem();
		if (std == null) {
			Utility.showMessage("Warning", "Select a student from the list !", Pos.TOP_CENTER, 4);
			return;
		}
		MyReference.student = std;
		try {
			Stage s = new Stage();
			s.initModality(Modality.APPLICATION_MODAL);
			s.setTitle("Courses");
			AnchorPane a = FXMLLoader.load(getClass().getResource("AddCourse.fxml"));
			Scene scene = new Scene(a);
			s.setScene(scene);
			s.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void showStudentCourses(ActionEvent event) {
		Student std = studentsTable.getSelectionModel().getSelectedItem();
		if (std == null) {
			Utility.showMessage("Warning", "Select a student from the list !", Pos.TOP_CENTER, 4);
			return;
		}
		MyReference.student = std;
		try {
			Stage s = new Stage();
			s.initModality(Modality.APPLICATION_MODAL);
			s.setTitle("Student's Courses");
			AnchorPane a = FXMLLoader.load(getClass().getResource("StudentsCourses.fxml"));
			Scene scene = new Scene(a);
			s.setScene(scene);
			s.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
