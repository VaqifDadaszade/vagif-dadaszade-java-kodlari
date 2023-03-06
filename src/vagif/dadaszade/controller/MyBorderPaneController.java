package vagif.dadaszade.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class MyBorderPaneController implements Initializable {
	@FXML
	private WebView myWeb;

	private WebEngine myEngine;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		myEngine = myWeb.getEngine();
		myEngine.load("https://www.google.com");

	}
}
