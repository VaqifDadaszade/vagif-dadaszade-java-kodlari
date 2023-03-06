package vagif.dadaszade;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginController {

	@FXML
	private TextField usernameText;

	@FXML
	private PasswordField passwordText;
	
	public static String loginUsername;

	@FXML
	private void onLogin() {

		try {
			String username=usernameText.getText();
			String password=passwordText.getText();
			if (login(username,password)) {
				LoginController.loginUsername=username;
				Stage s = new Stage();
				s.initModality(Modality.APPLICATION_MODAL);
				s.setTitle("Students");
				AnchorPane a = FXMLLoader.load(getClass().getResource("Students2Table.fxml"));
				Scene scene = new Scene(a);
				s.setScene(scene);
				s.show();
			} else {
				JOptionPane.showMessageDialog(null, "You Enter the False Information !");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private boolean login(String username, String password) {
		boolean b = false;
		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_FX", "vagif", "2012");
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(
					"select * from teachers where username='" + username +"' and password='"+password+"'");

			if (rs.next()) {
				b = true;
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
		return b;
	}

}
