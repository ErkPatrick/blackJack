package EstruturasDados.Fila;

public class Fila<T> implements InterfaceFila<T> {
	private int sizeMax;
	private Object[] array;
	private int first;
	private int last;

	public Fila(int sizeMax) {
		this.first = -1;
		this.last = -1;
		this.sizeMax = sizeMax;
		this.array = new Object[sizeMax];
	}

	public void add(T valor) throws Exception {
		int lastTemp = (last + 1) % sizeMax;

		if (lastTemp == first) {
			throw new Exception("\nERRO: fila cheia[" + valor + "]!!!\n");
		}

		last = lastTemp;
		array[last] = valor;

		// inserindo o primeiro elemento
		if (first == -1) {
			first = 0;
		}
	}

	@SuppressWarnings("unchecked")
	public T remove() throws Exception {
		if (first == -1) {
			throw new Exception("\nERRO: fila vazia!!!\n");
		}

		T retorno = (T) array[first];

		if (first == last) {
			// removendo o unico elemento
			first = -1;
			last = -1;
		} else {
			first = (first + 1) % sizeMax;
		}

		return retorno;
	}

	@SuppressWarnings("unchecked")
	public T peek() throws Exception {
		T retorno;

		if (first == -1) {
			throw new Exception("\nERRO: fila vazia!!!\n");
		}

		retorno = (T) array[first];

		return retorno;
	}

	public boolean isFull() {
		int lastTemp = (last + 1) % sizeMax;

		if (lastTemp == first) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isEmpty() {
		if (first == -1) {
			return true;
		} else {
			return false;
		}
	}

	public void show() {
		int i = first;

		if (first == -1) {
			return;
		}

		while (i != last) {
			System.out.println("posicao " + i + " = " + array[i] + "\n");

			i = (i + 1) % sizeMax;
		}
		System.out.println("posicao " + i + " = " + array[i] + "\n");

		System.out.println("inicio = " + first + "  fim = " + last + "\n");
	}

}