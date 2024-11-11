import java.util.ArrayList;

class MinHeap {
    private ArrayList<Integer> heap;

    public MinHeap() {
        heap = new ArrayList<>();
    }

    // Insertar un nuevo valor en el heap
    public void insert(int value) {
        heap.add(value); // Agregar el valor al final
        System.out.println("Insertando " + value);
        heapifyUp(heap.size() - 1); // Acomodar el valor hacia arriba
        printHeap();
    }

    // Obtener el valor mínimo (raíz)
    public int getMin() {
        if (heap.size() == 0) {
            throw new IllegalStateException("El heap está vacío");
        }
        return heap.get(0);
    }

    // Eliminar el valor mínimo (raíz) y reorganizar el heap
    public int removeMin() {
        if (heap.size() == 0) {
            throw new IllegalStateException("El heap está vacío");
        }

        int min = heap.get(0); // Obtener el valor mínimo
        int lastValue = heap.remove(heap.size() - 1); // Remover el último valor

        if (heap.size() > 0) {
            heap.set(0, lastValue); // Colocar el último valor en la raíz
            System.out.println("Eliminando el mínimo " + min + " y reorganizando el heap");
            heapifyDown(0); // Acomodar el valor hacia abajo
        } else {
            System.out.println("Eliminando el mínimo " + min);
        }

        printHeap();
        return min;
    }

    // Método para organizar el heap después de insertar un valor
    private void heapifyUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (heap.get(index) >= heap.get(parentIndex)) {
                break; // Si el valor es mayor que el padre, detenerse
            }
            System.out.println("Intercambiando " + heap.get(index) + " con el padre " + heap.get(parentIndex));
            swap(index, parentIndex); // Intercambiar con el padre
            index = parentIndex; // Actualizar el índice
            printHeap();
        }
    }

    // Método para organizar el heap después de eliminar el mínimo
    private void heapifyDown(int index) {
        int leftChild, rightChild, minIndex;

        while (index < heap.size() / 2) { // Mientras tenga al menos un hijo
            leftChild = 2 * index + 1;
            rightChild = 2 * index + 2;
            minIndex = index;

            if (leftChild < heap.size() && heap.get(leftChild) < heap.get(minIndex)) {
                minIndex = leftChild;
            }
            if (rightChild < heap.size() && heap.get(rightChild) < heap.get(minIndex)) {
                minIndex = rightChild;
            }
            if (minIndex == index) {
                break; // Si el índice mínimo es el mismo, detener
            }

            System.out.println("Intercambiando " + heap.get(index) + " con el hijo menor " + heap.get(minIndex));
            swap(index, minIndex); // Intercambiar con el hijo más pequeño
            index = minIndex;
            printHeap();
        }
    }

    // Método para intercambiar dos elementos en el heap
    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    // Método para imprimir el heap (solo para visualizar)
    public void printHeap() {
        System.out.println("Estado actual del heap: " + heap);
    }

    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap();
        
        // Insertar elementos en el heap
        minHeap.insert(7);
        minHeap.insert(3);
        minHeap.insert(10);
        minHeap.insert(2);
        minHeap.insert(8);
        minHeap.insert(5);
        
        // Obtener el valor mínimo
        System.out.println("Valor mínimo (raíz): " + minHeap.getMin());
        
        // Eliminar el valor mínimo
        System.out.println("Eliminando el mínimo: " + minHeap.removeMin());
        
        // Imprimir el heap después de eliminar el mínimo
        minHeap.printHeap();
    }
}
