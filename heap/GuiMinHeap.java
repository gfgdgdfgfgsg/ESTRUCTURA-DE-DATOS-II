import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MinHeapGUI extends JFrame {
    private MinHeap minHeap;
    private JTextArea heapDisplay;
    private JTextField inputField;

    public MinHeapGUI() {
        // Configuración de la ventana principal
        setTitle("MinHeap GUI");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        minHeap = new MinHeap();
        heapDisplay = new JTextArea(10, 30);
        heapDisplay.setEditable(false);
        inputField = new JTextField(30);

        // Crear panel para la entrada
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Inserta una lista de números separados por comas: "));
        inputPanel.add(inputField);

        // Crear botones
        JButton insertButton = new JButton("Insertar Lista");
        JButton clearButton = new JButton("Borrar");
        JButton resetButton = new JButton("Reiniciar");

        insertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                insertList();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearHeap();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetHeap();
            }
        });

        // Crear panel para los botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(insertButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(resetButton);

        // Panel para mostrar el estado del heap
        JScrollPane scrollPane = new JScrollPane(heapDisplay);
        JPanel displayPanel = new JPanel();
        displayPanel.add(scrollPane);

        // Organizar todo en el layout
        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(displayPanel, BorderLayout.SOUTH);
    }

    // Insertar lista en el MinHeap
    private void insertList() {
        String input = inputField.getText();
        String[] inputArray = input.split(",");
        ArrayList<Integer> numbers = new ArrayList<>();

        try {
            // Convertir la entrada en una lista de números
            for (String num : inputArray) {
                numbers.add(Integer.parseInt(num.trim())); // Eliminar espacios y convertir a enteros
            }

            // Construir el MinHeap
            minHeap.buildMinHeap(numbers);

            // Mostrar el estado final del heap
            heapDisplay.setText("MinHeap después de insertar la lista:\n" + minHeap.getHeapState());

        } catch (NumberFormatException e) {
            heapDisplay.setText("Error: Asegúrese de ingresar una lista de números válidos separados por comas.");
        }
    }

    // Borrar el heap
    private void clearHeap() {
        minHeap.clear();
        heapDisplay.setText("Heap vacío. Realice una nueva inserción.");
    }

    // Reiniciar el heap
    private void resetHeap() {
        minHeap = new MinHeap();
        heapDisplay.setText("Heap reiniciado. Realice una nueva inserción.");
    }

    public static void main(String[] args) {
        // Crear y mostrar la GUI
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MinHeapGUI().setVisible(true);
            }
        });
    }
}

class MinHeap {
    private ArrayList<Integer> heap;

    public MinHeap() {
        heap = new ArrayList<>();
    }

    // Insertar un nuevo valor en el heap
    public void insert(int value) {
        heap.add(value); // Agregar el valor al final
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
            heapifyDown(0); // Acomodar el valor hacia abajo
        }
        
        return min;
    }

    // Método para organizar el heap después de insertar un valor
    private void heapifyUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (heap.get(index) >= heap.get(parentIndex)) {
                break; // Si el valor es mayor que el padre, detenerse
            }
            swap(index, parentIndex); // Intercambiar con el padre
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

            if (leftChild < heap.size() && heap.get(leftChild) < heap.get(minIndex)) {
                minIndex = leftChild;
            }
            if (rightChild < heap.size() && heap.get(rightChild) < heap.get(minIndex)) {
                minIndex = rightChild;
            }
            if (minIndex == index) {
                break; // Si el índice mínimo es el mismo, detener
            }

            swap(index, minIndex); // Intercambiar con el hijo más pequeño
            index = minIndex;
        }
    }

    // Método para intercambiar dos elementos en el heap
    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    // Método para imprimir el heap como una cadena (solo para visualizar)
    public String getHeapState() {
        return heap.toString();
    }

    // Método para construir el MinHeap a partir de una lista de enteros
    public void buildMinHeap(ArrayList<Integer> list) {
        for (int value : list) {
            insert(value); // Inserta cada valor en el heap
        }
    }

    // Método para limpiar el heap
    public void clear() {
        heap.clear();
    }
}
