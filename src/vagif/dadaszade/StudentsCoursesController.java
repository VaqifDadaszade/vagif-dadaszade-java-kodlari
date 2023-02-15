package vagif.dadaszade;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class StudentsCoursesController implements Initializable{
	
    @FXML
    private TableColumn<Course, Integer> timeColumn;
    
    @FXML
    private TableColumn<Course, String> nameColumn;

    @FXML
    private TableView<Course> studentsCoursesTable;

    @FXML
    private TableColumn<Course, Integer> idColumn;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
		ObservableList<Course> coursesList=FXCollections.observableArrayList();
		ArrayList<Course> courses=new ArrayList<Course>();
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
		courses=Database.loadStudentsCourse(MyReference.student.getId());
		
		courses=Database.loadStudentsCourse(72);
		
		coursesList.addAll(courses);
		
		studentsCoursesTable.setItems(coursesList);
		
	}	
	
}
