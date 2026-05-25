package Arboles;
import Pila.Pila;

public class ArbolesM {

    private int TOP;
    private int[] AI;
    private int[] AD;
    private int[] INFO;
    private int[] cabeza;
    private Pila DISP;
    
    public ArbolesM() {
        
    }
    public ArbolesM(int tamaño) {
        this.TOP = tamaño;
        inicializarPila();
        this.AI = new int[TOP + 1];
        this.AD = new int[TOP + 1];
        this.INFO = new int[TOP + 1];
        cabeza = new int[5];
    }

    public ArbolesM(int tamaño, int nArboles) {
        this.TOP = tamaño;
        inicializarPila();
        this.AI = new int[TOP + 1];
        this.AD = new int[TOP + 1];
        this.INFO = new int[TOP + 1];
        //cabeza = 0;
        cabeza = new int[nArboles];

    }

    public void inicializarPila() {
        DISP = new Pila(TOP);
        for (int i = 1; i < TOP; i++) {
            DISP.ingresarElemento(i);
        }
    }

    public int solicitarNodo() {
        return DISP.eliminarElemento();
    }

    public void desecharNodo(int x) {
        DISP.ingresarElemento(x);
    }

    public void crearArbolIRD(int valor, int nArbol) {
        int x = solicitarNodo();
        this.INFO[x] = valor;
        this.AI[x] = -1 * x;
        this.AD[x] = 0;
        cabeza[nArbol] = x;
    }

    public void agregarIzq(int valor, int nNodo) {
        //int PTR = cabeza;
        int x = solicitarNodo();
        this.INFO[x] = valor;
        this.AI[x] = -1 * nNodo;
        this.AD[x] = -1 * nNodo;
        this.AI[nNodo] = x;
    }

    public void agregarDer(int valor, int nNodo) {
        int x = solicitarNodo();
        this.INFO[x] = valor;
        this.AI[x] = AD[nNodo];
        this.AD[x] = AD[nNodo];
        this.AD[nNodo] = x;
    }

    public void buscarUbicacion(int valor, int nArbol) {        
        int PTR = cabeza[nArbol];
        if (PTR == 0) {
            crearArbolIRD(valor, nArbol);
        } else {
            int U = 0;
            int K = PTR;
            while (PTR > 0) {
                K = PTR;
                if (INFO[PTR] > valor) {
                    PTR = AI[PTR];
                    U = 1;
                } else {
                    PTR = AD[PTR];
                    U = 2;
                }
            }
            if (U == 1) {
                agregarIzq(valor, K);
            } else {
                agregarDer(valor, K);
            }
        }
    }

    public int buscarElemento(int valor, int nArbol) {
        int PTR = cabeza[nArbol];
        int K = PTR;
        boolean encontrado = false;
        while (PTR > 0 && !encontrado) {
            K = PTR;
            if (INFO[PTR] == valor) {
                encontrado = true;
            } else {
                if (INFO[PTR] > valor) {
                    PTR = AI[PTR];
                } else {
                    PTR = AD[PTR];
                }
            }
        }

        if (encontrado) {
            return K;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        String salida = "";
        for (int i = 1; i < TOP; i++) {
            salida += i + " ";
            salida += "[" + AI[i] + "]";
            salida += "[" + INFO[i] + "]";
            salida += "[" + AD[i] + "] ";
            for (int j = 0; j < cabeza.length; j++) {
                if (cabeza[j] == i) {
                    salida += "<== CABEZA " + j;
                }
            }
            salida += "\n";
        }
        salida += "Estado de la pila\n";
        salida += DISP.toString();
        return salida;
    }
    
}