import matplotlib.pyplot as plt

# Datos de ejemplo (tiempo de ejecución en ms)
muebles = [1, 5, 10, 20]
trabajadores_1 = [500, 2800, 6000, 12000]  # tiempo con 1 trabajador
trabajadores_2 = [500, 1800, 3500, 7000]   # tiempo con 2 trabajadores
trabajadores_5 = [500, 1200, 2000, 4000]   # tiempo con 5 trabajadores

plt.figure(figsize=(10,6))

plt.plot(muebles, trabajadores_1, marker='o', label="1 trabajador")
plt.plot(muebles, trabajadores_2, marker='s', label="2 trabajadores")
plt.plot(muebles, trabajadores_5, marker='^', label="5 trabajadores")

plt.title("Escalabilidad del ensamblaje de muebles")
plt.xlabel("Número de muebles")
plt.ylabel("Tiempo de ejecución (ms)")
plt.xticks(muebles)
plt.grid(True)
plt.legend()
plt.show()
