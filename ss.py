import matplotlib.pyplot as plt
import numpy as np

# Configurar el estilo
plt.style.use('seaborn-v0_8-whitegrid')
plt.rcParams['figure.figsize'] = (14, 8)
plt.rcParams['font.size'] = 12
plt.rcParams['font.family'] = 'serif'

# Datos del número de platos
n_platos = np.array([5, 8, 10, 12, 15, 18, 20, 22, 25])

# Combinaciones posibles (2^n - 1)
combinaciones_teoricas = 2**n_platos - 1

# Tiempo estimado en milisegundos
# Tiempo base: 0.3 ms para n=5
tiempo_base = 0.3
tiempos_ms = tiempo_base * (2**(n_platos - 5))

# Convertir a segundos para valores grandes
tiempos_segundos = tiempos_ms / 1000

# Crear figura
fig, ax = plt.subplots(figsize=(14, 8))

# ============================================================
# GRÁFICO: Tiempo de Ejecución vs Número de Platos
# ============================================================
# Separar datos en dos escalas
mask_ms = tiempos_ms < 1000
mask_seg = tiempos_ms >= 1000

# Plot principal
ax.semilogy(n_platos[mask_ms], tiempos_ms[mask_ms], 'o-', linewidth=2.5, 
            markersize=9, color='#2E86AB', label='Tiempo (milisegundos)', markeredgecolor='white', markeredgewidth=1.5)
ax.semilogy(n_platos[mask_seg], tiempos_segundos[mask_seg], 's-', linewidth=2.5, 
            markersize=9, color='#A23B72', label='Tiempo (segundos)', markeredgecolor='white', markeredgewidth=1.5)

# Líneas de referencia
ax.axvline(x=5, color='#06A77D', linestyle='--', linewidth=2, alpha=0.6, 
           label='Caso base: n = 5')
ax.axhline(y=100, color='#F18F01', linestyle=':', linewidth=2, alpha=0.7, 
           label='Límite aceptable: 100 ms')
ax.axhline(y=1000, color='#C73E1D', linestyle=':', linewidth=2, alpha=0.7, 
           label='Límite crítico: 1 segundo')

# Zonas de rendimiento
ax.axhspan(0.001, 100, alpha=0.08, color='#06A77D', zorder=0)
ax.axhspan(100, 1000, alpha=0.08, color='#F18F01', zorder=0)
ax.axhspan(1000, max(tiempos_ms), alpha=0.08, color='#C73E1D', zorder=0)

# Etiquetas y título
ax.set_xlabel('Número de Platos (n)', fontsize=14, fontweight='bold')
ax.set_ylabel('Tiempo de Ejecución (escala logarítmica)', fontsize=14, fontweight='bold')
ax.set_title('Análisis de Complejidad Temporal: Algoritmo Backtracking O(2ⁿ)', 
             fontsize=16, fontweight='bold', pad=20)

# Grid mejorado
ax.grid(True, alpha=0.3, linestyle='-', linewidth=0.5)
ax.set_axisbelow(True)

# Leyenda profesional
ax.legend(fontsize=11, loc='upper left', framealpha=0.95, edgecolor='gray', fancybox=True)

# Anotaciones científicas
ax.annotate(f'n = 5\nt = {tiempos_ms[0]:.2f} ms', 
            xy=(5, tiempos_ms[0]), 
            xytext=(7, 0.08), 
            fontsize=11,
            bbox=dict(boxstyle='round,pad=0.6', facecolor='white', edgecolor='#06A77D', linewidth=2, alpha=0.9),
            arrowprops=dict(arrowstyle='->', color='#06A77D', lw=2))

ax.annotate(f'n = 15\nt = {tiempos_ms[4]:.0f} ms', 
            xy=(15, tiempos_ms[4]), 
            xytext=(17, tiempos_ms[4]*0.3), 
            fontsize=11,
            bbox=dict(boxstyle='round,pad=0.6', facecolor='white', edgecolor='#F18F01', linewidth=2, alpha=0.9),
            arrowprops=dict(arrowstyle='->', color='#F18F01', lw=2))

ax.annotate(f'n = 20\nt = {tiempos_segundos[6]:.1f} s', 
            xy=(20, tiempos_segundos[6]), 
            xytext=(22, tiempos_segundos[6]*0.15), 
            fontsize=11,
            bbox=dict(boxstyle='round,pad=0.6', facecolor='white', edgecolor='#C73E1D', linewidth=2, alpha=0.9),
            arrowprops=dict(arrowstyle='->', color='#C73E1D', lw=2))

# Ajustar límites
ax.set_xlim(4, 26)
ax.set_ylim(0.05, max(tiempos_ms)*2)

# Ajustar layout
plt.tight_layout()

# Guardar la figura en alta resolución
plt.savefig('analisis_temporal_backtracking.png', dpi=300, bbox_inches='tight', facecolor='white')
print("✅ Gráfico guardado como 'analisis_temporal_backtracking.png'")

# Mostrar la figura
plt.show()

# ============================================================
# TABLA DE RESULTADOS
# ============================================================
print("\n" + "="*80)
print(" "*20 + "ANÁLISIS DE COMPLEJIDAD TEMPORAL")
print("="*80)
print(f"{'n':<8} {'Combinaciones':<20} {'Tiempo (ms)':<18} {'Tiempo (s)':<18}")
print("-"*80)

for i, n in enumerate(n_platos):
    if tiempos_ms[i] < 1000:
        print(f"{n:<8} {combinaciones_teoricas[i]:<20,} {tiempos_ms[i]:<18.2f} {'—':<18}")
    else:
        print(f"{n:<8} {combinaciones_teoricas[i]:<20,} {tiempos_ms[i]:<18.0f} {tiempos_segundos[i]:<18.2f}")

print("="*80)
print("\n📊 RESULTADOS DEL ANÁLISIS:")
print("   • Complejidad temporal: O(2ⁿ)")
print("   • Rendimiento óptimo: n ≤ 12 (< 100 ms)")
print("   • Rendimiento aceptable: n ≤ 15 (< 1 segundo)")
print("   • Límite de viabilidad práctica: n ≈ 20")
print("\n" + "="*80)