class MaxHeap:
    def __init__(self):
        self.heap = []

    def insert(self, value):
        print(f"\nInsertando el valor {value} al Max-Heap")
        self.heap.append(value)  # Agregar al final del heap
        self._heapify_up(len(self.heap) - 1)  # Reorganizar el heap
        print(f"Max-Heap después de insertar {value}: {self.heap}")

    def remove_max(self):
        if len(self.heap) == 0:
            raise IndexError("El heap está vacío")
        
        max_value = self.heap[0]  # La raíz es el máximo
        last_value = self.heap.pop()  # Eliminar el último valor
        print(f"\nEliminando el valor máximo {max_value}")

        if len(self.heap) > 0:
            self.heap[0] = last_value  # Mover el último valor a la raíz
            self._heapify_down(0)  # Reorganizar el heap
            print(f"Max-Heap después de eliminar el máximo: {self.heap}")
        else:
            print(f"El heap está vacío después de eliminar el máximo.")
        
        return max_value

    def _heapify_up(self, index):
        # Mover el elemento hacia arriba hasta que se restaure la propiedad del Max-Heap
        while index > 0:
            parent_index = (index - 1) // 2
            print(f"Comparando {self.heap[index]} con el padre {self.heap[parent_index]}")
            if self.heap[index] <= self.heap[parent_index]:
                break
            print(f"Intercambiando {self.heap[index]} con {self.heap[parent_index]}")
            self.heap[index], self.heap[parent_index] = self.heap[parent_index], self.heap[index]
            index = parent_index
            print(f"Max-Heap después de intercambiar: {self.heap}")

    def _heapify_down(self, index):
        # Mover el elemento hacia abajo hasta que se restaure la propiedad del Max-Heap
        while index < len(self.heap) // 2:  # Mientras tenga al menos un hijo
            left_child_index = 2 * index + 1
            right_child_index = 2 * index + 2
            largest = index

            if left_child_index < len(self.heap) and self.heap[left_child_index] > self.heap[largest]:
                print(f"Comparando {self.heap[left_child_index]} con {self.heap[largest]}")
                largest = left_child_index
            if right_child_index < len(self.heap) and self.heap[right_child_index] > self.heap[largest]:
                print(f"Comparando {self.heap[right_child_index]} con {self.heap[largest]}")
                largest = right_child_index
            
            if largest == index:
                break

            print(f"Intercambiando {self.heap[index]} con {self.heap[largest]}")
            self.heap[index], self.heap[largest] = self.heap[largest], self.heap[index]
            index = largest
            print(f"Max-Heap después de intercambiar: {self.heap}")

    def build_max_heap(self, lst):
        print(f"\nConstruyendo Max-Heap desde la lista: {lst}")
        self.heap = lst
        start_index = len(lst) // 2 - 1  # El último nodo que tiene hijos
        for i in range(start_index, -1, -1):
            self._heapify_down(i)
        print(f"Max-Heap construido: {self.heap}")

    def get_max(self):
        if len(self.heap) == 0:
            raise IndexError("El heap está vacío")
        return self.heap[0]

    def __str__(self):
        return str(self.heap)


# Función para tomar la entrada del usuario y crear el Max-Heap
def main():
    # Obtener la lista de números del usuario
    input_list = input("Ingresa una lista de números separados por comas: ")
    try:
        # Convertir la entrada en una lista de enteros
        numbers = [int(num.strip()) for num in input_list.split(',')]
        
        # Crear el Max-Heap
        max_heap = MaxHeap()
        max_heap.build_max_heap(numbers)
        
        # Insertar un nuevo valor
        new_value = int(input("Ingresa un nuevo valor para insertar en el Max-Heap: "))
        max_heap.insert(new_value)
        
        # Eliminar el máximo valor
        removed_value = max_heap.remove_max()
        
        # Mostrar el máximo valor actual
        print("El valor máximo actual es:", max_heap.get_max())
    
    except ValueError:
        print("Error: Asegúrate de ingresar una lista de números válidos separados por comas.")

if __name__ == "__main__":
    main()
