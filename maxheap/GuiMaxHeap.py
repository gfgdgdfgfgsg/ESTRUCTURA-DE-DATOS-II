import tkinter as tk
from tkinter import messagebox

class MaxHeap:
    def __init__(self):
        self.heap = []

    def insert(self, value):
        self._log(f"Insertando el valor {value} al Max-Heap")
        self.heap.append(value)
        self._heapify_up(len(self.heap) - 1)
        self._log(f"Max-Heap después de insertar {value}: {self.heap}")

    def remove_max(self):
        if len(self.heap) == 0:
            raise IndexError("El heap está vacío")
        
        max_value = self.heap[0]
        last_value = self.heap.pop()
        self._log(f"\nEliminando el valor máximo {max_value}")

        if len(self.heap) > 0:
            self.heap[0] = last_value
            self._heapify_down(0)
            self._log(f"Max-Heap después de eliminar el máximo: {self.heap}")
        else:
            self._log(f"El heap está vacío después de eliminar el máximo.")
        
        return max_value

    def _heapify_up(self, index):
        while index > 0:
            parent_index = (index - 1) // 2
            if self.heap[index] <= self.heap[parent_index]:
                break
            self._log(f"Intercambiando {self.heap[index]} con {self.heap[parent_index]}")
            self.heap[index], self.heap[parent_index] = self.heap[parent_index], self.heap[index]
            index = parent_index
            self._log(f"Max-Heap después de intercambiar: {self.heap}")

    def _heapify_down(self, index):
        while index < len(self.heap) // 2:
            left_child_index = 2 * index + 1
            right_child_index = 2 * index + 2
            largest = index

            if left_child_index < len(self.heap) and self.heap[left_child_index] > self.heap[largest]:
                largest = left_child_index
            if right_child_index < len(self.heap) and self.heap[right_child_index] > self.heap[largest]:
                largest = right_child_index
            
            if largest == index:
                break

            self._log(f"Intercambiando {self.heap[index]} con {self.heap[largest]}")
            self.heap[index], self.heap[largest] = self.heap[largest], self.heap[index]
            index = largest
            self._log(f"Max-Heap después de intercambiar: {self.heap}")

    def build_max_heap(self, lst):
        self._log(f"\nConstruyendo Max-Heap desde la lista: {lst}")
        self.heap = lst
        start_index = len(lst) // 2 - 1
        for i in range(start_index, -1, -1):
            self._heapify_down(i)
        self._log(f"Max-Heap construido: {self.heap}")

    def get_max(self):
        if len(self.heap) == 0:
            raise IndexError("El heap está vacío")
        return self.heap[0]

    def _log(self, message):
        log_text.insert(tk.END, message + "\n")
        log_text.yview(tk.END)  # Auto-scroll down


# Función para manejar el evento de "Insertar Lista"
def on_insert_list():
    input_list = entry.get()
    try:
        numbers = [int(num.strip()) for num in input_list.split(',')]
        max_heap.build_max_heap(numbers)
    except ValueError:
        messagebox.showerror("Error", "Asegúrate de ingresar una lista válida de números separados por comas.")

# Función para manejar el evento de "Insertar un nuevo valor"
def on_insert_value():
    try:
        value = int(entry_value.get())
        max_heap.insert(value)
    except ValueError:
        messagebox.showerror("Error", "Ingresa un valor numérico válido para insertar.")

# Función para manejar el evento de "Eliminar el máximo"
def on_remove_max():
    try:
        max_value = max_heap.remove_max()
        log_text.insert(tk.END, f"\nValor máximo eliminado: {max_value}\n")
        log_text.yview(tk.END)  # Auto-scroll down
    except IndexError:
        messagebox.showerror("Error", "El heap está vacío, no se puede eliminar el máximo.")

# Función para reiniciar el Max-Heap
def on_reset():
    max_heap.__init__()  # Resetear el heap
    log_text.delete(1.0, tk.END)  # Limpiar el área de log

# Crear la ventana principal
root = tk.Tk()
root.title("Max-Heap GUI")

# Crear instancias del MaxHeap
max_heap = MaxHeap()

# Crear los componentes de la interfaz gráfica
instructions_label = tk.Label(root, text="Ingresa una lista de números separados por comas para construir el Max-Heap.")
instructions_label.pack(pady=5)

entry = tk.Entry(root, width=50)
entry.pack(pady=5)

insert_list_button = tk.Button(root, text="Insertar Lista", command=on_insert_list)
insert_list_button.pack(pady=5)

entry_value_label = tk.Label(root, text="Ingresa un valor para insertar al Max-Heap:")
entry_value_label.pack(pady=5)

entry_value = tk.Entry(root, width=50)
entry_value.pack(pady=5)

insert_value_button = tk.Button(root, text="Insertar Valor", command=on_insert_value)
insert_value_button.pack(pady=5)

remove_max_button = tk.Button(root, text="Eliminar Máximo", command=on_remove_max)
remove_max_button.pack(pady=5)

reset_button = tk.Button(root, text="Reiniciar", command=on_reset)
reset_button.pack(pady=5)

log_label = tk.Label(root, text="Proceso detallado:")
log_label.pack(pady=5)

log_text = tk.Text(root, width=80, height=20)
log_text.pack(pady=5)

# Ejecutar la ventana principal
root.mainloop()
