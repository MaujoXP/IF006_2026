package descifrador;

public class Descifrador {

    private char tabla[][] = new char[20][20];//Prueba
    private String texto;
    private char textoChar[];

    public Descifrador() {
        limpiarTabla();
    }

    public void limpiarTabla() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                tabla[i][j] = 'b';
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

    public void rellenarFila(char caracter, int fila) {
        for (int i = 0; i < 20; i++) {
            tabla[fila][i] = caracter;
        }
    }

    public void rellenarFila(char caracter, int fila, int inicio) {
        for (int i = inicio; i < 20; i++) {
            tabla[fila][i] = caracter;
        }
    }

    public void rellenarFila(char caracter, int fila, int inicio, int fin) {
        for (int i = inicio; i < fin; i++) {
            tabla[fila][i] = caracter;
        }
    }

    public void rellenarFila(char caracter, int fila, int inicio, int fin, int sec) {
        for (int i = inicio; i < fin; i += sec) {
            tabla[fila][i] = caracter;
        }
    }

    public char[] convertirChar(String texto) {
        this.texto = texto;
        textoChar = this.texto.toCharArray();
        return textoChar;
    }

    public void rellenarFilaPalabra(int fila, String palabra) {
        char[] vec = convertirChar(palabra);
        for (int i = 0; i < vec.length; i++) {
            tabla[fila][i] = vec[i];
        }
    }

    public void rellenarFilaPalabra(int fila, String palabra, int sec) {
        int k = 0;
        char[] vec = convertirChar(palabra);
        for (int j = 0; j < vec.length; j += sec) {
            tabla[fila][j] = vec[j];
        }
    }

    public void rellenarColumna(char caracter, int columna) {
        for (int i = 0; i < 20; i++) {
            tabla[i][columna] = caracter;
        }
    }

    public void rellenarColumna(char caracter, int columna, int inicio) {
        for (int i = inicio; i < 20; i++) {
            tabla[i][columna] = caracter;
        }
    }

    public void rellenarColumna(char caracter, int columna, int inicio, int fin) {
        for (int i = inicio; i <= fin; i++) {
            tabla[i][columna] = caracter;
        }
    }

    public void rellenarColumna(char caracter, int columna, int inicio, int fin, int sec) {
        for (int i = inicio; i <= fin; i += sec) {
            tabla[i][columna] = caracter;
        }
    }

    public void rellenarColumnaPalabra(int columna, String palabra) {
        char vec[] = convertirChar(palabra);
        for (int i = 0; i < vec.length; i++) {
            tabla[i][columna] = vec[i];
        }
    }

    public void rellenarColumnaPalabra(int columna, String palabra, int sec) {
        char vec[] = convertirChar(palabra);
        for (int i = 0; i < vec.length; i += sec) {
            tabla[i][columna] = vec[i];
        }
    }

    public void rellenarDP(char caracter, int inicio, int fin) {
        for (int j = inicio; j < fin; j++) {
            tabla[j][j] = caracter;
        }
    }

    public void rellenarDS(char caracter) {
        int fin = 20;
        for (int i = 0; i < fin; i++) {
            tabla[i][fin - i - 1] = caracter;
        }
    }

    public String extraerFila(int fila, int inicio, int fin, int sec) {
        String Sal = "";
        for (int i = inicio; i < fin; i += sec) {
            Sal += tabla[fila][i];
        }
        return Sal;
    }

    public String extraerFila(int fila, int inicio, int fin) {
        String sal = "";
        for (int j = inicio; j <= fin; j++) {
            sal += tabla[fila][j];
        }
        return sal;
    }

    public String extraerFilaInversa(int fila, int inicio, int fin) {
        String sal = "";
        for (int j = fin; j >= inicio; j--) {
            sal += tabla[fila][j];
        }
        return sal;
    }

    public String extraerColumna(int columna, int inicio, int fin) {
        String sal = "";
        for (int i = inicio; i <= fin; i++) {
            sal += tabla[i][columna];
        }
        return sal;
    }

    public String extraerColumnaInversa(int columna, int inicio, int fin) {
        String sal = "";
        for (int i = fin; i >= inicio; i--) {
            sal += tabla[i][columna];
        }
        return sal;
    }

    public String extraerDiagonalDerecha(int inicio, int fin) {
        String sal = "";
        for (int i = inicio; i <= fin; i++) {
            sal += tabla[i][i];
        }
        return sal;
    }

    public String extraerDiagonalDerechainversa(int inicio, int fin) {
        String sal = "";
        for (int i = fin; i >= inicio; i--) {
            sal += tabla[i][i];
        }
        return sal;
    }

    public String extraerDiagonalIzquierda(int inicio, int fin) {
        String sal = "";
        for (int i = 0; i <= fin; i++) {
            sal += tabla[inicio][fin - i - 1];
        }
        return sal;
    }

    public String extraerDiagonalIzquerdainversa(int inicio, int fin) {
        String sal = "";
        for (int i = fin; i >= inicio; i--) {
            sal += tabla[inicio - i - 1][fin];
        }
        return sal;
    }

    @Override
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
