def quick_sort(arr, depth=0):
    """
    Implementación del algoritmo Quick Sort con seguimiento del proceso.
    """
    # Indentación para visualizar recursión
    indent = "  " * depth
    print(f"{indent}Ordenando: {arr}")

    # Caso base: si la lista tiene 0 o 1 elemento, ya está ordenada
    if len(arr) <= 1:
        print(f"{indent}Lista pequeña o vacía, ya está ordenada: {arr}")
        return arr

    # Elegimos un pivote (usaremos el último elemento)
    pivot = arr[-1]
    print(f"{indent}Pivote elegido: {pivot}")

    # Dividimos la lista en dos partes: menores y mayores que el pivote
    less_than_pivot = [x for x in arr[:-1] if x <= pivot]  # Elementos menores o iguales al pivote
    greater_than_pivot = [x for x in arr[:-1] if x > pivot]  # Elementos mayores al pivote

    print(f"{indent}Menores o iguales que el pivote: {less_than_pivot}")
    print(f"{indent}Mayores que el pivote: {greater_than_pivot}")

    # Ordenamos recursivamente las sublistas y las combinamos con el pivote
    sorted_less = quick_sort(less_than_pivot, depth + 1)
    sorted_greater = quick_sort(greater_than_pivot, depth + 1)

    result = sorted_less + [pivot] + sorted_greater
    print(f"{indent}Resultado combinado: {result}")
    return result

if __name__ == "__main__":
    # Solicitar al usuario una lista de números separados por comas
    entrada = input("Ingresa una lista de números separados por comas: ")
    try:
        # Convertimos la entrada a una lista de enteros
        lista = [int(num.strip()) for num in entrada.split(",")]
        print("\nInicio del proceso de Quick Sort:")
        lista_ordenada = quick_sort(lista)
        print("\nLista ordenada final:", lista_ordenada)
    except ValueError:
        print("Por favor, ingresa solo números separados por comas.")
