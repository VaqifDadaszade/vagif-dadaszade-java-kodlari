package vagif.dadaszade;

import java.awt.Desktop.Action;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MenuController implements Initializable{

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	@FXML
	private void openJavaPage(ActionEvent event) {
		try {
			Stage s = new Stage();
			s.initModality(Modality.APPLICATION_MODAL);
			s.setTitle("About the Java");
			AnchorPane a = FXMLLoader.load(getClass().getResource("Java.fxml"));
			Scene scene = new Scene(a);
			s.setScene(scene);
			s.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void openSQLPage(ActionEvent event) {
		try {
			Stage s = new Stage();
			s.initModality(Modality.APPLICATION_MODAL);
			s.setTitle("About the SQL");
			AnchorPane a = FXMLLoader.load(getClass().getResource("SQL.fxml"));
			Scene scene = new Scene(a);
			s.setScene(scene);
			s.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void openLogin(ActionEvent event) {
		try {
			Stage s = new Stage();
			s.initModality(Modality.APPLICATION_MODAL);
			s.setTitle("Login");
			AnchorPane a = FXMLLoader.load(getClass().getResource("Login.fxml"));
			Scene scene = new Scene(a);
			s.setScene(scene);
			s.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void openStudentsPage(ActionEvent event) {
		try {
			Stage s = new Stage();
			s.initModality(Modality.APPLICATION_MODAL);
			s.setTitle("Student's List");
			AnchorPane a = FXMLLoader.load(getClass().getResource("Students2Table.fxml"));
			Scene scene = new Scene(a);
			s.setScene(scene);
			s.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
