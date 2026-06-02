package ListasEncadenadas;

import Pila.Pila;

public class ListaDobleF {
    private int TOP;       // Máximo
    private int INFO[];    // Datos
    private int AIzq[];    // Apuntador izquierda
    private int ADer[];    // Apuntador derecha
    private int PTR;       // Puntero principal
    private Pila DISP;     // Pila de disponibles

    public ListaDobleF() {
        TOP = 20;
        INFO = new int[TOP];
        AIzq = new int[TOP];
        ADer = new int[TOP];
        PTR = 0;
        DISP = new Pila(TOP - 1);
        inicializarPila();
    }

    public ListaDobleF(int max) {
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

        AIzq[X] = AIzq[PTR];   // conecta hacia atrás
        ADer[X] = PTR;         // conecta hacia adelante

        if (PTR != 0) {
            AIzq[PTR] = X;
        }
        PTR = X;
    }

    public int eliminarElemento() {
        int X = PTR;
        int elemento = INFO[X];

        INFO[X] = 0;
        PTR = ADer[X];

        if (PTR != 0) {
            AIzq[PTR] = AIzq[X];
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
