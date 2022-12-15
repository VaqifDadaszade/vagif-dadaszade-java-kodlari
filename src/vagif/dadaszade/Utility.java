package vagif.dadaszade;

import org.controlsfx.control.Notifications;

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
}
