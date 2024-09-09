import tkinter as tk
from tkinter import messagebox
from sorting import mergeSort  

def sort_array():
    user_input = entry.get()
    try:
        array = list(map(int, user_input.split(',')))
        sorted_array = mergeSort(array)
        messagebox.showinfo("Resultado", f"Lista ordenada: {sorted_array}")
    except ValueError:
        messagebox.showerror("Error", "Por favor ingresa una lista válida de números separados por comas.")

root = tk.Tk()
root.title("Merge Sort - Ordenador de Listas")
root.geometry("520x340")
root.config(bg="#fdf6e3") 

bg_color = "#fdf6e3"  
frame_bg = "#eee8d5"  
btn_color = "#333333"  
btn_hover_color = "#1c658c" 
entry_bg = "#ffffff"  
entry_fg = "#657b83" 
text_color = "#586e75" 
font_main = ("Helvetica Neue", 14, "bold")  
font_secondary = ("Helvetica Neue", 12)  
def on_enter(e):
    button['bg'] = btn_hover_color

def on_leave(e):
    button['bg'] = btn_color

frame = tk.Frame(root, bg=frame_bg, padx=20, pady=20, relief=tk.FLAT, bd=2)
frame.place(relx=0.5, rely=0.5, anchor=tk.CENTER)

title_label = tk.Label(frame, text="Merge Sort - Ordena tu Lista", bg=frame_bg, fg=text_color, font=("Helvetica Neue", 18, "bold"))
title_label.pack(pady=10)

label = tk.Label(frame, text="Ingresa una lista de números separados por comas:", bg=frame_bg, fg=text_color, font=font_secondary)
label.pack(pady=10)

entry = tk.Entry(frame, width=40, font=font_secondary, bg=entry_bg, fg=entry_fg, relief=tk.FLAT, bd=1)
entry.pack(pady=10)

button = tk.Button(frame, text="Ordenar", command=sort_array, bg=btn_color, fg="white", font=font_main, relief=tk.FLAT, padx=20, pady=8, cursor="hand2")
button.pack(pady=20)

button.bind("<Enter>", on_enter)
button.bind("<Leave>", on_leave)

footer_label = tk.Label(root, text="Desarrollado por: Mi", bg=bg_color, fg=text_color, font=("Helvetica Neue", 10))
footer_label.pack(side="bottom", pady=10)

root.mainloop()
