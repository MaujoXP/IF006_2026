/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Meowricio
 */
public class JuegoCuatroEnLinea {
    private final int FILAS = 6;
    private final int COLUMNAS = 7;
    private final char[][] tablero;
    private char jugadorActual;

    public JuegoCuatroEnLinea() {
        tablero = new char[FILAS][COLUMNAS];
        reiniciar();
    }

    public void reiniciar() {
        for (int f = 0; f < FILAS; f++) {
            for (int c = 0; c < COLUMNAS; c++) {
                tablero[f][c] = '-';
            }
        }
        jugadorActual = 'X';
    }

    public char getJugadorActual() {
        return jugadorActual;
    }

    public char[][] getTablero() {
        return tablero;
    }

    public boolean jugada(int columna) {
        for (int fila = FILAS - 1; fila >= 0; fila--) {
            if (tablero[fila][columna] == '-') {
                tablero[fila][columna] = jugadorActual;
                return true;
            }
        }
        return false; // columna llena
    }

    public boolean CKjugada(int fila, int columna) {
        return fila >= 0 && fila < FILAS && columna >= 0 && columna < COLUMNAS && tablero[fila][columna] == '-';
    }

    public boolean juegoGanado(int fila, int columna) {
        char simbolo = tablero[fila][columna];
        return contar(fila, columna, 1, 0, simbolo) + contar(fila, columna, -1, 0, simbolo) >= 3 ||
               contar(fila, columna, 0, 1, simbolo) + contar(fila, columna, 0, -1, simbolo) >= 3 ||
               contar(fila, columna, 1, 1, simbolo) + contar(fila, columna, -1, -1, simbolo) >= 3 ||
               contar(fila, columna, 1, -1, simbolo) + contar(fila, columna, -1, 1, simbolo) >= 3;
    }

    public boolean juegoPerdido() {
        for (int f = 0; f < FILAS; f++) {
            for (int c = 0; c < COLUMNAS; c++) {
                if (tablero[f][c] == '-') return false;
            }
        }
        return true;
    }

    private int contar(int fila, int columna, int df, int dc, char simbolo) {
        int count = 0;
        int f = fila + df, c = columna + dc;
        while (f >= 0 && f < FILAS && c >= 0 && c < COLUMNAS && tablero[f][c] == simbolo) {
            count++;
            f += df;
            c += dc;
        }
        return count;
    }

    public void cambiarTurno() {
        jugadorActual = (jugadorActual == 'X') ? 'O' : 'X';
    }
}

