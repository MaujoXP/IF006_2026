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
        segundaPruebaDoc(); 
//        Vector vec = new Vector(1000);
//        vec.ordenarBurbuja();
//        System.out.println("Mil ordenados");
//        System.out.println(vec);
//        int[][] vec1 = vec.dividirVector(vec.getVector(), 4);
//        int[] a = vec1[0];
//        System.out.println("Partes ordenadas");
//        Vector aa = new Vector(a);
//        aa.ordenarBurbuja();
//        System.out.println(aa);
//        int[] b = vec1[1];
//        Vector bb = new Vector(b);
//        bb.ordenarBurbuja();
//        System.out.println(bb);
//        int[] c = vec1[2];
//        Vector cc = new Vector(c);
//        cc.ordenarBurbuja();
//        System.out.println(cc);
//        int[] d = vec1[3];
//        Vector dd = new Vector(d);
//        dd.ordenarBurbuja();
//        System.out.println(dd);
//        Vector A = new Vector(a);
//        Vector B = new Vector(b);
//        Vector C = new Vector(c);
//        Vector D = new Vector(d);
//        vec.intercalarVector(A, B);
//        System.out.println("Partes intercaladas 250+250");
//        System.out.println(vec);
//        Vector AB = new Vector(vec.getVector());
//        //System.out.println(vec);
//        Vector Aa = new Vector(vec.getVector());
//        vec.intercalarVector(C, D);
//        System.out.println("Partes intercaladas 250+250");
//        System.out.println(vec);
//        Vector CD = new Vector(vec.getVector());
//        //System.out.println(vec);
//        Vector Bb = new Vector(vec.getVector());
//        vec.intercalarVector(Aa, Bb);
//        System.out.println("Partes intercaladas 500+500");
//        System.out.println(vec);
//        Vector AaBb = new Vector(vec.getVector());
//        A.ordenarBurbuja();
//        B.ordenarBurbuja();
//        C.ordenarBurbuja();
//        D.ordenarBurbuja();
//        A.ordenarHundimiento();
//        B.ordenarHundimiento();
//        C.ordenarHundimiento();
//        D.ordenarHundimiento();
//        System.out.println(A);
//        System.out.println(B);
//        System.out.println(C);
//        System.out.println(D);
        
    }

    // Método para imprimir resultados de cada algoritmo
    private static void probarOrdenamiento(String nombre, Vector v, String metodo) {
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
    
    public static void segundaPruebaDoc() {
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
                Vector v = new Vector(t);
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
        Vector original = new Vector(20);
        int[] datos = original.getVector().clone();

        System.out.println("ORIGINAL = { " + mostrarConjunto(datos) + " }");

        // Probar cada método con el mismo vector original
        probarOrdenamiento("Burbuja", new Vector(datos.clone()), "ordenarBurbuja");
        probarOrdenamiento("Hundimiento", new Vector(datos.clone()), "ordenarHundimiento");
        probarOrdenamiento("Selección Lineal Intercambio", new Vector(datos.clone()), "ordenarSLI");
        probarOrdenamiento("Shell", new Vector(datos.clone()), "ordenarShell");
        probarOrdenamiento("QuickSort", new Vector(datos.clone()), "ordenarQuick");
        probarOrdenamiento("MergeSort", new Vector(datos.clone()), "ordenarMerge");
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

