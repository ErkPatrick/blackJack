package EstruturasDados.listaEncadeada;

public class SinglyLinkedList<T> implements interfaceList<T> {

	class Node {
		T data;
		Node next; // endereço do proximo elemento

		public Node(T data) {
			this.data = data;
			this.next = null;
		}

	}

	private Node head;
	private Node tail;
	private int size;

	public SinglyLinkedList() {
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
			tail.next = novo;
			tail = novo;
		}
		size++;
	}

	@Override
	public boolean addAfter(T dado, T crit) {
		Node p = searchNode(crit);
		if (p == null) {
			System.out.println("Critério inválido");
			return false;
		} else {
			Node novo = new Node(dado);

			if (p.next == null) {
				addLast(dado);
				return true;
			}
			novo.next = p.next;
			p.next = novo;

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
			throw new Exception("Lista Vazia!!! \n");
		} else {
			dadoRetorno = head.data;

			if (head == tail) {
				head = null;
				tail = null;
			} else {
				head = head.next;
				p.next = null; // isola elemento removido
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
				head = null;
				tail = null;
			} else {
				Node p = head;
				// Procuro o penultimo elemento
				while (p.next != tail) {
					p = p.next;
				}

				tail = p;
				tail.next = null;
			}

			// OBS: nao precisa isolar elemento pois o next do tail é sempre null.

			size--;
		}

		return dadoRetorno;
	}

	@Override
	public T remove(T crit) throws Exception {
		Node anterior = null;
		Node removido = null;

		if (head == null) {
			System.out.println("Lista Vazia!!! \n");
			return null;
		}

		anterior = searchBefore(crit); // null: ID não existe OU ID está no 1o elemento

		if (anterior == null) {
			if (head.data.equals(crit) == false) {
				return null;
			} else {
				// Caso: elemento a ser removido esta no inicio
				return removeFirst();
			}
		} else {
			removido = anterior.next;

			if (removido == tail) {
				return removeLast();
			} else {
				anterior.next = removido.next; // se desliga do elemento removido
				removido.next = null; // isola elemento removido
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
	}

	@Override
	public void showReverse() {
		// TODO Auto-generated method stub

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

	private Node searchBefore(T criterio) {
		Node p = head; // ponteiro temporario
		Node anterior = null; // ponteiro anterior

		while (p != null) {
			anterior = p;
			p = p.next;

			if (p != null && p.data.equals(criterio)) {
				return anterior;
			}
		}

		return null;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}
}
