package Model;

public class Blackjack {
	
	private Jogador jogador = new Jogador();
	private Jogador dealer = new Jogador();
	
	public Blackjack(String nomeJogador, String nomeDealer) {
		jogador.setNome(nomeJogador);
		dealer.setNome(nomeDealer);
	}	
	public Jogador getJogador() {
        return jogador;
    }
	    
    public Jogador getDealer() {
        return dealer;
    }
}

