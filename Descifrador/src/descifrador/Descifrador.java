/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package descifrador;

/**
 *
 * @author Gaby
 */
public class Descifrador {

    private char tabla[][] = new char[20][20];
    private String texto;
    private char textoChar[];

    public Descifrador() {
        limpiarTabla();
    }

    public void limpiarTabla() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                tabla[i][j] = 'L';
            }
        }
    }
    
    public void rellenarTabla(char caracter) {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                tabla[i][j] = caracter;
            }
        }
    }
    
    public void rellenanarFila(char caracter, int fila) {
        for (int j = 0; j < 20; j++) {
            tabla[fila][j] = caracter;
        }
    }
    
    public void rellenanarFila(char caracter, int fila, int inicio) {
        for (int j = inicio; j < 20; j++) {
            tabla[fila][j] = caracter;
        }
    }
    
    public void rellenanarFila(char caracter, int fila, int inicio, int fin) {
        for (int j = inicio; j <= fin; j++) {
            tabla[fila][j] = caracter;
        }
    }
    
    public void rellenanarFila(char caracter, int fila, int inicio, int fin, int sec) {
        for (int j = inicio; j <= fin; j += sec) {
            tabla[fila][j] = caracter;
        }
    }
    
    public char[] convertirChar(String texto) {
        this.texto = texto;
        textoChar = this.texto.toCharArray();
        
        return textoChar;
    }
    
    public void rellenanarFilaPalabra(int fila, String palabra) {
        char[] vec = convertirChar(palabra);
        for (int j = 0; j < vec.length; j++) {
            tabla[fila][j] = vec[j];
        }
    }
    
    public void rellenanarFilaPalabra(int fila, String palabra, int sec) {
        int k = 0;
        char[] vec = convertirChar(palabra);
        for (int j = 0; j < vec.length; j += sec) {
            tabla[fila][j] = vec[k++];
        }
    }
    
    public void rellenanarCol(char caracter, int col) {
        for (int j = 0; j < 20; j++) {
            tabla[j][col] = caracter;
        }
    }
    
    public void rellenanarCol(char caracter, int col, int inicio) {
        for (int j = inicio; j < 20; j++) {
            tabla[j][col] = caracter;
        }
    }
    
    public void rellenanarCol(char caracter, int col, int inicio, int fin) {
        for (int j = inicio; j <= fin; j++) {
            tabla[j][col] = caracter;
        }
    }
    
    public void rellenanarCol(char caracter, int col, int inicio, int fin, int sec) {
        for (int j = inicio; j <= fin; j += sec) {
            tabla[j][col] = caracter;
        }
    }
    
    public void rellenanarColPalabra(int col, String palabra) {
        char[] vec = convertirChar(palabra);
        for (int j = 0; j < vec.length; j++) {
            tabla[j][col] = vec[j];
        }
    }
    
    public void rellenanarColPalabra(int col, String palabra, int sec) {
        int k = 0;
        char[] vec = convertirChar(palabra);
        for (int j = 0; j < vec.length; j += sec) {
            tabla[j][col] = vec[k++];
        }
    }
    
    public void rellenarDP(char caracter, int inicio, int fin) {
        for(int i = inicio; i < fin; i++) {
            tabla[i][i] = caracter;
        }
    }
    
    public void rellenarDS(char caracter) {
        int fin = 20;
        for(int i = 0; i < fin; i++) {
            tabla[i][fin - i - 1] = caracter;
        }
    }
    
    public String extraerFila(int fila, int inicio, int fin, int sec) {
        String salida = "";
        for(int i = inicio; i < fin; i += sec) {
            salida += tabla[fila][i];
        }
        
        return salida;
    }

    public String toString() {
        String Sal = "          CONTENEDOR DE LA TABLA\n\n";
        Sal += "   0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 \n";
        for (int i = 0; i < 20; i++) {
            if (i < 10) {
                Sal += "\n" + i + "  ";
            } else {
                Sal += "\n" + i + " ";
            }
            for (int j = 0; j < 20; j++) {
                Sal += tabla[i][j] + " ";
            }
        }
        return Sal;
    }
}
