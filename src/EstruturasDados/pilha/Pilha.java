package EstruturasDados.pilha;

public class Pilha<T> implements InterfacePilha<T> {

	T[] vetor;
	int topo;
	int size;

	@SuppressWarnings("unchecked")
	public Pilha(int size) {
		this.size = size;
		vetor = (T[]) new Object[size];
		topo = -1;
	}

	@Override
	public void push(T number) throws Exception {
		if (isFull()) {
			throw new Exception("A pilha está cheia");
		} else {
			topo += 1;
			vetor[topo] = number;
		}

	}

	@Override
	public T pop() throws Exception {
		if (isEmpty()) {
			throw new Exception("A pilha está vazia");
		} else {
			T element = vetor[topo];
			topo -= 1; // apenas altero a variavel topo, aquele espaço do vetor fica como lixo
			return element;
		}
	}

	@Override
	public T peek() throws Exception {
		if (isEmpty()) {
			throw new Exception("A pilha não contém elementos");
		} else {
			return vetor[topo];
		}
	}

	@Override
	public boolean isEmpty() {
		return topo == -1;
	}

	@Override
	public boolean isFull() {
		return topo == size - 1;
	}

	@Override
	public void show() {
		System.out.print("[ ");
		for (int i = 0; i <= topo; i++) {
			System.out.print(vetor[i] + " ");
		}
		System.out.print("]");
	}

	public int getTopo() {
		return topo;
	}

	public int getSize() {
		return size;
	}
}
