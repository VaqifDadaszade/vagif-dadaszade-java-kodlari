package vagif.dadaszade;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;

import javax.management.Notification;

import org.controlsfx.control.Notifications;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

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
	private Label rowCountLabel;
	@FXML
	private ComboBox<String> studentNationality;
	@FXML
	private ListView<String> myListView;
	@FXML
	private ImageView ProgLanIMG;
	@FXML
	private TableView<Student> studentsTable;
	@FXML
	private TableColumn<Student,String> idColumn;
	@FXML
	private TableColumn<Student,String> nameColumn;
	@FXML
	private TableColumn<Student,String> surnameColumn;
	@FXML
	private TableColumn<Student,Integer> phoneColumn;
	@FXML
	private TableColumn<Student,String> addressColumn;
	@FXML
	private TableColumn<Student,String> schoolColumn;
	@FXML
	private TableColumn<Student,String> pobColumn;
	@FXML
	private TableColumn<Student,String> fbColumn;
	@FXML
	private TableColumn<Student,LocalDate> birthDayColumn;
	@FXML
	private TableColumn<Student,String> nationalityColumn;
	
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
			
			ArrayList<Student> students = new ArrayList<Student>();
			
			while (rs.next()) {
				int id = rs.getInt("id");
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
				
				Student s = new Student(id, name1, surname1, null, adress1, school1, POB1, FB1, birthday1, nationality1);
				students.add(s);
			}
			
			ObservableList<Student> list= FXCollections.observableArrayList();
			list.addAll(students);
			studentsTable.setItems(list);

			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
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
			surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surName"));
			phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
			addressColumn.setCellValueFactory(new PropertyValueFactory<>("adress"));
			schoolColumn.setCellValueFactory(new PropertyValueFactory<>("school"));
			pobColumn.setCellValueFactory(new PropertyValueFactory<>("place_og_birth"));
			fbColumn.setCellValueFactory(new PropertyValueFactory<>("favourite_book"));
			birthDayColumn.setCellValueFactory(new PropertyValueFactory<>("birth_day"));
			nationalityColumn.setCellValueFactory(new PropertyValueFactory<>("nationality"));
			
		}
	}

	String[] images = {"Delphi.png","Fstar-official-logo-2015.png","ISO_C++_Logo.svg.png","java_logo.jpg","jython.png"
			,"Python-Symbol.png","SQL_Logo.png"};
	int i=0;
	@FXML
	private void showIMG() {
		File file= new File("images/"+images[i++]);
		if(i==images.length) {
			i=0;
		}
		Image image = new Image(file.toURI().toString());
		ProgLanIMG.setImage(image);
	}
}