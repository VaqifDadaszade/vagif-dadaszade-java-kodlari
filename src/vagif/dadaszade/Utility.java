package vagif.dadaszade;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import org.controlsfx.control.Notifications;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.util.Duration;

public class Utility {
	public static void showMessage(String title,String message,Pos p,int duration) {
		Notifications.create()
		.title(title)
		.text(message)
		.position(p)
		.hideAfter(Duration.seconds(duration))
		.showInformation();
	}
	
	public static void insertUpdateAndDel(String query) {
		Connection conn =null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_FX", "vagif", "2012");
			Statement st = conn.createStatement();
			st.executeUpdate(query);
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
