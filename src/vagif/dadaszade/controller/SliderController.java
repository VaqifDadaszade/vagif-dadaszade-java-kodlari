package vagif.dadaszade.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class SliderController implements Initializable{

	@FXML
	private Slider sliderComp;
	@FXML
	private Circle circle;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
			
	}
	
	@FXML
	private void dragged() {
		System.out.println("Dragged: "+sliderComp.getValue());
	}
	
	@FXML
	private void onScroll() {
		System.out.println("OnScroll: "+ sliderComp.getValue());
	}
	@FXML
	private void onMouseDragged() {
		System.out.println("OnMouseDragged: "+ sliderComp.getValue());
		
		setRadius(sliderComp.getValue());
	}
	@FXML
	private void onMouseClicked() {
		System.out.println("OnMouseClicked: "+ sliderComp.getValue());
	}

	@FXML
	private void setRadius(double radius) {
		circle.setRadius(radius);
		circle.setFill(Color.DARKVIOLET);
	}

}
