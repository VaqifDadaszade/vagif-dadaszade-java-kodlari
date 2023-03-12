package vagif.dadaszade;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SignupController {

	@FXML
	private TextField usernameText;

	@FXML
	private PasswordField passwordText;

	@FXML
	private TextField teacherName;

	@FXML
	private TextField teacherSurname;

	@FXML
	private void onSignUp() {

		try {
			String username = usernameText.getText();
			String password = passwordText.getText();
			String name = teacherName.getText();
			String surname = teacherSurname.getText();

			// username is have or have not?
			boolean userExists = false;
			userExists=Database.checkUser(username);
			// If you have one, create an account and give notifications
			if (userExists) {
				Utility.showMessage("Account Creation Error", "There is such an account!", Pos.TOP_CENTER, 5);
			} else {
				Database.createTeacherAccount(username, password, name, surname);
				Utility.showMessage("Create Account", "Created with User Success", Pos.TOP_CENTER, 5);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	@FXML
	private void onLogin() {

		try {
			Utility.signupStage.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}