package EstruturasDados.listaEncadeada;

public interface interfaceList<E> {

	void addFirst(E value);

	void addLast(E value);

	boolean addAfter(E dado, E crit) throws Exception;

	E peekFirst() throws Exception;

	E peekLast() throws Exception;

	boolean search(E crit) throws Exception;

	E removeFirst() throws Exception;

	E removeLast();

	E remove(E crit) throws Exception;

	boolean isEmpty();

	void show();

	void showReverse();
}
