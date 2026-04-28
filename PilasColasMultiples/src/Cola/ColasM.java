package Cola;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Mauricio León B C5G444
 */
public class ColasM {

    private Cola[] colaM;
    private int nColas;

    public ColasM() {
        nColas = 10;
        colaM = new Cola[nColas];
    }

    public ColasM(int cColas) {
        this.nColas = cColas;
        colaM = new Cola[nColas];
    }

    public void agregarColaM(int nCola, int elemento) {
        colaM[nCola].agregarElemento(elemento);
    }

    public int eliminarColaM(int nCola) {
        return colaM[nCola].eliminarElemento();
    }

    public void crearCola(int nCola, int nElementos) {
        colaM[nCola] = new Cola(nElementos);
    }

    public void inicializarColas(int nElementos) {
        for (int i = 0; i < nColas; i++) {
            colaM[i] = new Cola(nElementos);
        }
    }

    @Override
    public String toString() {
        String salida = "\n";
        for (int i = 0; i < nColas; i++) {
            salida += colaM[i].toString();
        }
        return salida;
    }

    public String toString(int nCola) {
        if (colaM[nCola] == null) {
            return "[COLA NO CREADA]";
        }
        return colaM[nCola].toString();
    }

    public boolean existe(int nCola) {
        return colaM[nCola] != null;
    }

    public boolean estaLlena(int nCola) {
        return colaM[nCola] != null && colaM[nCola].colaLlena();
    }

    public boolean estaVacia(int nCola) {
        return colaM[nCola] != null && colaM[nCola].colaVacia();
    }

    public int buscarLibre() {
        for (int i = 0; i < nColas; i++) {
            if (colaM[i] == null) {
                return i;
            }
        }
        return -1;
    }

    public String listar() {
        String s = "";
        for (int i = 0; i < nColas; i++) {
            if (colaM[i] != null) {
                s += i + " | ";
            }
        }
        return s.equals("") ? "(vacío)" : s;
    }
}
