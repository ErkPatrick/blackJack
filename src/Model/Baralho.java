package Model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Baralho {
	public class Carta{
		private String valor;
		private String naipe;
		public Carta(String valor, String naipe) {
			this.valor = valor;
			this.naipe = naipe;
		}
		public String toString() {
			return valor + "-" + naipe;
		}
	}
	Map<Carta, Integer> mapCarta = new HashMap<Carta, Integer>();
	Stack<Carta> pilhaBaralho = new Stack<Carta>(); //não declaro como static pois todo baralho terá sua pilha
	
	public Baralho() {
		inicializarBaralho();
		embaralharCartas();
	}
	
	public void inicializarBaralho() {
        String[] naipes = {"Paus", "Ouros", "Copas", "Espadas"};
        String[] valores = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        
        for (String naipe : naipes) {
            for (String valor : valores) {
            	Carta carta = new Carta(valor, naipe);
                pilhaBaralho.push(carta);
                switch (valor) {
                	case "2":
                		mapCarta.put(carta, 2);
                		break;
                	 case "3":
                         mapCarta.put(carta, 3);
                         break;
                     case "4":
                         mapCarta.put(carta, 4);
                         break;
                     case "5":
                         mapCarta.put(carta, 5);
                         break;
                     case "6":
                         mapCarta.put(carta, 6);
                         break;
                     case "7":
                         mapCarta.put(carta, 7);
                         break;
                     case "8":
                         mapCarta.put(carta, 8);
                         break;
                     case "9":
                         mapCarta.put(carta, 9);
                         break;
                     case "10":
                         mapCarta.put(carta, 10);
                         break;
                     case "J":
                         mapCarta.put(carta, 10);
                         break;
                     case "Q":
                         mapCarta.put(carta, 10);
                         break;
                     case "K":
                         mapCarta.put(carta, 10);
                         break;
                     case "A":
                         mapCarta.put(carta, 1);
                         break;
                }
            }
        }
    }
    public void embaralharCartas() {
        Collections.shuffle(pilhaBaralho);
    }
}
