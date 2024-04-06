package EstruturasDados.LinkedList;

import java.util.List;

public interface InterfaceListaEncadeada<E> extends List<E>{
	void addFirst(E value);
	void addLast(E value);
	
	E peekFirst() throws Exception;
	E peekLast() throws Exception;
	
	E removeFirst();
	E removeLast();
}
