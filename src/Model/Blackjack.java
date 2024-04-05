package Model;

public class Blackjack {
	
	private Jogador jogador;
	private Jogador dealer;
	
	public Blackjack(String nomeJogador, String nomeDealer) {
		jogador = new Jogador(nomeJogador);
		dealer = new Jogador(nomeDealer);
	}	
	public Jogador getJogador() {
        return jogador;
    }
	    
    public Jogador getDealer() {
        return dealer;
    }
    public void restartGame() {
    	
    }
}

