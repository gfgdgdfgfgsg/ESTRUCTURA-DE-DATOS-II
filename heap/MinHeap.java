import java.util.ArrayList;
import java.util.Scanner;

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
        
        return min;
    }

    // Método para organizar el heap después de insertar un valor
    private void heapifyUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            System.out.println("Comparando " + heap.get(index) + " con su padre " + heap.get(parentIndex));
            if (heap.get(index) >= heap.get(parentIndex)) {
                break; // Si el valor es mayor que el padre, detenerse
            }
            swap(index, parentIndex); // Intercambiar con el padre
            printHeap(); // Mostrar el estado del heap después de cada intercambio
            index = parentIndex; // Actualizar el índice
        }
    }

    // Método para organizar el heap después de eliminar el mínimo
    private void heapifyDown(int index) {
        int leftChild, rightChild, minIndex;

        while (index < heap.size() / 2) { // Mientras tenga al menos un hijo
            leftChild = 2 * index + 1;
            rightChild = 2 * index + 2;
            minIndex = index;

            // Comparar con el hijo izquierdo
            System.out.println("Comparando " + heap.get(index) + " con su hijo izquierdo " + heap.get(leftChild));
            if (leftChild < heap.size() && heap.get(leftChild) < heap.get(minIndex)) {
                minIndex = leftChild;
            }

            // Comparar con el hijo derecho
            System.out.println("Comparando " + heap.get(index) + " con su hijo derecho " + heap.get(rightChild));
            if (rightChild < heap.size() && heap.get(rightChild) < heap.get(minIndex)) {
                minIndex = rightChild;
            }

            if (minIndex == index) {
                break; // Si el índice mínimo es el mismo, detener
            }

            swap(index, minIndex); // Intercambiar con el hijo más pequeño
            printHeap(); // Mostrar el estado del heap después de cada intercambio
            index = minIndex;
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

    // Método para construir el MinHeap a partir de una lista de enteros
    public void buildMinHeap(ArrayList<Integer> list) {
        for (int value : list) {
            insert(value); // Inserta cada valor en el heap
        }
    }

    // Método principal para leer la entrada del usuario y trabajar con el MinHeap
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Leer la lista de números del usuario
        System.out.println("Ingrese los números para construir el MinHeap (separados por comas): ");
        String input = scanner.nextLine();
        
        // Separar la entrada por comas
        String[] inputArray = input.split(",");
        ArrayList<Integer> numbers = new ArrayList<>();

        // Convertir la entrada en una lista de números
        try {
            for (String num : inputArray) {
                numbers.add(Integer.parseInt(num.trim())); // Eliminar espacios y convertir a enteros
            }

            // Crear el MinHeap
            MinHeap minHeap = new MinHeap();
            minHeap.buildMinHeap(numbers);

            // Mostrar el valor mínimo
            System.out.println("\nValor mínimo (raíz): " + minHeap.getMin());

            // Eliminar el valor mínimo y mostrar los pasos de reorganización
            System.out.println("\nEliminando el mínimo:");
            minHeap.removeMin();
            minHeap.printHeap();

        } catch (NumberFormatException e) {
            System.out.println("Error: Asegúrese de ingresar una lista de números válidos separados por comas.");
        }

        scanner.close();
    }
}
