package vagif.dadaszade.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ToggleButton;
import javafx.stage.FileChooser;

public class MyComponnetsController implements Initializable {
	@FXML
	private ToggleButton actButton;

	@FXML
	private ColorPicker captainColor;

	@FXML
	private Button fileChoose;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {


	}

	@FXML
	private void activated() {
		if (actButton.isSelected()) {
			System.out.println("The light came on!");
		} else {
			System.out.println("The light came out!");
		}
	}

	@FXML
	private void showColorCode() {
		System.out.println(captainColor.getValue());
	}

	@FXML
	private void fileChooser() {
		FileChooser chooser = new FileChooser();
		File selectedFile =chooser.showOpenDialog(null);
		
		if(selectedFile == null) {
			System.out.println("Select the File!");
		}else {
			System.out.println(selectedFile.getAbsolutePath());
			System.out.println(selectedFile.getName());
			System.out.println(selectedFile.length());
		}
		
	}

}
