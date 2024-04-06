package EstruturasDados.listaDuplamenteEncadeada;

import EstruturasDados.listaEncadeada.interfaceList;

public class DoubleLinkedList<T> implements interfaceList<T> {
	class Node {
		T data;
		Node next; // endereço do proximo elemento
		Node prev;

		public Node(T data) {
			this.data = data;
			this.next = null;
			this.prev = null;
		}

	}

	private Node head;
	private Node tail;
	private int size;

	public DoubleLinkedList() {
		head = null;
		tail = null;
		size = 0;
	}

	@Override
	public void addFirst(T value) {
		Node novo = new Node(value);
		if (size == 0) {
			head = novo;
			tail = novo;
		} else {
			novo.next = head;
			head.prev = novo;
			head = novo;
		}
		size++;

	}

	@Override
	public void addLast(T value) {
		Node novo = new Node(value);
		if (size == 0) {
			head = novo;
			tail = novo;
		} else {
			novo.prev = tail;
			tail.next = novo;
			tail = novo;
		}
		size++;
	}
	@Override
	public void add(T value) {
		Node novo = new Node(value);
		if (size == 0) {
			head = novo;
			tail = novo;
		} else {
			novo.prev = tail;
			tail.next = novo;
			tail = novo;
		}
		size++;
	}
	@Override
	public boolean addAfter(T dado, T crit) throws Exception {
		Node p = searchNode(crit);
		if (p == null) {
			throw new Exception("Critério inválido");
		} else {
			Node novo = new Node(dado);

			if (p.next == null) {
				addLast(dado);
				return true;
			}
			novo.next = p.next;
			novo.prev = p;
			p.next = novo;
			// p = novo.next; solução alternativa
			// p.prev = novo;
			novo.next.prev = novo;

			size++;
			return true;
		}
	}
	

	@Override
	public T peekFirst() throws Exception {
		if (head == null) {
			throw new Exception("Não há elementos na lista");
		} else {
			return head.data;
		}
	}

	@Override
	public T peekLast() throws Exception {
		if (head == null) {
			throw new Exception("Não há elementos na lista");
		} else {
			return tail.data;
		}
	}

	@Override
	public boolean search(T crit) throws Exception {
		Node p = searchNode(crit);
		if (p == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public T removeFirst() throws Exception {
		Node p = head;
		T dadoRetorno = null;

		if (head == null) {
			System.out.println("Lista Vazia!!!\n");
		} else {
			dadoRetorno = head.data;

			if (head == tail) {
				// Remove unico elemento
				head = null;
				tail = null;
			} else {
				// Remove primeiro elemento, mas há mais outros
				head = head.next;
				p.next = null; // isola elemento removido
				head.prev = null;
			}
			size--;
		}
		return dadoRetorno;
	}

	@Override
	public T removeLast() {
		T dadoRetorno = null;

		if (tail == null) {
			System.out.println("Lista Vazia!!! \n");
			return null;
		} else {
			dadoRetorno = tail.data;

			if (head == tail) {
				// Remove unico elemento;
				head = null;
				tail = null;
			} else {
				// Remove ultimo elemento, mas há mais outros
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

	@Override
	public T remove(T crit) throws Exception {
		Node removido = searchNode(crit);

		if (head == null) {
			System.out.println("Lista Vazia!!! \n");
			return null;
		}

		if (removido == null) {
			throw new Exception("critério não existe");
		} else {
			if (removido == head) {
				return removeFirst();
			} else if (removido == tail) {
				return removeLast();
			} else {
				// Remove meio
				Node anterior = removido.prev;
				Node proximo = removido.next;
				anterior.next = proximo;
				proximo.prev = anterior;
				removido.prev = null;
				removido.next = null;
				size--;
				return removido.data;
			}
		}
	}

	@Override
	public void show() {
		Node p = head;
		while (p != null) {
			System.out.print(p.data + " ");
			p = p.next;
		}
		System.out.println();
	}

	@Override
	public void showReverse() {
		Node p = tail;
		while (p != null) {
			System.out.print(p.data + " ");
			p = p.prev;
		}
		System.out.println();
	}

	private Node searchNode(T crit) {
		Node p = head;

		while (p != null) {
			if (p.data.equals(crit)) {
				return p;
			}
			p = p.next;
		}
		return null;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public T get(int index) throws Exception {
		Node p = head;
		for(int i = 0; i<index; i++) {
			p = p.next;
		}
		return p.data;
	}

	@Override
	public int size() {
		return size;
	}

}
