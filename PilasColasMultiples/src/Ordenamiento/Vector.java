package Ordenamiento;

public class Vector {

    // Atributos
    private int vector[];
    private int tamaño;
    private int nIntercambios;
    private int nComparaciones;
    private int nPasadas;

    // Constructores
    public Vector(int t) {
        vector = new int[t];
        tamaño = t;
        reiniciarContadores();
        for (int i = 0; i < t; i++) {
            vector[i] = (int) (Math.random() * t);
        }
    }

    public Vector(int vec[]) {
        tamaño = vec.length;
        reiniciarContadores();
        vector = vec.clone();
    }

    // Métodos auxiliares
    private void reiniciarContadores() {
        nIntercambios = 0;
        nComparaciones = 0;
        nPasadas = 0;
    }

    private void intercambio(int i, int j) {
        int aux = vector[i];
        vector[i] = vector[j];
        vector[j] = aux;
    }

    // ==========================
    // MÉTODOS DE ORDENAMIENTO
    // ==========================
    // BURBUJA
    public void ordenarBurbuja() {
        ordenarBurbuja(0, tamaño - 1);
    }

    public void ordenarBurbuja(int inicio) {
        ordenarBurbuja(inicio, tamaño - 1);
    }

    public void ordenarBurbuja(int inicio, int fin) {
        reiniciarContadores();
        for (int i = inicio; i < fin; i++) {
            nPasadas++;
            for (int j = inicio; j < fin - (i - inicio); j++) {
                nComparaciones++;
                if (vector[j] > vector[j + 1]) {
                    intercambio(j, j + 1);
                    nIntercambios++;
                }
            }
        }
    }

    // HUNDIMIENTO
    public void ordenarHundimiento() {
        ordenarHundimiento(0, tamaño - 1);
    }

    public void ordenarHundimiento(int inicio) {
        ordenarHundimiento(inicio, tamaño - 1);
    }

    public void ordenarHundimiento(int inicio, int fin) {
        reiniciarContadores();
        for (int i = inicio; i < fin; i++) {
            nPasadas++;
            for (int j = inicio; j < fin - (i - inicio); j++) {
                nComparaciones++;
                if (vector[j] > vector[j + 1]) {
                    intercambio(j, j + 1);
                    nIntercambios++;
                }
            }
        }
    }

    // SELECCIÓN LINEAL CON INTERCAMBIO
    public void ordenarSLI() {
        ordenarSLI(0, tamaño - 1);
    }

    public void ordenarSLI(int inicio) {
        ordenarSLI(inicio, tamaño - 1);
    }

    public void ordenarSLI(int inicio, int fin) {
        reiniciarContadores();
        for (int i = inicio; i < fin; i++) {
            for (int j = i + 1; j <= fin; j++) {
                nComparaciones++;
                if (vector[i] > vector[j]) {
                    intercambio(i, j);
                    nIntercambios++;
                }
            }
            nPasadas++;
        }
    }

    // SELECCIÓN LINEAL CON CONTEO (SLC)
    public void ordenarSLC() {
        ordenarSLC(0, tamaño - 1);
    }

    public void ordenarSLC(int inicio) {
        ordenarSLC(inicio, tamaño - 1);
    }

    public void ordenarSLC(int inicio, int fin) {
        reiniciarContadores();
        int n = fin - inicio + 1;
        int[] aux = new int[n];
        boolean[] ocupado = new boolean[n];

        for (int i = inicio; i <= fin; i++) {
            int conteo = 0;
            for (int j = inicio; j <= fin; j++) {
                nComparaciones++;
                if (vector[j] < vector[i]) {
                    conteo++;
                }
            }
            while (ocupado[conteo]) {
                conteo++;
            }
            aux[conteo] = vector[i];
            ocupado[conteo] = true;
            nPasadas++;
        }

        for (int i = 0; i < n; i++) {
            vector[inicio + i] = aux[i];
        }
    }

    // SHELL
    public void ordenarShell() {
        ordenarShell(0, tamaño - 1);
    }

    public void ordenarShell(int inicio) {
        ordenarShell(inicio, tamaño - 1);
    }

    public void ordenarShell(int inicio, int fin) {
        reiniciarContadores();
        int n = fin - inicio + 1;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            nPasadas++;
            for (int i = inicio + gap; i <= fin; i++) {
                int temp = vector[i];
                int j = i;
                while (j - gap >= inicio && vector[j - gap] > temp) {
                    nComparaciones++;
                    vector[j] = vector[j - gap];
                    j -= gap;
                    nIntercambios++;
                }
                vector[j] = temp;
            }
        }
    }

    // QUICKSORT
    public void ordenarQuick() {
        ordenarQuick(0, tamaño - 1);
    }

    public void ordenarQuick(int inicio) {
        ordenarQuick(inicio, tamaño - 1);
    }

    public void ordenarQuick(int inicio, int fin) {
        reiniciarContadores();
        quickSort(inicio, fin);
    }

    private void quickSort(int inicio, int fin) {
        if (inicio < fin) {
            nPasadas++;
            int p = particionar(inicio, fin);
            quickSort(inicio, p - 1);
            quickSort(p + 1, fin);
        }
    }

    private int particionar(int inicio, int fin) {
        int pivote = vector[fin];
        int i = inicio - 1;
        for (int j = inicio; j < fin; j++) {
            nComparaciones++;
            if (vector[j] <= pivote) {
                i++;
                intercambio(i, j);
                nIntercambios++;
            }
        }
        intercambio(i + 1, fin);
        return i + 1;
    }

    // MERGESORT
    public void ordenarMerge() {
        ordenarMerge(0, tamaño - 1);
    }

    public void ordenarMerge(int inicio) {
        ordenarMerge(inicio, tamaño - 1);
    }

    public void ordenarMerge(int inicio, int fin) {
        reiniciarContadores();
        mergeSort(inicio, fin);
    }

    private void mergeSort(int inicio, int fin) {
        if (inicio < fin) {
            int medio = (inicio + fin) / 2;
            mergeSort(inicio, medio);
            mergeSort(medio + 1, fin);
            merge(inicio, medio, fin);
            nPasadas++;
        }
    }

    private void merge(int inicio, int medio, int fin) {
        int n1 = medio - inicio + 1;
        int n2 = fin - medio;
        int[] izq = new int[n1];
        int[] der = new int[n2];
        for (int i = 0; i < n1; i++) {
            izq[i] = vector[inicio + i];
        }
        for (int j = 0; j < n2; j++) {
            der[j] = vector[medio + 1 + j];
        }
        int i = 0, j = 0, k = inicio;
        while (i < n1 && j < n2) {
            nComparaciones++;
            if (izq[i] <= der[j]) {
                vector[k++] = izq[i++];
            } else {
                vector[k++] = der[j++];
            }
            nIntercambios++;
        }
        while (i < n1) {
            vector[k++] = izq[i++];
        }
        while (j < n2) {
            vector[k++] = der[j++];
        }
    }

    // INTERCAMBIO DE PAREJAS
    public void ordenarIntercambioParejas() {
        ordenarIntercambioParejas(0, tamaño - 1);
    }

    public void ordenarIntercambioParejas(int inicio) {
        ordenarIntercambioParejas(inicio, tamaño - 1);
    }

    public void ordenarIntercambioParejas(int inicio, int fin) {
        reiniciarContadores();
        boolean estaOrdenado = false;
        while (!estaOrdenado) {
            estaOrdenado = true;
            nPasadas++;

            for (int i = inicio; i < fin; i += 2) {
                nComparaciones++;
                if (vector[i] > vector[i + 1]) {
                    intercambio(i, i + 1);
                    nIntercambios++;
                    estaOrdenado = false;
                }
            }

            for (int i = inicio + 1; i < fin; i += 2) {
                nComparaciones++;
                if (vector[i] > vector[i + 1]) {
                    intercambio(i, i + 1);
                    nIntercambios++;
                    estaOrdenado = false;
                }
            }
        }
    }

    // RADIX SORT
    public void ordenarRadix() {
        ordenarRadix(0, tamaño - 1);
    }

    public void ordenarRadix(int inicio) {
        ordenarRadix(inicio, tamaño - 1);
    }

    public void ordenarRadix(int inicio, int fin) {
        reiniciarContadores();
        int max = vector[inicio];
        for (int i = inicio + 1; i <= fin; i++) {
            if (vector[i] > max) {
                max = vector[i];
            }
        }
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSort(exp, inicio, fin);
            nPasadas++;
        }
    }

    private void countingSort(int exp, int inicio, int fin) {
        int n = fin - inicio + 1;
        int[] salida = new int[n];
        int[] conteo = new int[10];

        for (int i = inicio; i <= fin; i++) {
            int digito = (vector[i] / exp) % 10;
            conteo[digito]++;
            nComparaciones++;
        }

        for (int i = 1; i < 10; i++) {
            conteo[i] += conteo[i - 1];
        }

        for (int i = fin; i >= inicio; i--) {
            int digito = (vector[i] / exp) % 10;
            salida[conteo[digito] - 1] = vector[i];
            conteo[digito]--;
            nIntercambios++;
        }

        for (int i = 0; i < n; i++) {
            vector[inicio + i] = salida[i];
        }
    }

    // INSERCIÓN EN ÁRBOLES
    private class NodoArbol {

        int dato;
        NodoArbol izquierdo, derecho;

        NodoArbol(int d) {
            dato = d;
        }
    }

    private NodoArbol raiz;
    private int indice;

    public void ordenarArbol() {
        ordenarArbol(0, tamaño - 1);
    }

    public void ordenarArbol(int inicio) {
        ordenarArbol(inicio, tamaño - 1);
    }

    public void ordenarArbol(int inicio, int fin) {
        reiniciarContadores();
        raiz = null;
        for (int i = inicio; i <= fin; i++) {
            insertar(vector[i]);
            nPasadas++;
        }
        int[] resultado = new int[fin - inicio + 1];
        indice = 0;
        inOrden(raiz, resultado);
        for (int i = 0; i < resultado.length; i++) {
            vector[inicio + i] = resultado[i];
        }
    }

    private void insertar(int dato) {
        raiz = insertarNodo(raiz, dato);
    }

    private NodoArbol insertarNodo(NodoArbol nodo, int dato) {
        if (nodo == null) {
            return new NodoArbol(dato);
        }
        nComparaciones++;
        if (dato < nodo.dato) {
            nodo.izquierdo = insertarNodo(nodo.izquierdo, dato);
        } else {
            nodo.derecho = insertarNodo(nodo.derecho, dato);
        }
        nIntercambios++;
        return nodo;
    }

    private void inOrden(NodoArbol nodo, int[] resultado) {
        if (nodo != null) {
            inOrden(nodo.izquierdo, resultado);
            resultado[indice++] = nodo.dato;
            inOrden(nodo.derecho, resultado);
        }
    }

    // ==========================
    // MÉTODOS DE BÚSQUEDA
    // ==========================
    public int busquedaSecuencial(int dato) {
        reiniciarContadores();
        for (int i = 0; i < vector.length; i++) {
            nComparaciones++;
            if (vector[i] == dato) {
                return i;
            }
        }
        return -1;
    }

    public int busquedaBinaria(int dato) {
        reiniciarContadores();
        int izq = 0, der = vector.length - 1;
        while (izq <= der) {
            int centro = (izq + der) / 2;
            nComparaciones++;
            if (vector[centro] == dato) {
                return centro;
            }
            if (dato < vector[centro]) {
                der = centro - 1;
            } else {
                izq = centro + 1;
            }
        }
        return -1;
    }

    // ==========================
    // GETTERS
    // ==========================
    public int getPasadas() {
        return nPasadas;
    }

    public int getIntercambios() {
        return nIntercambios;
    }

    public int getComparaciones() {
        return nComparaciones;
    }

    public int[] getVector() {
        return vector;
    }

    // ==========================
    // UTILIDADES
    // ==========================
    public int[] generarVector(int cantidad) {
        int[] temp = new int[cantidad];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = (int) (Math.random() * cantidad);
        }
        return temp;
    }

    public String toString() {
        String sal = "CONTENIDO DEL VECTOR\n";
//        for (int i = 0; i < vector.length; i++) {
//            sal += "Vector[" + i + "] = " + vector[i] + "\n";
//        }
        sal += "\nNúmero de elementos     = " + tamaño;
        sal += "\nNúmero de pasadas       = " + nPasadas;
        sal += "\nNúmero de intercambios  = " + nIntercambios;
        sal += "\nNúmero de comparaciones = " + nComparaciones + "\n";
        return sal;
    }
    
    //Intercalar dos vectores
    public void intercalarVector(Vector a, Vector b) {
        reiniciarContadores();
        a.ordenarBurbuja();
        b.ordenarBurbuja();
        int z = a.getVector().length + b.getVector().length;
        int m = a.getVector().length;
        int n = b.getVector().length;
        int[] c = a.getVector();
        int[] d = b.getVector();
        int[] vec = new int[z];
        int k = 0;
        int j = 0;
        int i = 0;
        while (i < z && k < n && j < m) {
            if (c[j] < d[k]) {
                 vec[i++] = c[j++];  
            } else {
                vec[i++] = d[k++];
            }
            nComparaciones++;
            nPasadas++;
        }
        if (i < z) {
            if ((k < n)) {
                while(k < n) {
                    vec[i++] = d[k++];
                }   
            } else {
                 while(j < m) {
                     vec[i++] = c[j++];
                 }
            }
        }
        this.vector = vec;
        this.tamaño = vec.length;
    }
    
    public static int[][] dividirVector(int[] vector, int partes) {
        if (partes <= 0 || vector.length % partes != 0) {
            System.out.println("El número de partes debe dividir exactamente el tamaño del vector");
        }

        int elementosPorParte = vector.length / partes;
        int[][] resultado = new int[partes][elementosPorParte];

        int indice = 0;
        for (int i = 0; i < partes; i++) {
            for (int j = 0; j < elementosPorParte; j++) {
                resultado[i][j] = vector[indice++];
            }
        }

        return resultado;
    }

}
