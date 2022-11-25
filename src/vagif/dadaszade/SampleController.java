package vagif.dadaszade;



import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SampleController {
	@FXML
	private TextField studentNameText;
	
	@FXML
	private Label studentNameLable;
	
	@FXML
	private void saveStudent() {
		studentNameLable.setText("Salam Adiniz: "+studentNameText.getText());
	}
}
