package EstruturasDados.Fila;

import EstruturasDados.listaEncadeada.SinglyLinkedList;

public class FilaList <T> implements InterfaceFila<T>{
	SinglyLinkedList<T> fila = new SinglyLinkedList<T>();

	@Override
	public void add(T element) throws Exception {
		fila.addLast(element);
		
	}

	@Override
	public T remove() throws Exception {
		return fila.removeFirst();
	}

	@Override
	public T peek() throws Exception {
		return fila.peekFirst();
	}

	@Override
	public boolean isEmpty() {
		return fila.isEmpty();
	}

	@Override
	public boolean isFull() {
		return false;
	}

	@Override
	public void show() {
		fila.show();
	}
}
