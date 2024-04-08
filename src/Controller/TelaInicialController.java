package Controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TelaInicialController {

	@FXML
    private Label lbNomeVazio;

    @FXML
    private TextField nomeJogador;
    
	private Stage stage;
	private Scene scene;
	private Parent root;

	@FXML
	void goToRegras(ActionEvent event) throws IOException {
		Stage stage;
		Scene scene;
		Parent root;
		root = FXMLLoader.load(getClass().getResource("../View/telaRegras.fxml"));
	    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	    scene = new Scene(root);
	    stage.setTitle("Tela Inicial");
	    stage.setScene(scene);
	    stage.show();
	}

	@FXML
	void iniciarJogo(ActionEvent event) throws IOException, Exception{
		if(!nomeJogador.getText().isEmpty()) {
			String nomeJogadorString = nomeJogador.getText();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/telaJogo.fxml"));
			root = loader.load();
			TelaJogoController telaJogoController = loader.getController();
			telaJogoController.initialize(nomeJogadorString); // instancio um objeto do tipo do controller que quero enviar
																// uma informação e chamo o método, depois a tela é mudada
																// normalmente
			// trocar tela
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setTitle("Tela de Jogo");
			stage.setScene(scene);
			stage.show();
		}
		else {
			lbNomeVazio.setText("Nome vazio");
			lbNomeVazio.setStyle("-fx-text-fill: red");
		}
	}

}
