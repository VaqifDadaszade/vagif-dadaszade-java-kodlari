package vagif.dadaszade.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ToggleButton;

public class ToggleButtonController implements Initializable{
	@FXML
	private ToggleButton actButton;
	
	@FXML
	private ColorPicker captainColor;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		
	}
	
	@FXML
	private void activated() {
		if(actButton.isSelected()) {
			System.out.println("The light came on!");
		} else {
			System.out.println("The light came out!");
		}
	}
	
	@FXML
	private void showColorCode() {
		System.out.println(captainColor.getValue());
	}

}
