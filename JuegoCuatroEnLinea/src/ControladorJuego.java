/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Meowricio
 */
public class ControladorJuego {
    private JuegoCuatroEnLinea juego;

    public ControladorJuego(JuegoCuatroEnLinea juego) {
        this.juego = juego;
    }

    public boolean realizarJugada(int columna) {
        boolean exito = juego.jugada(columna);
        return exito;
    }

    public boolean verificarGanador(int fila, int columna) {
        return juego.juegoGanado(fila, columna);
    }

    public boolean verificarEmpate() {
        return juego.juegoPerdido();
    }

    public void cambiarTurno() {
        juego.cambiarTurno();
    }

    public char getJugadorActual() {
        return juego.getJugadorActual();
    }

    public char[][] getTablero() {
        return juego.getTablero();
    }

    public void reiniciarJuego() {
        juego.reiniciar();
    }
}

