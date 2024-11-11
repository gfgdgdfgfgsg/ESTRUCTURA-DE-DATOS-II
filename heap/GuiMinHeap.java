import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class MinHeap {
    private ArrayList<Integer> heap;

    public MinHeap() {
        heap = new ArrayList<>();
    }

    public void insert(int value) {
        heap.add(value);
        heapifyUp(heap.size() - 1);
    }

    public int getMin() {
        if (heap.size() == 0) throw new IllegalStateException("El heap está vacío");
        return heap.get(0);
    }

    public int removeMin() {
        if (heap.size() == 0) throw new IllegalStateException("El heap está vacío");

        int min = heap.get(0);
        int lastValue = heap.remove(heap.size() - 1);

        if (heap.size() > 0) {
            heap.set(0, lastValue);
            heapifyDown(0);
        }
        
        return min;
    }

    private void heapifyUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (heap.get(index) >= heap.get(parentIndex)) break;
            swap(index, parentIndex);
            index = parentIndex;
        }
    }

    private void heapifyDown(int index) {
        int leftChild, rightChild, minIndex;

        while (index < heap.size() / 2) {
            leftChild = 2 * index + 1;
            rightChild = 2 * index + 2;
            minIndex = index;

            if (leftChild < heap.size() && heap.get(leftChild) < heap.get(minIndex)) {
                minIndex = leftChild;
            }
            if (rightChild < heap.size() && heap.get(rightChild) < heap.get(minIndex)) {
                minIndex = rightChild;
            }
            if (minIndex == index) break;

            swap(index, minIndex);
            index = minIndex;
        }
    }

    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public ArrayList<Integer> getHeap() {
        return heap;
    }

    public void clearHeap() {
        heap.clear();
    }
}

public class MinHeapGUI extends JFrame {
    private MinHeap minHeap = new MinHeap();
    private JTextField inputField;
    private JTextArea outputArea;
    
    public MinHeapGUI() {
        setTitle("MinHeap GUI");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel instructions = new JLabel("<html>Instrucciones:<br>"
            + "1. Ingrese un número en el campo de texto y haga clic en Insertar para añadirlo al heap.<br>"
            + "2. Haga clic en Obtener Mínimo para ver el valor mínimo.<br>"
            + "3. Haga clic en Eliminar Mínimo para eliminar el valor mínimo.<br>"
            + "4. Haga clic en Borrar para limpiar la salida.<br>"
            + "5. Haga clic en Reiniciar para restablecer el heap.<br></html>");
        instructions.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputField = new JTextField(10);
        JButton insertButton = new JButton("Insertar");
        JButton getMinButton = new JButton("Obtener Mínimo");
        JButton removeMinButton = new JButton("Eliminar Mínimo");
        JButton clearButton = new JButton("Borrar");
        JButton resetButton = new JButton("Reiniciar");

        outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Valor:"));
        inputPanel.add(inputField);
        inputPanel.add(insertButton);
        inputPanel.add(getMinButton);
        inputPanel.add(removeMinButton);
        inputPanel.add(clearButton);
        inputPanel.add(resetButton);

        add(instructions, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.CENTER);
        add(new JScrollPane(outputArea), BorderLayout.SOUTH);

        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int value = Integer.parseInt(inputField.getText());
                    minHeap.insert(value);
                    inputField.setText("");
                    outputArea.append("Insertado " + value + " en el heap.\n");
                    displayHeap();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese un número válido.");
                }
            }
        });

        getMinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int min = minHeap.getMin();
                    outputArea.append("El valor mínimo es: " + min + "\n");
                } catch (IllegalStateException ex) {
                    outputArea.append("El heap está vacío.\n");
                }
            }
        });

        removeMinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int min = minHeap.removeMin();
                    outputArea.append("Eliminado el valor mínimo: " + min + "\n");
                    displayHeap();
                } catch (IllegalStateException ex) {
                    outputArea.append("El heap está vacío.\n");
                }
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outputArea.setText("");
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                minHeap.clearHeap();
                outputArea.setText("Heap reiniciado.\n");
                displayHeap();
            }
        });
    }

    private void displayHeap() {
        outputArea.append("Estado actual del heap: " + minHeap.getHeap() + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MinHeapGUI gui = new MinHeapGUI();
                gui.setVisible(true);
            }
        });
    }
}
