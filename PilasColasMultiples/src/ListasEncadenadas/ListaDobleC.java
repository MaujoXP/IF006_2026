package ListasEncadenadas;

import Pila.Pila;

public class ListaDobleC {
    private int TOP;
    private int INFO[];
    private int AIzq[];
    private int ADer[];
    private int PTR;
    private Pila DISP;

    public ListaDobleC() {
        TOP = 20;
        INFO = new int[TOP];
        AIzq = new int[TOP];
        ADer = new int[TOP];
        PTR = 0;
        DISP = new Pila(TOP - 1);
        inicializarPila();
    }

    public ListaDobleC(int max) {
        TOP = max + 1;
        INFO = new int[TOP];
        AIzq = new int[TOP];
        ADer = new int[TOP];
        PTR = 0;
        DISP = new Pila(TOP - 1);
        inicializarPila();
    }

    private void inicializarPila() {
        for (int i = 1; i < TOP; i++) {
            DISP.ingresarElemento(i);
        }
    }

    public void agregarElemento(int elemento) {
        int X = DISP.eliminarElemento();
        INFO[X] = elemento;

        if (PTR == 0) {
            PTR = X;
            AIzq[X] = X;
            ADer[X] = X;
        } else {
            AIzq[X] = AIzq[PTR];
            ADer[X] = PTR;

            ADer[AIzq[PTR]] = X;
            AIzq[PTR] = X;

            PTR = X;
        }
    }

    public int eliminarElemento() {
        int X = PTR;
        int elemento = INFO[X];

        INFO[X] = 0;

        if (AIzq[X] == X && ADer[X] == X) {
            PTR = 0; // lista queda vacía
        } else {
            PTR = ADer[X];
            AIzq[PTR] = AIzq[X];
            ADer[AIzq[X]] = PTR;
        }

        AIzq[X] = 0;
        ADer[X] = 0;

        DISP.ingresarElemento(X);
        return elemento;
    }

    public String toString() {
        String salida = "";
        for (int i = 1; i < TOP; i++) {
            salida += i + " ";
            salida += "[" + INFO[i] + "]";
            salida += "[" + AIzq[i] + "]";
            salida += "[" + ADer[i] + "]";
            if (i == PTR) salida += " <--- PTR";
            salida += "\n";
        }
        salida += "Estado de la pila\n";
        salida += DISP.toString();
        return salida;
    }
}
