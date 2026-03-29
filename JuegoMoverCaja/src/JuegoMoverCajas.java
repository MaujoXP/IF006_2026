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
    private char debajoTrabajador = 'L';

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

    public boolean puedeMoverArriba() {
        return tabla[filaTrabajador - 1][colTrabajador] == 'L'
                || tabla[filaTrabajador - 1][colTrabajador] == 'P';
    }

    public boolean puedeMoverAbajo() {
        return tabla[filaTrabajador + 1][colTrabajador] == 'L'
                || tabla[filaTrabajador + 1][colTrabajador] == 'P';
    }

    public boolean puedeMoverIzq() {
        return tabla[filaTrabajador][colTrabajador - 1] == 'L'
                || tabla[filaTrabajador][colTrabajador - 1] == 'P';
    }

    public boolean puedeMoverDer() {
        return tabla[filaTrabajador][colTrabajador + 1] == 'L'
                || tabla[filaTrabajador][colTrabajador + 1] == 'P';
    }

    public void moverArriba() {
        if (puedeMoverArriba()) {
            tabla[filaTrabajador][colTrabajador] = debajoTrabajador;
            filaTrabajador--;
            debajoTrabajador = tabla[filaTrabajador][colTrabajador];
            tabla[filaTrabajador][colTrabajador] = 'M';
        }
    }

    public void moverAbajo() {
        if (puedeMoverAbajo()) {
            tabla[filaTrabajador][colTrabajador] = debajoTrabajador;
            filaTrabajador++;
            debajoTrabajador = tabla[filaTrabajador][colTrabajador];
            tabla[filaTrabajador][colTrabajador] = 'M';
        }
    }

    public void moverIzq() {
        if (puedeMoverIzq()) {
            tabla[filaTrabajador][colTrabajador] = debajoTrabajador;
            colTrabajador--;
            debajoTrabajador = tabla[filaTrabajador][colTrabajador];
            tabla[filaTrabajador][colTrabajador] = 'M';
        }
    }

    public void moverDer() {
        if (puedeMoverDer()) {
            tabla[filaTrabajador][colTrabajador] = debajoTrabajador;
            colTrabajador++;
            debajoTrabajador = tabla[filaTrabajador][colTrabajador];
            tabla[filaTrabajador][colTrabajador] = 'M';
        }
    }

    public boolean seExcedeLimiteSup() {
        return (filaTrabajador - 2) < 0;
    }

    public boolean seExcedeLimiteInf() {
        return (filaTrabajador + 2) > tabla.length;
    }

    public boolean seExcedeLimiteIzq() {
        return (colTrabajador - 2) < 0;
    }

    public boolean seExcedeLimiteDer() {
        return (filaTrabajador + 2) > tabla[0].length;
    }

    public boolean hayCajaArriba() {
        return tabla[filaTrabajador - 1][colTrabajador] == 'C'
                || tabla[filaTrabajador - 1][colTrabajador] == 'R';
    }

    public boolean hayCajaAbajo() {
        return tabla[filaTrabajador + 1][colTrabajador] == 'C'
                || tabla[filaTrabajador + 1][colTrabajador] == 'R';
    }

    public boolean hayCajaIzq() {
        return tabla[filaTrabajador][colTrabajador - 1] == 'C'
                || tabla[filaTrabajador][colTrabajador - 1] == 'R';
    }

    public boolean hayCajaDer() {
        return tabla[filaTrabajador][colTrabajador + 1] == 'C'
                || tabla[filaTrabajador][colTrabajador + 1] == 'R';
    }

    public boolean hayEspacioLibreSup() {
        if (!seExcedeLimiteSup() && hayCajaArriba()) {
            char destino = tabla[filaTrabajador - 2][colTrabajador];
            return destino == 'L' || destino == 'P';
        }
        return false;
    }

    public boolean hayEspacioLibreInf() {
        if (!seExcedeLimiteInf() && hayCajaAbajo()) {
            char destino = tabla[filaTrabajador + 2][colTrabajador];
            return destino == 'L' || destino == 'P';
        }
        return false;
    }

    public boolean hayEspacioLibreIzq() {
        if (!seExcedeLimiteIzq() && hayCajaIzq()) {
            char destino = tabla[filaTrabajador][colTrabajador - 2];
            return destino == 'L' || destino == 'P';
        }
        return false;
    }

    public boolean hayEspacioLibreDer() {
        if (!seExcedeLimiteDer() && hayCajaDer()) {
            char destino = tabla[filaTrabajador][colTrabajador + 2];
            return destino == 'L' || destino == 'P';
        }
        return false;
    }

    public void empujarArriba() {
        if (hayEspacioLibreSup()) {
            int filaCajaOrigen = filaTrabajador - 1;
            int filaCajaDestino = filaTrabajador - 2;

            // Mover la caja primero
            if (tabla[filaCajaOrigen][colTrabajador] == 'R') {
                tabla[filaCajaOrigen][colTrabajador] = 'P';
            } else {
                tabla[filaCajaOrigen][colTrabajador] = 'L';
            }

            if (tabla[filaCajaDestino][colTrabajador] == 'P') {
                tabla[filaCajaDestino][colTrabajador] = 'R';
            } else {
                tabla[filaCajaDestino][colTrabajador] = 'C';
            }

            // Restaurar casilla del trabajador
            tabla[filaTrabajador][colTrabajador] = debajoTrabajador;

            // Mover trabajador
            filaTrabajador--;
            debajoTrabajador = tabla[filaTrabajador][colTrabajador];
            tabla[filaTrabajador][colTrabajador] = 'M';
        }
    }

    public void empujarAbajo() {
        if (hayEspacioLibreInf()) {
            int filaCajaOrigen = filaTrabajador + 1;
            int filaCajaDestino = filaTrabajador + 2;

            // Mover la caja primero
            if (tabla[filaCajaOrigen][colTrabajador] == 'R') {
                tabla[filaCajaOrigen][colTrabajador] = 'P';
            } else {
                tabla[filaCajaOrigen][colTrabajador] = 'L';
            }

            if (tabla[filaCajaDestino][colTrabajador] == 'P') {
                tabla[filaCajaDestino][colTrabajador] = 'R';
            } else {
                tabla[filaCajaDestino][colTrabajador] = 'C';
            }

            // Restaurar casilla del trabajador
            tabla[filaTrabajador][colTrabajador] = debajoTrabajador;

            // Mover trabajador
            filaTrabajador++;
            debajoTrabajador = tabla[filaTrabajador][colTrabajador];
            tabla[filaTrabajador][colTrabajador] = 'M';
        }
    }

    public void empujarIzq() {
        if (hayEspacioLibreIzq()) {
            int colCajaOrigen = colTrabajador - 1;
            int colCajaDestino = colTrabajador - 2;

            // Mover la caja primero
            if (tabla[filaTrabajador][colCajaOrigen] == 'R') {
                tabla[filaTrabajador][colCajaOrigen] = 'P';
            } else {
                tabla[filaTrabajador][colCajaOrigen] = 'L';
            }

            if (tabla[filaTrabajador][colCajaDestino] == 'P') {
                tabla[filaTrabajador][colCajaDestino] = 'R';
            } else {
                tabla[filaTrabajador][colCajaDestino] = 'C';
            }

            // Restaurar casilla del trabajador
            tabla[filaTrabajador][colTrabajador] = debajoTrabajador;

            // Mover trabajador
            colTrabajador--;
            debajoTrabajador = tabla[filaTrabajador][colTrabajador];
            tabla[filaTrabajador][colTrabajador] = 'M';
        }
    }

    public void empujarDer() {
        if (hayEspacioLibreDer()) {
            int colCajaOrigen = colTrabajador + 1;
            int colCajaDestino = colTrabajador + 2;

            // Mover la caja primero
            if (tabla[filaTrabajador][colCajaOrigen] == 'R') {
                tabla[filaTrabajador][colCajaOrigen] = 'P';
            } else {
                tabla[filaTrabajador][colCajaOrigen] = 'L';
            }

            if (tabla[filaTrabajador][colCajaDestino] == 'P') {
                tabla[filaTrabajador][colCajaDestino] = 'R';
            } else {
                tabla[filaTrabajador][colCajaDestino] = 'C';
            }

            // Restaurar casilla del trabajador
            tabla[filaTrabajador][colTrabajador] = debajoTrabajador;

            // Mover trabajador
            colTrabajador++;
            debajoTrabajador = tabla[filaTrabajador][colTrabajador];
            tabla[filaTrabajador][colTrabajador] = 'M';
        }
    }

    public boolean hayCajas() {
        for (int i = 0; i < tabla.length; i++) {
            for (int j = 0; j < tabla[i].length; j++) {
                if (tabla[i][j] == 'C') {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean juegoGanado() {
        return !hayCajas();
    }

    public boolean cajaEnPunto() {
        return tabla[filaTrabajador][colTrabajador] == 'C';
    }

    public void setValorTabla(int fila, int col, char caracter) {
        this.tabla[fila][col] = caracter;
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

    public void cargarJuego1() {
        setTrabajador(4, 4);

        setValorTabla(0, 0, 'N');
        setValorTabla(0, 1, 'N');
        setValorTabla(0, 2, 'B');
        setValorTabla(0, 3, 'B');
        setValorTabla(0, 4, 'B');
        setValorTabla(0, 5, 'N');
        setValorTabla(0, 6, 'N');
        setValorTabla(0, 7, 'N');

        setValorTabla(1, 0, 'N');
        setValorTabla(1, 1, 'N');
        setValorTabla(1, 2, 'B');
        setValorTabla(1, 3, 'P');
        setValorTabla(1, 4, 'B');
        setValorTabla(1, 5, 'N');
        setValorTabla(1, 6, 'N');
        setValorTabla(1, 7, 'N');

        setValorTabla(2, 0, 'N');
        setValorTabla(2, 1, 'N');
        setValorTabla(2, 2, 'B');
        setValorTabla(2, 3, 'L');
        setValorTabla(2, 4, 'B');
        setValorTabla(2, 5, 'B');
        setValorTabla(2, 6, 'B');
        setValorTabla(2, 7, 'B');

        setValorTabla(3, 0, 'B');
        setValorTabla(3, 1, 'B');
        setValorTabla(3, 2, 'B');
        setValorTabla(3, 3, 'C');
        setValorTabla(3, 4, 'L');
        setValorTabla(3, 5, 'C');
        setValorTabla(3, 6, 'P');
        setValorTabla(3, 7, 'B');

        setValorTabla(4, 0, 'B');
        setValorTabla(4, 1, 'P');
        setValorTabla(4, 2, 'L');
        setValorTabla(4, 3, 'C');
        setValorTabla(4, 5, 'B');
        setValorTabla(4, 6, 'B');
        setValorTabla(4, 7, 'B');

        setValorTabla(5, 0, 'B');
        setValorTabla(5, 1, 'B');
        setValorTabla(5, 2, 'B');
        setValorTabla(5, 3, 'B');
        setValorTabla(5, 4, 'C');
        setValorTabla(5, 5, 'B');
        setValorTabla(5, 6, 'N');
        setValorTabla(5, 7, 'N');

        setValorTabla(6, 0, 'N');
        setValorTabla(6, 1, 'N');
        setValorTabla(6, 2, 'B');
        setValorTabla(6, 3, 'B');
        setValorTabla(6, 4, 'P');
        setValorTabla(6, 5, 'B');
        setValorTabla(6, 6, 'N');
        setValorTabla(6, 7, 'N');

        setValorTabla(7, 0, 'N');
        setValorTabla(7, 1, 'N');
        setValorTabla(7, 2, 'N');
        setValorTabla(7, 3, 'B');
        setValorTabla(7, 4, 'B');
        setValorTabla(7, 5, 'B');
        setValorTabla(7, 6, 'N');
        setValorTabla(7, 7, 'N');
    }

    public void cargarJuego2() {
        setTrabajador(1, 1);

        setValorTabla(0, 0, 'B');
        setValorTabla(0, 1, 'B');
        setValorTabla(0, 2, 'B');
        setValorTabla(0, 3, 'B');
        setValorTabla(0, 4, 'B');
        setValorTabla(0, 5, 'N');
        setValorTabla(0, 6, 'N');
        setValorTabla(0, 7, 'N');
        setValorTabla(0, 8, 'N');

        setValorTabla(1, 0, 'B');
        setValorTabla(1, 2, 'L');
        setValorTabla(1, 3, 'L');
        setValorTabla(1, 4, 'B');
        setValorTabla(1, 5, 'N');
        setValorTabla(1, 6, 'N');
        setValorTabla(1, 7, 'N');
        setValorTabla(1, 8, 'N');

        setValorTabla(2, 0, 'B');
        setValorTabla(2, 1, 'L');
        setValorTabla(2, 2, 'C');
        setValorTabla(2, 3, 'C');
        setValorTabla(2, 4, 'B');
        setValorTabla(2, 5, 'N');
        setValorTabla(2, 6, 'B');
        setValorTabla(2, 7, 'B');
        setValorTabla(2, 8, 'B');

        setValorTabla(3, 0, 'B');
        setValorTabla(3, 1, 'L');
        setValorTabla(3, 2, 'C');
        setValorTabla(3, 3, 'L');
        setValorTabla(3, 4, 'B');
        setValorTabla(3, 5, 'N');
        setValorTabla(3, 6, 'B');
        setValorTabla(3, 7, 'P');
        setValorTabla(3, 8, 'B');

        setValorTabla(4, 0, 'B');
        setValorTabla(4, 1, 'B');
        setValorTabla(4, 2, 'B');
        setValorTabla(4, 3, 'L');
        setValorTabla(4, 4, 'B');
        setValorTabla(4, 5, 'B');
        setValorTabla(4, 6, 'B');
        setValorTabla(4, 7, 'P');
        setValorTabla(4, 8, 'B');

        setValorTabla(5, 0, 'N');
        setValorTabla(5, 1, 'B');
        setValorTabla(5, 2, 'B');
        setValorTabla(5, 3, 'L');
        setValorTabla(5, 4, 'L');
        setValorTabla(5, 5, 'L');
        setValorTabla(5, 6, 'L');
        setValorTabla(5, 7, 'P');
        setValorTabla(5, 8, 'B');

        setValorTabla(6, 0, 'N');
        setValorTabla(6, 1, 'B');
        setValorTabla(6, 2, 'L');
        setValorTabla(6, 3, 'L');
        setValorTabla(6, 4, 'L');
        setValorTabla(6, 5, 'B');
        setValorTabla(6, 6, 'L');
        setValorTabla(6, 7, 'L');
        setValorTabla(6, 8, 'B');

        setValorTabla(7, 0, 'N');
        setValorTabla(7, 1, 'B');
        setValorTabla(7, 2, 'L');
        setValorTabla(7, 3, 'L');
        setValorTabla(7, 4, 'L');
        setValorTabla(7, 5, 'B');
        setValorTabla(7, 6, 'B');
        setValorTabla(7, 7, 'B');
        setValorTabla(7, 8, 'B');

        setValorTabla(8, 0, 'N');
        setValorTabla(8, 1, 'B');
        setValorTabla(8, 2, 'B');
        setValorTabla(8, 3, 'B');
        setValorTabla(8, 4, 'B');
        setValorTabla(8, 5, 'B');
        setValorTabla(8, 6, 'N');
        setValorTabla(8, 7, 'N');
        setValorTabla(8, 8, 'N');
    }

    public void cargarJuego3() {

        setTrabajador(3, 2);

        setValorTabla(0, 0, 'N');
        setValorTabla(0, 8, 'N');
        setValorTabla(0, 9, 'N');
        setValorTabla(1, 0, 'N');
        setValorTabla(1, 2, 'L');
        setValorTabla(1, 3, 'L');
        setValorTabla(1, 4, 'L');
        setValorTabla(1, 5, 'L');
        setValorTabla(1, 6, 'L');
        setValorTabla(2, 2, 'C');
        setValorTabla(2, 6, 'L');
        setValorTabla(2, 7, 'L');
        setValorTabla(2, 8, 'L');
        setValorTabla(3, 1, 'L');
        setValorTabla(3, 3, 'L');
        setValorTabla(3, 4, 'C');
        setValorTabla(3, 5, 'L');
        setValorTabla(3, 6, 'L');
        setValorTabla(3, 7, 'C');
        setValorTabla(3, 8, 'L');
        setValorTabla(4, 1, 'L');
        setValorTabla(4, 2, 'P');
        setValorTabla(4, 3, 'P');
        setValorTabla(4, 5, 'L');
        setValorTabla(4, 6, 'C');
        setValorTabla(4, 7, 'L');
        setValorTabla(5, 2, 'P');
        setValorTabla(5, 3, 'P');
        setValorTabla(5, 5, 'L');
        setValorTabla(5, 6, 'L');
        setValorTabla(5, 7, 'L');
        setValorTabla(5, 9, 'N');
        setValorTabla(6, 0, 'N');
        setValorTabla(6, 9, 'N');
    }

    public void solucionJuego1() {
        empujarAbajo();

        moverArriba();

        empujarIzq();
        empujarIzq();

        moverDer();

        empujarArriba();
        empujarArriba();

        moverAbajo();

        moverDer();

        empujarDer();
    }

    public void solucionJuego2() {
        moverDer();
        moverDer();

        empujarAbajo();
        empujarAbajo();
        empujarAbajo();
        empujarAbajo();

        moverDer();

        moverAbajo();
        moverAbajo();

        moverIzq();
        moverIzq();

        moverArriba();

        empujarDer();

        moverAbajo();

        moverDer();

        empujarArriba();

        moverIzq();

        moverArriba();

        empujarDer();
        empujarDer();
        empujarDer();

        moverAbajo();

        moverDer();

        empujarArriba();
        empujarArriba();

        moverAbajo();

        moverIzq();
        moverIzq();
        moverIzq();
        moverIzq();

        moverArriba();
        moverArriba();
        moverArriba();
        moverArriba();

        moverIzq();
        moverIzq();

        moverAbajo();

        empujarDer();

        moverArriba();

        moverDer();

        empujarAbajo();
        empujarAbajo();
        empujarAbajo();

        moverDer();

        moverAbajo();
        moverAbajo();

        moverIzq();
        moverIzq();

        moverArriba();

        empujarDer();

        moverAbajo();

        moverDer();

        empujarArriba();

        moverIzq();

        moverArriba();

        empujarDer();
        empujarDer();
        empujarDer();

    }

    public void solucionJuego3() {
        moverDer();
        
        empujarDer();
        empujarDer();
        
        moverAbajo();
        moverAbajo();
        
        moverDer();
        moverDer();
        
        moverArriba();
        
        empujarIzq();
        
        moverDer();
        
        empujarArriba();
        
        empujarIzq();
        empujarIzq();
        empujarIzq();
        
        moverDer();
        moverDer();
        
        moverArriba();
        moverArriba();
        
        moverIzq();
        moverIzq();
        moverIzq();
        moverIzq();
        
        empujarAbajo();
        empujarAbajo();
        empujarAbajo();
        
        moverArriba();
        moverArriba();
        moverArriba();
        
        moverDer();
        moverDer();
        moverDer();
        moverDer();
        
        moverAbajo();
        moverAbajo();
        
        moverIzq();
        moverIzq();
        
        empujarIzq();
        
        moverDer();
        moverDer();
        moverDer();
        
        moverArriba();
        moverArriba();
        
        moverIzq();
        moverIzq();
        moverIzq();
        moverIzq();
        
        moverAbajo();
        
        empujarAbajo();
        
        moverIzq();
        
        moverAbajo();
        
        empujarDer();
        
        moverDer();
        
        moverArriba();
        
        moverDer();
        
        empujarAbajo();
        
        moverArriba();
        
        moverDer();
        moverDer();
        moverDer();
        
        moverAbajo();
        moverAbajo();
        
        moverIzq();
        
        empujarArriba();
        
        moverDer();
        
        moverArriba();
        
        empujarIzq();
        empujarIzq();
        empujarIzq();
        
        moverDer();
        moverDer();
        moverDer();
        
        moverArriba();
        moverArriba();
        
        moverIzq();
        moverIzq();
        moverIzq();
        moverIzq();
        
        moverAbajo();
        
        empujarAbajo();
        
        moverArriba();
        moverArriba();
        
        moverDer();
        moverDer();
        moverDer();
        moverDer();
        
        moverAbajo();
        moverAbajo();
        
        moverIzq();
        moverIzq();
        moverIzq();
        moverIzq();
        moverIzq();
        
        moverAbajo();
        
        empujarDer();
        
        moverArriba();
        
        moverDer();
        moverDer();
        moverDer();
        moverDer();
        moverDer();
        moverDer();
        
        moverArriba();
        
        empujarIzq();
        
        moverAbajo();
        
        moverIzq();
        moverIzq();
        moverIzq();
        moverIzq();
        moverIzq();
        
        moverArriba();
        moverArriba();
        
        moverDer();
        moverDer();
        moverDer();
        moverDer();
        
        empujarAbajo();
        
        moverDer();
        
        moverAbajo();
        
        empujarIzq();
        empujarIzq();
        empujarIzq();
        empujarIzq();
        
        moverDer();
        moverDer();
        moverDer();
        
        moverArriba();
        moverArriba();
        
        moverIzq();
        moverIzq();
        moverIzq();
        moverIzq();
        
        moverAbajo();
        
        empujarAbajo();
    }

}
