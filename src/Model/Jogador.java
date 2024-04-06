package Model;

import java.util.List;
import EstruturasDados.LinkedList.ListaEncadeada;
import Model.Baralho.Carta;

public class Jogador {
	private String nome;
	ListaEncadeada<Carta> mao = new ListaEncadeada<Carta>();
	private int somatorioMao = 0;
	private int pontos;
	static Baralho baralho;

	public Jogador(String nomeJogador) throws Exception {
		baralho = new Baralho();
		setNome(nomeJogador);
		inicializarMao();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if (nome != null && !nome.isEmpty())
			this.nome = nome;
	}
	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos += pontos;
	}

	public List<Carta> getMao() {
		return mao;
	}

	public int valorMao() {
		return somatorioMao;
	}

	public int valorMao(int i) throws Exception {
		return baralho.mapCarta.get(mao.get(i));
	}

	public void inicializarMao() throws Exception {
		mao.add(baralho.pilhaBaralho.pop());
		mao.add(baralho.pilhaBaralho.pop());
		somatorioMao = baralho.mapCarta.get(mao.get(0)) + baralho.mapCarta.get(mao.get(1));
	}

	public void puxarCarta() throws Exception {
		mao.add(baralho.pilhaBaralho.pop());
		somatorioMao += baralho.mapCarta.get(mao.get(mao.size() - 1));
	}
	public int pontuacaoVitoria(int qtdVitorias) {
		setPontos(qtdVitorias*100);
		return qtdVitorias*100;
	}
	public int pontuacaoDerrota(int qtdDerrotas) {
		setPontos(qtdDerrotas*-100);
		return qtdDerrotas*-100;
	}
}
