package vagif.dadaszade.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;

public class ProgressBarController implements Initializable {

	@FXML
	private ProgressBar myProgressBar;

	@FXML
	private void begin() throws InterruptedException {

		Runnable process = new Runnable() {

			@Override
			public void run() {
				double d = 0;

				for (int i = 1; i <= 10; i++) {
					waiting(500);
					d += 0.1;
					myProgressBar.setProgress(d);
				}

			}
		};
		Thread myThread=new Thread(process);
		myThread.start();

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}
	
	static void waiting(int waTi) {
		try {
			Thread.sleep(waTi);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
