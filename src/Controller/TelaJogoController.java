package Controller;

import java.util.List;
import java.time.*;
import Model.Baralho.Carta;
import Model.Blackjack;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TelaJogoController {

	@FXML
    private Button bntManter;

    @FXML
    private Button bntPuxarCarta;

    @FXML
    private ImageView carta10Dealer;

    @FXML
    private ImageView carta10Jogador;

    @FXML
    private ImageView carta11Dealer;

    @FXML
    private ImageView carta11Jogador;

    @FXML
    private ImageView carta1Dealer;

    @FXML
    private ImageView carta1Jogador;

    @FXML
    private ImageView carta2Dealer;

    @FXML
    private ImageView carta2Jogador;

    @FXML
    private ImageView carta3Dealer;

    @FXML
    private ImageView carta3Jogador;

    @FXML
    private ImageView carta4Dealer;

    @FXML
    private ImageView carta4Jogador;

    @FXML
    private ImageView carta5Dealer;

    @FXML
    private ImageView carta5Jogador;

    @FXML
    private ImageView carta6Dealer;

    @FXML
    private ImageView carta6Jogador;

    @FXML
    private ImageView carta7Dealer;

    @FXML
    private ImageView carta7Jogador;

    @FXML
    private ImageView carta8Dealer;

    @FXML
    private ImageView carta8Jogador;

    @FXML
    private ImageView carta9Dealer;

    @FXML
    private ImageView carta9Jogador;

    @FXML
    private Label lbBlackjackDealer;

    @FXML
    private Label lbBlackjackJogador;

    @FXML
    private Label nomeDealer;

    @FXML
    private Label nomeJogador;

    @FXML
    private Label pontosEndGame;

    @FXML
    private Label somaCartasDealer;

    @FXML
    private Label somaCartasJogador;

    @FXML
    private Label statusEndGame;
    
    static ImageView[] imgCartasJogador = new ImageView[11];
    static ImageView[] imgCartasDealer = new ImageView[11];
    
    static Blackjack blackjack;
    
    private void preencherVetorImageViewJogador() {
		imgCartasJogador[0] = carta1Jogador;
		imgCartasJogador[1] = carta2Jogador;
		imgCartasJogador[2] = carta3Jogador;
		imgCartasJogador[3] = carta4Jogador;
		imgCartasJogador[4] = carta5Jogador;
		imgCartasJogador[5] = carta6Jogador;
		imgCartasJogador[6] = carta7Jogador;
		imgCartasJogador[7] = carta8Jogador;
		imgCartasJogador[8] = carta9Jogador;
		imgCartasJogador[9] = carta10Jogador;
		imgCartasJogador[10] = carta11Jogador;
	}
	private void preencherVetorImageViewDealer() {
		imgCartasDealer[0] = carta1Dealer;
		imgCartasDealer[1] = carta2Dealer;
		imgCartasDealer[2] = carta3Dealer;
		imgCartasDealer[3] = carta4Dealer;
		imgCartasDealer[4] = carta5Dealer;
		imgCartasDealer[5] = carta6Dealer;
		imgCartasDealer[6] = carta7Dealer;
		imgCartasDealer[7] = carta8Dealer;
		imgCartasDealer[8] = carta9Dealer;
		imgCartasDealer[9] = carta10Dealer;
		imgCartasDealer[10] = carta11Dealer;
	}
	
    public void initialize(String nomeJogadorString) {
    	//inicializo os vetores de imagens para melhor manipulação
    	preencherVetorImageViewJogador();
    	preencherVetorImageViewDealer();
    	
    	//crio o jogo e suas configurações iniciais básicas, como nome e etc
    	blackjack = new Blackjack(nomeJogadorString,"Dealer");
    	//setando os nomes dos jogadores nos campos de nomes
    	nomeDealer.setText(blackjack.getDealer().getNome());
    	nomeJogador.setText(blackjack.getJogador().getNome());
    	
    	//os jogadores já inicializaram logicamente suas mãos, agora irei exibir na tela o valor acumulado das cartas de suas mãos
    	setValorMao(true);
    	
    	
    	//exibir cartas iniciais dos jogadores
    	exibirCartasJogador(blackjack.getJogador().getMao());
    	exibirCartasDealer(blackjack.getDealer().getMao(), true);
    	

    	
    	
    }
    void setValorMao(boolean inicioDePartida) {
    	if(inicioDePartida) {
	    	int valorMaoDealer = (blackjack.getDealer().valorMao()) - blackjack.getDealer().valorMao(1);//não pode exibir a soma total da mão do dealer inicialmente
	    	somaCartasDealer.setText("" +  valorMaoDealer);
	    	somaCartasJogador.setText("" + blackjack.getJogador().valorMao());
    	}
    	else {
    		somaCartasDealer.setText("" +  blackjack.getDealer().valorMao());
	    	somaCartasJogador.setText("" + blackjack.getJogador().valorMao());
    	}
    }
    
	private void exibirCartasJogador(List<Carta>mao) {
		for(int i = 0; i<mao.size(); i++) {
			String carta = mao.get(i).toString();
			Image imgCarta = new Image(getClass().getResourceAsStream("../View/img/" + carta + ".png"));
			imgCartasJogador[i].setImage(imgCarta);
		}
	}
	private void exibirCartasDealer(List<Carta> mao, boolean inicioDepartida) {
		for(int i = 0; i<mao.size(); i++) {
			String carta = mao.get(i).toString();
			if(i == 1 && inicioDepartida) {
				Image imgCarta = new Image(getClass().getResourceAsStream("../View/img/ParteDeTrasCarta.jpg"));
				imgCartasDealer[i].setImage(imgCarta);
			}
			else {
				Image imgCarta = new Image(getClass().getResourceAsStream("../View/img/" + carta + ".png"));
				imgCartasDealer[i].setImage(imgCarta);
			}
		}
	}
	@FXML
    void manterMao(ActionEvent event) {
    	bntManter.setDisable(true);
    	bntPuxarCarta.setDisable(true);
    	turnoDealer();
    }

    private void turnoDealer(){
    	exibirCartasDealer(blackjack.getDealer().getMao(), false);
    	setValorMao(false); //não é inicio de partida
		while(blackjack.getDealer().valorMao()<17) {
			blackjack.getDealer().puxarCarta();
			exibirCartasDealer(blackjack.getDealer().getMao(), false);
			setValorMao(false); //não é inicio de partida
		}
		
		if(blackjack.getDealer().valorMao() > 21) {
			statusEndGame.setText("Vitória");
			statusEndGame.setStyle("-fx-text-fill: gold");
			endGame();
		}
		else {
			if(blackjack.getDealer().valorMao() == 21) {
				lbBlackjackDealer.setVisible(true);
			}
			if(blackjack.getDealer().valorMao() > blackjack.getJogador().valorMao()) {
				statusEndGame.setText("Derrota");
				statusEndGame.setStyle("-fx-text-fill: red");
				endGame();
			}
			else if(blackjack.getDealer().valorMao() == blackjack.getJogador().valorMao()) {
				statusEndGame.setText("Empate");
				statusEndGame.setStyle("-fx-text-fill: white");
				endGame();
			}
			else {
				statusEndGame.setText("Vitória");
				statusEndGame.setStyle("-fx-text-fill: gold");
				endGame();
			}
		}
	}
	@FXML
    void puxarCarta(ActionEvent event) {
		if(blackjack.getJogador().valorMao()<21) {
    		blackjack.getJogador().puxarCarta();
    		exibirCartasJogador(blackjack.getJogador().getMao());
    		
    		if(blackjack.getJogador().valorMao()==21) {
    			manterMao(event);
    			lbBlackjackJogador.setVisible(true);
    		}
    		else if(blackjack.getJogador().valorMao()>21) {
    			statusEndGame.setText("Derrota");
    			statusEndGame.setStyle("-fx-text-fill: red");
    			endGame();
    		}
		}
		
		setValorMao(false); //não é inicio de partida
    }
	void endGame() {
		bntManter.setDisable(true);
		bntPuxarCarta.setDisable(true);
	}
	@FXML
    void restart(ActionEvent event) {
		for(int i = 0; i < imgCartasDealer.length; i++) {
			imgCartasDealer[i].setImage(null);
			imgCartasJogador[i].setImage(null);
		}
		bntManter.setDisable(false);
		bntPuxarCarta.setDisable(false);
		statusEndGame.setText(null);
		pontosEndGame.setText(null);
		lbBlackjackDealer.setVisible(false);
		lbBlackjackJogador.setVisible(false);
		initialize(blackjack.getJogador().getNome());
    }
	
}
