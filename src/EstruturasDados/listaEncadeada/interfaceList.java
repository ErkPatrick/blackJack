package EstruturasDados.listaEncadeada;

public interface interfaceList<E> {

	void addFirst(E value);
	
	void add(E value);

	void addLast(E value);

	boolean addAfter(E dado, E crit) throws Exception;

	E peekFirst() throws Exception;

	E peekLast() throws Exception;
	
	E get(int index) throws Exception;

	boolean search(E crit) throws Exception;

	E removeFirst() throws Exception;

	E removeLast();

	E remove(E crit) throws Exception;
	
	int size();

	boolean isEmpty();

	void show();

	void showReverse();

	
}
