package vagif.dadaszade.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebHistory.Entry;
import javafx.scene.web.WebView;

public class MyBrowserController implements Initializable{

	@FXML
	private TextField myAddress;
	@FXML
	private WebView myWeb;
	
	private WebEngine myEngine;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		myEngine =myWeb.getEngine();
		myEngine.load("https://www.google.com");
		
	}
	
	@FXML
	private void goSite() {
		String siteLink=myAddress.getText();
		siteLink="https://www."+siteLink;
		myEngine.load(siteLink);
	}
	
	@FXML
	private void showHistory() {
		WebHistory history=myEngine.getHistory();
		ObservableList<Entry> entries=history.getEntries();
		for (Entry entry : entries) {
			System.out.println(entry.getUrl());
		}
	}
	
	@FXML
	private void undo() {
		WebHistory history=myEngine.getHistory();
		history.go(-1);
		String currentLink = history.getEntries().get(history.getCurrentIndex()).getUrl();
		myAddress.setText(currentLink);
	}
	@FXML
	private void rundo() {
		WebHistory history=myEngine.getHistory();
		history.go(1);
		String currentLink = history.getEntries().get(history.getCurrentIndex()).getUrl();
		myAddress.setText(currentLink);
	}
}
