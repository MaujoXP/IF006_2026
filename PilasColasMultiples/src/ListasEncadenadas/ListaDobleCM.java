package ListasEncadenadas;

import Pila.Pila;

public class ListaDobleCM {
    private int TOP;        // Máximo
    private int INFO[];     // Datos
    private int AIzq[];     // Apuntador izquierda
    private int ADer[];     // Apuntador derecha
    private int vPTR[];     // Varios punteros de lista
    private Pila DISP;      // Pila de disponibles

    // Constructor por defecto (5 listas)
    public ListaDobleCM() {
        TOP = 20;
        INFO = new int[TOP];
        AIzq = new int[TOP];
        ADer = new int[TOP];
        vPTR = new int[5];
        DISP = new Pila(TOP - 1);
        inicializarPila();
    }

    // Constructor con tamaño y número de listas
    public ListaDobleCM(int max, int nListas) {
        TOP = max + 1;
        INFO = new int[TOP];
        AIzq = new int[TOP];
        ADer = new int[TOP];
        vPTR = new int[nListas];
        DISP = new Pila(TOP - 1);
        inicializarPila();
    }

    private void inicializarPila() {
        for (int i = 1; i < TOP; i++) {
            DISP.ingresarElemento(i);
        }
    }

    public void agregarElemento(int elemento, int nPTR) {
        int X = DISP.eliminarElemento();
        INFO[X] = elemento;

        if (vPTR[nPTR] == 0) {
            // primer nodo de la lista
            vPTR[nPTR] = X;
            AIzq[X] = X;
            ADer[X] = X;
        } else {
            int PTR = vPTR[nPTR];
            AIzq[X] = AIzq[PTR];
            ADer[X] = PTR;

            ADer[AIzq[PTR]] = X;
            AIzq[PTR] = X;

            vPTR[nPTR] = X;
        }
    }

    public int eliminarElemento(int nPTR) {
        int PTR = vPTR[nPTR];
        int X = PTR;
        int elemento = INFO[X];

        INFO[X] = 0;

        if (AIzq[X] == X && ADer[X] == X) {
            vPTR[nPTR] = 0; // lista queda vacía
        } else {
            vPTR[nPTR] = ADer[X];
            AIzq[vPTR[nPTR]] = AIzq[X];
            ADer[AIzq[X]] = vPTR[nPTR];
        }

        AIzq[X] = 0;
        ADer[X] = 0;

        DISP.ingresarElemento(X);
        return elemento;
    }

    public int cantidadElementos(int nPTR) {
        int PTR = vPTR[nPTR];
        if (PTR == 0) return 0;

        int R = PTR;
        int cantidad = 0;
        boolean inicio = false;

        while (R != PTR || !inicio) {
            inicio = true;
            cantidad++;
            R = ADer[R];
        }
        return cantidad;
    }

    public String toString() {
        String salida = "";
        for (int i = 1; i < TOP; i++) {
            salida += i + " ";
            salida += "[" + INFO[i] + "]";
            salida += "[" + AIzq[i] + "]";
            salida += "[" + ADer[i] + "]\n";
        }
        salida += "Estado de la pila\n";
        salida += DISP.toString();
        return salida;
    }
}

