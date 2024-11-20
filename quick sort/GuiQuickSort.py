import tkinter as tk
from tkinter import messagebox

def quick_sort(arr, depth=0):
    """
    Implementación del algoritmo Quick Sort.
    """
    if len(arr) <= 1:
        return arr

    pivot = arr[-1]
    less_than_pivot = [x for x in arr[:-1] if x <= pivot]
    greater_than_pivot = [x for x in arr[:-1] if x > pivot]

    sorted_less = quick_sort(less_than_pivot, depth + 1)
    sorted_greater = quick_sort(greater_than_pivot, depth + 1)

    return sorted_less + [pivot] + sorted_greater

def sort_numbers():
    """
    Obtiene los números del campo de entrada, los ordena y muestra el resultado.
    """
    entrada = entry.get()
    try:
        # Convertir la entrada a una lista de números
        lista = [int(num.strip()) for num in entrada.split(",")]
        # Ordenar la lista con Quick Sort
        resultado = quick_sort(lista)
        # Mostrar el resultado en el área de salida
        result_text.set(f"Lista ordenada: {resultado}")
    except ValueError:
        # Mostrar mensaje de error si la entrada no es válida
        messagebox.showerror("Error", "Por favor, ingresa solo números separados por comas.")

# Crear la ventana principal
root = tk.Tk()
root.title("Quick Sort GUI")
root.geometry("400x300")
root.resizable(False, False)

# Etiqueta para instrucciones
label = tk.Label(root, text="Ingresa una lista de números separados por comas:", font=("Arial", 12))
label.pack(pady=10)

# Campo de entrada
entry = tk.Entry(root, width=40, font=("Arial", 12))
entry.pack(pady=10)

# Botón para ordenar
sort_button = tk.Button(root, text="Ordenar", command=sort_numbers, font=("Arial", 12), bg="#4CAF50", fg="white")
sort_button.pack(pady=10)

# Área para mostrar el resultado
result_text = tk.StringVar()
result_label = tk.Label(root, textvariable=result_text, font=("Arial", 12), wraplength=380)
result_label.pack(pady=20)

# Iniciar el loop principal de la GUI
root.mainloop()
