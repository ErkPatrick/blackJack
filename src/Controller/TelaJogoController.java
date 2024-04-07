package Controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import EstruturasDados.Fila.Fila;
import EstruturasDados.listaEncadeada.Main;
import Model.Baralho.Carta;
import Model.Blackjack;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

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
    private Label lb1lugar;

    @FXML
    private Label lb2lugar;

    @FXML
    private Label lb3lugar;

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

    @FXML
    private TextArea txaRanking;


	static ImageView[] imgCartasJogador = new ImageView[11];
	static ImageView[] imgCartasDealer = new ImageView[11];

	static Blackjack blackjack;
	static int sequenciaVitorias = 0;
	static String path = "";
	

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

	public void initialize(String nomeJogadorString) throws Exception {
		// inicializo os vetores de imagens para melhor manipulação
		preencherVetorImageViewJogador();
		preencherVetorImageViewDealer();

		// crio o jogo e suas configurações iniciais básicas, como nome e etc
		blackjack = new Blackjack(nomeJogadorString, "Dealer");
		
		//acessará o arquivo toda vez, pois temos que atualizar seus pontos pois blackjack agr aponta para outro espaço da memoria e os jogadores perderam seus pontos
		path = "/blackjack/src/arquivoJogador/pontosJogadores.txt"; //path do arquivo
		try {
			buscarJogadorArquivo(nomeJogadorString);
			exibirRankingJogadores();
		}catch(IOException e) {
			e.printStackTrace();
		}
		

		
		// setando os nomes dos jogadores nos campos de nomes
		nomeDealer.setText(blackjack.getDealer().getNome());
		nomeJogador.setText(blackjack.getJogador().getNome() + "(" + blackjack.getJogador().getPontos() + ")");

		// os jogadores já inicializaram logicamente suas mãos, agora irei exibir na
		// tela o valor acumulado das cartas de suas mãos
		setValorMaoDealer(true);
		setValorMaoJogador();
		
		// exibir cartas iniciais dos jogadores
		exibirCartasJogador(blackjack.getJogador().getMao());
		exibirCartasDealer(blackjack.getDealer().getMao(), true);

	}

	private void exibirRankingJogadores() throws Exception {
		class JogadorArquivo{
			String nome;
			int pontos;
			JogadorArquivo(){
				nome = "";
				pontos = 0;
			}
			public String getNome() {
				return nome;
			}
			public void setNome(String nome) {
				this.nome = nome;
			}
			public int getPontos() {
				return pontos;
			}
			public void setPontos(int pontos) {
				this.pontos = pontos;
			}
		}
		BufferedReader br = new BufferedReader(new FileReader(path));
		Fila<JogadorArquivo> filaRanking = new Fila<JogadorArquivo>(15); //fila onde será inserido em ordem decrescente os jogadores com maiores pontos
		JogadorArquivo[] jogadoresArquivo = new JogadorArquivo[15];
		String linha = "";
		String linhaSplit[];
		int qtdJogadoresArquivo = 0;
		for(int i = 0; i<jogadoresArquivo.length && (linha = br.readLine()) != null; i++) {	
			jogadoresArquivo[i] = new JogadorArquivo();
			linhaSplit = linha.split(":");
			jogadoresArquivo[i].setNome(linhaSplit[0]);
			jogadoresArquivo[i].setPontos(Integer.parseInt(linhaSplit[1]));
			qtdJogadoresArquivo+=1;
		}
		br.close();
		
		//algoritmo de ordenação insertSort
//	    int j;
//		jogadorArquivo eleito;
//	    int size = qtdJogadoresArquivo;
//	    
//	    // LOOP EXTERNO
//	    for (int i = 0; i < size; i++)
//	    {	    	
//	        eleito = jogadoresArquivo[i];
//	        j = i - 1;
//
//	        while ( (j>=0) && (eleito.pontos < jogadoresArquivo[j].pontos) ) 
//	        {
//	            jogadoresArquivo[j+1] = jogadoresArquivo[j];
//	            j--;
//	        }
//	        jogadoresArquivo[j+1] = eleito;
//	    }
		//algoritmo de ordenação bubbleSort otimizado
		boolean ordenado = false;
		for(int i = 0; i < qtdJogadoresArquivo-1 && ordenado == false; i++) {
			ordenado = true;
			for(int j = 0; j<qtdJogadoresArquivo-1; j++) {
				if(jogadoresArquivo[j].getPontos() > jogadoresArquivo[j+1].getPontos()) {
					JogadorArquivo temp = jogadoresArquivo[j];
					jogadoresArquivo[j] = jogadoresArquivo[j+1];
					jogadoresArquivo[j+1] = temp;
					ordenado = false;
				}
			}
		}
	    for(int i = qtdJogadoresArquivo-1; i>=0; i--) {
	    	filaRanking.add(jogadoresArquivo[i]);
	    }
	    for(int i = 0; i<qtdJogadoresArquivo; i++) {
	    	if(i==0) {
	    		lb1lugar.setText("1º)" + filaRanking.peek().getNome() + "(" + filaRanking.peek().getPontos() + ")");
	    	}
	    	else if(i==1) {
	    		lb2lugar.setText("2º)" + filaRanking.peek().getNome() + "(" + filaRanking.peek().getPontos() + ")");
	    	}
	    	else if(i==2) {
	    		lb3lugar.setText("3º)" + filaRanking.peek().getNome() + "(" + filaRanking.peek().getPontos() + ")");
	    	}
	    	else {
	    		txaRanking.setText((i+1) + "º)" + filaRanking.peek().getNome() + "(" + filaRanking.peek().getPontos() + ")\n");
	    	}
		    filaRanking.remove();
	    }
	}
	
	

	private void buscarJogadorArquivo(String nomeJogador) throws IOException {
		BufferedReader br = new BufferedReader( new FileReader(path) );
		boolean encontrouJogador = false;
		String linha = "";
		String[] linhaSplit;
		while((linha = br.readLine()) != null) {
			linhaSplit = linha.split(":");
			if(linhaSplit[0].equals(nomeJogador)) {
				encontrouJogador = true;
				try {
					blackjack.getJogador().setPontos(Integer.parseInt(linhaSplit[1]));
				}catch(NumberFormatException e) {
					e.printStackTrace();
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		br.close();
		if(!encontrouJogador) {
			BufferedWriter bw = new BufferedWriter(new FileWriter(path, true));//escrever no final sem sobrescrever
			bw.append(nomeJogador + ":0\n"); //0, pois sua pontuação ainda é zero
			bw.close();
		}
	}

	void setValorMaoDealer(boolean inicioDePartida) throws Exception {
		if (inicioDePartida) {
			int valorMaoDealer = (blackjack.getDealer().valorMao()) - blackjack.getDealer().valorMao(1);// não pode exibir a soma total da mão do dealer inicialmente																							// do dealer inicialmente														
			somaCartasDealer.setText("" + valorMaoDealer);
		} else {
			somaCartasDealer.setText("" + blackjack.getDealer().valorMao());
		}
	}
	void setValorMaoJogador() throws Exception {
		somaCartasJogador.setText("" + blackjack.getJogador().valorMao());
	}

	private void exibirCartasJogador(List<Carta> mao) {
		for (int i = 0; i < mao.size(); i++) {
			String carta = mao.get(i).toString();
			Image imgCarta = new Image(getClass().getResourceAsStream("../View/img/" + carta + ".png"));
			imgCartasJogador[i].setImage(imgCarta);
		}
	}

	private void exibirCartasDealer(List<Carta> mao, boolean inicioDepartida) {
		for (int i = 0; i < mao.size(); i++) {
			String carta = mao.get(i).toString();
			if (i == 1 && inicioDepartida) {
				Image imgCarta = new Image(getClass().getResourceAsStream("../View/img/ParteDeTrasCarta.jpg"));
				imgCartasDealer[i].setImage(imgCarta);
			} else {
				Image imgCarta = new Image(getClass().getResourceAsStream("../View/img/" + carta + ".png"));
				imgCartasDealer[i].setImage(imgCarta);
			}
		}
	}

	@FXML
	void manterMao(ActionEvent event) throws Exception {
		bntManter.setDisable(true);
		bntPuxarCarta.setDisable(true);
		turnoDealer();
	}

	private void turnoDealer() throws Exception {
		exibirCartasDealer(blackjack.getDealer().getMao(), false);
		setValorMaoDealer(false); // não é inicio de partida
		while (blackjack.getDealer().valorMao() < 17) {
			blackjack.getDealer().puxarCarta();
			exibirCartasDealer(blackjack.getDealer().getMao(), false);
			setValorMaoDealer(false); // não é inicio de partida
		}

		if (blackjack.getDealer().valorMao() > 21) {
			statusEndGame.setText("Vitória");
			statusEndGame.setStyle("-fx-text-fill: gold");
			sequenciaVitorias +=1;
			pontosEndGame.setText("Ganhou " + blackjack.getJogador().pontuacaoVitoria(sequenciaVitorias) + " Pontos");
			pontosEndGame.setStyle("-fx-text-fill: gold");
			endGame();
		} else {
			if (blackjack.getDealer().valorMao() == 21) {
				lbBlackjackDealer.setVisible(true);
			}
			if (blackjack.getDealer().valorMao() > blackjack.getJogador().valorMao()) {
				statusEndGame.setText("Derrota");
				statusEndGame.setStyle("-fx-text-fill: red");
				sequenciaVitorias = 0;
				pontosEndGame.setText("Perdeu " + blackjack.getJogador().pontuacaoDerrota() + " Pontos");
				pontosEndGame.setStyle("-fx-text-fill: red");
				endGame();
			} else if (blackjack.getDealer().valorMao() == blackjack.getJogador().valorMao()) {
				statusEndGame.setText("Empate");
				statusEndGame.setStyle("-fx-text-fill: white");
				endGame();
			} else {
				statusEndGame.setText("Vitória");
				statusEndGame.setStyle("-fx-text-fill: gold");
				sequenciaVitorias +=1;
				pontosEndGame.setText("Ganhou " + blackjack.getJogador().pontuacaoVitoria(sequenciaVitorias) + " Pontos");
				pontosEndGame.setStyle("-fx-text-fill: gold");
				endGame();
			}
		}
	}

	@FXML
	void puxarCarta(ActionEvent event) throws Exception {
		if (blackjack.getJogador().valorMao() < 21) {
			blackjack.getJogador().puxarCarta();
			exibirCartasJogador(blackjack.getJogador().getMao());

			if (blackjack.getJogador().valorMao() == 21) {
				manterMao(event);
				lbBlackjackJogador.setVisible(true);
			} else if (blackjack.getJogador().valorMao() > 21) {
				exibirCartasDealer(blackjack.getDealer().getMao(), false);
				statusEndGame.setText("Derrota");
				statusEndGame.setStyle("-fx-text-fill: red");
				sequenciaVitorias=0;
				pontosEndGame.setText("Perdeu " + blackjack.getJogador().pontuacaoDerrota() + " Pontos");
				pontosEndGame.setStyle("-fx-text-fill: red");
				endGame();
			}
		}

		setValorMaoJogador();
	}

	void endGame() {
		bntManter.setDisable(true);
		bntPuxarCarta.setDisable(true);
		//atualizo a pontuação na tela
		nomeJogador.setText(blackjack.getJogador().getNome() + "(" + blackjack.getJogador().getPontos() + ")");
		//atualizo a pontuação no arquivo
		try {
			atualizarPontuacaoArquivo(blackjack.getJogador().getNome(), blackjack.getJogador().getPontos());
			exibirRankingJogadores();
		} catch (IOException e) {
			System.out.println("Erro ao atualizar pontuação no arquivo");
			e.printStackTrace();
		}catch(Exception e) {
			System.out.println("Erro generalizado ao atualizar pontuação no arquivo");
			e.printStackTrace();
		}
	}

	
	private void atualizarPontuacaoArquivo(String nomeJogador,int pontos) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(path));
		br.mark(100000);
		String linha = "";
		int numJogadores = 0;
		while((linha = br.readLine()) != null) {
			numJogadores+=1;
		}
		String[][]linhaSplit = new String[numJogadores][2];
		while((linha = br.readLine()) != null) {
			numJogadores+=1;
		}
		br.reset();;
		for(int i = 0; i<numJogadores; i++) {
			if((linha = br.readLine()) != null){
				linhaSplit[i] = linha.split(":");
				if(linhaSplit[i][0].equals(nomeJogador)) {
					linhaSplit[i][1] = "" + pontos;
				}
			}
		}
		br.close();
		BufferedWriter bw = new BufferedWriter(new FileWriter(path));
		for(int i = 0; i<numJogadores; i++) {
			bw.append(linhaSplit[i][0] + ":" + linhaSplit[i][1] + "\n");
		}
		bw.close();
	}

	@FXML
	void restart(ActionEvent event) throws Exception {
		for (int i = 0; i < imgCartasDealer.length; i++) {
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
	@FXML
    void voltarTelaInicial(MouseEvent event) throws IOException {
    	
    }

}
