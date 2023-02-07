package vagif.dadaszade;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class AddCourseController implements Initializable {

	@FXML
	private Label studentName;
	@FXML
	private ComboBox<Course> courseList;
	@FXML
	private ComboBox<Integer> courseLenght;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

//		for (int i = 3; i < 18; i+=3) {
//			courseLenght.getItems().add(i);
//		}

		Integer[] myOwnCourseLength = { 6, 9, 12, 24, 28 };
		courseLenght.getItems().addAll(myOwnCourseLength);

		courseLenght.getSelectionModel().select(0);

		studentName.setText(MyReference.student.getName());
		courseList.getItems().addAll(Database.loadCourse(""));

		courseList.getSelectionModel().select(0);

	}

	@FXML
	private void saveCourse(ActionEvent event) {
		int studentId = MyReference.student.getId();
		int courseId = courseList.getValue().getId();
		int courseLengthNumber = courseLenght.getValue();

		Database.addCourse(studentId, courseId, courseLengthNumber);
	}

}
