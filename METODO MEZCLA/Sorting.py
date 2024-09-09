def mergeSort(arr):
    if len(arr) == 1:
        return arr
    
    middle = len(arr) // 2
    left_array = arr[:middle]
    right_array = arr[middle:]

    sorted_left_array = mergeSort(left_array)
    sorted_right_array = mergeSort(right_array)

    return Merge(sorted_left_array, sorted_right_array)

def Merge(left_arr, right_arr):
    arr_result = []
    while len(left_arr) > 0 and len(right_arr) > 0:
        if left_arr[0] > right_arr[0]:
            arr_result.append(right_arr[0])
            right_arr.pop(0)
        else: 
            arr_result.append(left_arr[0])
            left_arr.pop(0)

    while len(left_arr) > 0:
        arr_result.append(left_arr[0])
        left_arr.pop(0)

    while len(right_arr) > 0:
        arr_result.append(right_arr[0])
        right_arr.pop(0)

    return arr_result
