package dad.calculadora.css.ui;

import java.io.IOException;

import dad.calculadora.css.Calculadora;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class CalcController {
	
	// model
	
	private Calculadora calculadora = new Calculadora();
	
	// view
	
	@FXML 
	private GridPane view;
	
	@FXML
	private TextField screenText;

	public CalcController() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CalcView.fxml"));
        loader.setController(this);
        loader.load();
	}
	
	@FXML
	private void initialize() {
		screenText.textProperty().bind(calculadora.screenProperty());
		
		MenuItem clasico = new MenuItem("Clásico");
		clasico.setOnAction(e -> {
			view.getStylesheets().clear();
			view.getStylesheets().add("/css/clasico.css");
		});

		MenuItem moderno = new MenuItem("Moderno");
		moderno.setOnAction(e -> {
			view.getStylesheets().clear();
			view.getStylesheets().add("/css/moderno.css");
		});

		ContextMenu menu = new ContextMenu(clasico, moderno);

		view.setOnContextMenuRequested(e -> {
			menu.show(view, e.getScreenX(), e.getScreenY());
		});
	}
	
	@FXML
	private void onOperationButtonHandle(ActionEvent e) {
		String texto = ((Button)e.getSource()).getText();
		if (texto.equals("CE")) {
			calculadora.cleanEverything();
		} else if (texto.equals("C")) {
			calculadora.clean();
		} else if (texto.equals("+/-")) {
			calculadora.negate();
		} else {
			calculadora.operate(texto.charAt(0));
		}
	}
	
	@FXML
	private void onCommaButtonHandle(ActionEvent e) {
		calculadora.insertComma();
	}
	
	@FXML
	private void onNumberButtonHandle(ActionEvent e) {
		String texto = ((Button)e.getSource()).getText();
		calculadora.insert(texto.charAt(0));
	}
	
	public GridPane getView() {
		return view;
	}

}
