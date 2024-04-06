package EstruturasDados.pilha;

import EstruturasDados.listaEncadeada.SinglyLinkedList;

public class PilhaList<T> implements InterfacePilhaList<T> {

	SinglyLinkedList<T> pilha = new SinglyLinkedList<T>();

	@Override
	public void push(T number) throws Exception {
		pilha.addFirst(number);
	}

	@Override
	public T pop() throws Exception {
		return pilha.removeFirst();
	}

	@Override
	public T peek() throws Exception {
		return pilha.peekFirst();
	}

	@Override
	public void show() {
		pilha.show();
	}

	@Override
	public boolean isEmpty() {
		return pilha.isEmpty();
	}

	@Override
	public boolean isFull() {
		return false;
	}

}
