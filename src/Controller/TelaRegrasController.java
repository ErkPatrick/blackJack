package Controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class TelaRegrasController {
	
	@FXML
	private TextArea txaRegras;
	
    @FXML
    void voltarTelaInicial(MouseEvent event) throws IOException {
    	Stage stage;
		Scene scene;
		Parent root;
		root = FXMLLoader.load(getClass().getResource("../View/telaInicial.fxml"));
	    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    scene = new Scene(root);
	    stage.setTitle("Tela Inicial");
	    stage.setScene(scene);
	    stage.show();
    }

}
