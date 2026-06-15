/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ordenamiento;

/**
 *
 * @author Gaby
 */

public class Main {
    public static void main(String args[]) {
        //primeraPruebaDoc();
        int[] tamaños = {500, 1000, 2000, 3000, 5000, 8000, 10000, 15000, 20000};
        String[] metodos = {
            "Burbuja", "Hundimiento", "SLI", "SLC",
            "Shell", "Quick", "Merge",
            "Parejas", "Radix", "Arbol"
        };

        System.out.println("===== PRUEBAS DE VOLUMEN - MÉTODOS DE ORDENAMIENTO =====\n");

        for (String metodo : metodos) {
            System.out.println("\n=== MÉTODO: " + metodo + " ===");
            for (int t : tamaños) {
                VectorX v = new VectorX(t);
                long inicioMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                long inicio = System.nanoTime();

                switch (metodo) {
                    case "Burbuja":
                        v.ordenarBurbuja();
                        break;
                    case "Hundimiento":
                        v.ordenarHundimiento();
                        break;
                    case "SLI":
                        v.ordenarSLI();
                        break;
                    case "SLC":
                        v.ordenarSLC();
                        break;
                    case "Shell":
                        v.ordenarShell();
                        break;
                    case "Quick":
                        v.ordenarQuick();
                        break;
                    case "Merge":
                        v.ordenarMerge();
                        break;
                    case "Parejas":
                        v.ordenarIntercambioParejas();
                        break;
                    case "Radix":
                        v.ordenarRadix();
                        break;
                    case "Arbol":
                        v.ordenarArbol();
                        break;
                }
                long fin = System.nanoTime();
                long finMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                long duracion = (fin - inicio) / 1_000_000; // milisegundos
                long memoriaUsada = Math.abs(finMem - inicioMem);

                System.out.println("Tamaño=" + t +
                        " | Pasadas=" + v.getPasadas() +
                        " | Intercambios=" + v.getIntercambios() +
                        " | Comparaciones=" + v.getComparaciones() +
                        " | Duración=" + duracion + " ms" +
                        " | Memoria=" + memoriaUsada + " bytes");
            }
        }

        System.out.println("\n===== FIN DE LAS PRUEBAS =====");
    }
    
    public static void primeraPruebaDoc() {
        // Generar un vector aleatorio de 20 elementos
        VectorX original = new VectorX(20);
        int[] datos = original.getVector().clone();

        System.out.println("ORIGINAL = { " + mostrarConjunto(datos) + " }");

        // Probar cada método con el mismo vector original
        probarOrdenamiento("Burbuja", new VectorX(datos.clone()), "ordenarBurbuja");
        probarOrdenamiento("Hundimiento", new VectorX(datos.clone()), "ordenarHundimiento");
        probarOrdenamiento("Selección Lineal Intercambio", new VectorX(datos.clone()), "ordenarSLI");
        probarOrdenamiento("Shell", new VectorX(datos.clone()), "ordenarShell");
        probarOrdenamiento("QuickSort", new VectorX(datos.clone()), "ordenarQuick");
        probarOrdenamiento("MergeSort", new VectorX(datos.clone()), "ordenarMerge");
    }

    // Método para imprimir resultados de cada algoritmo
    private static void probarOrdenamiento(String nombre, VectorX v, String metodo) {
        long inicio = System.nanoTime();
        switch (metodo) {
            case "ordenarBurbuja": v.ordenarBurbuja(); break;
            case "ordenarHundimiento": v.ordenarHundimiento(); break;
            case "ordenarSLI": v.ordenarSLI(); break;
            case "ordenarShell": v.ordenarShell(); break;
            case "ordenarQuick": v.ordenarQuick(); break;
            case "ordenarMerge": v.ordenarMerge(); break;
        }
        long fin = System.nanoTime();

        System.out.println("\n=== " + nombre + " ===");
        System.out.println("RESULTADO = { " + mostrarConjunto(v.getVector()) + " }");
        System.out.println("Pasadas=" + v.getPasadas() +
                           " | Intercambios=" + v.getIntercambios() +
                           " | Comparaciones=" + v.getComparaciones() +
                           " | Tiempo=" + (fin - inicio) / 1_000_000.0 + " ms");
        
    }

    // Utilidad para mostrar vectores en formato {a,b,c,...}
    private static String mostrarConjunto(int[] vec) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < vec.length; i++) {
            sb.append(vec[i]);
            if (i < vec.length - 1) sb.append(", ");
        }
        return sb.toString();
    }
}

