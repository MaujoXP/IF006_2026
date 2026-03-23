/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Gaby
 */
public class JuegoMoverCajas {
    private char tabla[][];
    private int filaTrabajador;
    private int colTrabajador;
    
    public JuegoMoverCajas() {
        tabla = new char[20][20];
        filaTrabajador = 1;
        colTrabajador = 1;
        limpiarTabla();
        //tabla[1][1] = 'M'
    }
    
        public JuegoMoverCajas(int filas, int columnas) {
        tabla = new char[filas][columnas];
        filaTrabajador = 1;
        colTrabajador = 1;
        limpiarTabla();
        //tabla[1][1] = 'M'
    }

    public void limpiarTabla() {
        for (int i = 0; i < tabla.length; i++) {
            for (int j = 0; j < tabla[i].length; j++) {
                tabla[i][j] = 'B';
            }
        }
    }
    
    public void setTrabajador(int fila, int col) {
        tabla[fila][col] = 'M';
        this.filaTrabajador = fila;
        this.colTrabajador = col;
    }
    
    public String toString() {
        String Sal = "          CONTENEDOR DE LA TABLA\n\n";
        Sal += "   0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 \n";
        for (int i = 0; i < tabla.length; i++) {
            if (i < 10) {
                Sal += "\n" + i + "  ";
            } else {
                Sal += "\n" + i + " ";
            }
            for (int j = 0; j < tabla[i].length; j++) {
                Sal += tabla[i][j] + " ";
            }
        }
        Sal += "\n \n TRABAJADOR ESTA EL LA FILA :" + filaTrabajador + " COLUMNA " + colTrabajador;
        return Sal;
    }
}
