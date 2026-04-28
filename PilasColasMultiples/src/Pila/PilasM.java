package Pila;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Mauricio León B C5G444
 */
public class PilasM {

    private Pila[] pilaM;
    private int nPilas;

    public PilasM() {
        nPilas = 10;
        pilaM = new Pila[nPilas];
    }

    public PilasM(int cPilas) {
        this.nPilas = cPilas;
        pilaM = new Pila[nPilas];
    }

    public void agregarPilaM(int nPilas, int elemento) {
        pilaM[nPilas].ingresarElemento(elemento);
    }

    public int eliminarPilaM(int nPila) {
        return pilaM[nPila].eliminarElemento();
    }

    public void crearPila(int nCola, int nElementos) {
        pilaM[nCola] = new Pila(nElementos);
    }

    public void inicializarPilas(int nElementos) {
        for (int i = 0; i < nPilas; i++) {
            pilaM[i] = new Pila(nElementos);
        }
    }

    @Override
    public String toString() {
        String salida = "\n";
        for (int i = 0; i < nPilas; i++) {
            salida += pilaM[i].toString();
        }
        return salida;
    }

    public String toString(int nPila) {
        if (pilaM[nPila] == null) {
            return "[PILA NO CREADA]";
        }
        return pilaM[nPila].toString();
    }

    public boolean existe(int nPila) {
        return pilaM[nPila] != null;
    }

    public boolean estaLlena(int nPila) {
        return pilaM[nPila] != null && pilaM[nPila].pilaLlena();
    }

    public boolean estaVacia(int nPila) {
        return pilaM[nPila] != null && pilaM[nPila].pilaVacia();
    }

    public int buscarLibre() {
        for (int i = 0; i < nPilas; i++) {
            if (pilaM[i] == null) {
                return i;
            }
        }
        return -1;
    }

    public String listar() {
        String s = "";
        for (int i = 0; i < nPilas; i++) {
            if (pilaM[i] != null) {
                s += i + " | ";
            }
        }
        return s.equals("") ? "(vacío)" : s;
    }

}
