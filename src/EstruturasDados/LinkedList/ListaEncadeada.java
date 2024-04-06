package EstruturasDados.LinkedList;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class ListaEncadeada<E> implements InterfaceListaEncadeada<E> {
	
	class Node
	{
		E data;
		Node next; //endereço do proximo elemento
		Node prev;
		
		public Node(E data) {
			this.data = data;
			this.next = null;
			this.prev = null;
		}
		
	}
	private Node head;
	private Node tail;
	private int size;
	
	public ListaEncadeada() {
		head = null;
		tail = null;
		size = 0;
	}
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean contains(Object o) {
		Node p = head;
		while(p!=null) {
			if(p.data == o) {
				return true;
			}
			p = p.next;
		}
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		Object[] array = new Object[size];
		Node p = head;
		for(int i = 0; i <size; i++) {
			array[i] = p.data;
			p = p.next;
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] a) {
		T[] array = (T[]) new Object[size];
		Node p = head;
		for(int i = 0; i <size; i++) {
			array[i] = (T) p.data;
			p = p.next;
		}
		return array;
	}

	@Override
	public boolean add(E e) {
		Node novo = new Node(e);
		if(size == 0) {
			head = novo;
			tail = novo;
		}
		else {
			novo.prev = tail;
			tail.next = novo;
			tail = novo;
		}
		size++;
		return true;
	}

	@Override
	public boolean remove(Object o) {
		Node removido = searchNodeObject(o);

		if( head == null ) {
	        System.out.println("Lista Vazia!!! \n");
	        return false;
	    }

		if(removido == null)
		{
			return false;
		}
		else {
			if(removido == head) {
				removeFirst();
				return true;
			}
			else if(removido == tail) {
				removeLast();
				return true;
			}
			else {
				//Remove meio
				Node anterior = removido.prev;
				Node proximo = removido.next;;
				anterior.next = proximo;
				proximo.prev = anterior;
				removido.prev = null;
				removido.next = null;
		        size--;
				return true;
			}
		}
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) { //será se precisa desassociar cada elemento?
		head = null;
		tail = null;
		size = 0;
		return true;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public E get(int index){
		Node p = head;
		for(int i = 0; i<index; i++) {
			p = p.next;
		}
		return p.data;
	}

	@Override
	public E set(int index, E element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(int index, E element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public E remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ListIterator<E> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addFirst(E value) {
		Node novo = new Node(value);
		if(size == 0) {
			head = novo;
			tail = novo;
		}
		else {
			novo.next = head;
			head.prev = novo;
			head = novo;
		}
		size++;
		
	}

	@Override
	public void addLast(E value) {
		Node novo = new Node(value);
		if(size == 0) {
			head = novo;
			tail = novo;
		}
		else {
			novo.prev = tail;
			tail.next = novo;
			tail = novo;
		}
		size++;
	}

	@Override
	public E peekFirst() throws Exception {
		if(head==null) {
			throw new Exception("Não há elementos na lista");
		}
		else {
			return head.data;
		}
	}

	@Override
	public E peekLast() throws Exception {
		if(head==null) {
			throw new Exception("Não há elementos na lista");
		}
		else {
			return tail.data;
		}
	}

	@Override
	public E removeFirst() {
		Node p = head;
		E dadoRetorno = null;

		if( head == null ) {
	        System.out.println("Lista Vazia!!!\n");
	    }
		else
		{
			dadoRetorno = head.data;
			
			if (head == tail) 
			{
				//Remove unico elemento
                head = null;
                tail = null;
            } 
			else {
				//Remove primeiro elemento, mas há mais outros
                head = head.next;
                p.next = null; // isola elemento removido
                head.prev = null;
			}
			size--;
		}
		return dadoRetorno;
	}
	

	@Override
	public E removeLast() {
		E dadoRetorno = null;

        if (tail == null) {
        	System.out.println("Lista Vazia!!! \n");
            return null;
        }
        else 
        {
            dadoRetorno = tail.data;
            
            if (head == tail) 
            {
            	//Remove unico elemento;
            	head = null;
            	tail = null;
            } 
            else 
            {
            	//Remove ultimo elemento, mas há mais outros
        		Node p = tail;            
                tail = tail.prev;
                p.prev = null;
                tail.next = null;
            }
            
            // OBS: nao precisa isolar elemento pois o next do tail é sempre null.
            
            size--;
        }

        return dadoRetorno;
	}

	private Node searchNodeObject(Object crit) {
		Node p = head;
		
		while(p != null) {
			if(p.data.equals(crit)) {
				return p;
			}
			p = p.next;
		}
		return null;
	}

}
