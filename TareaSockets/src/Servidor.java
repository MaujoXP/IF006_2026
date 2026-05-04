
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Meowricio
 */
public class Servidor {

    private static ArrayList<Socket> jugadores = new ArrayList<>();
    private static char[][] tablero = new char[3][3];
    private static int turno = 0; //0 P1
                                  //1 P2

    public static void main(String[] args) throws IOException {
        ServerSocket servidor = new ServerSocket(5000);

        // Esperar jugadores
        while (jugadores.size() < 2) {
            Socket cliente = servidor.accept();
            jugadores.add(cliente);
            PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true);
            salida.println("Conectado como " + (jugadores.size() == 1 ? "X" : "O"));
        }

        // Juego ;p
        while (true) {
            // Llenar tablero
            for (int i = 0; i < tablero.length; i++) {
                for (int j = 0; j < tablero[i].length; j++) {
                    tablero[i][j] = '-';
                }
            }
            turno = 0;
            
            boolean partidaTerminada = false;
            while (!partidaTerminada) {
                Socket jugadorActual = jugadores.get(turno);
                BufferedReader entrada = new BufferedReader(new InputStreamReader(jugadorActual.getInputStream()));
                PrintWriter salidaActual = new PrintWriter(jugadorActual.getOutputStream(), true);

                salidaActual.println("Tu turno (" + (turno == 0 ? "X" : "O") + "). Ingresa fila y columna (Ej: 1,3): ");
                String jugada = entrada.readLine();

                if (jugada == null) {
                    enviarATodos("Jugador " + (turno == 0 ? "X" : "O") + " se desconectó\nFin de la partida");
                    partidaTerminada = true;
                    break;
                }

                if (jugada.trim().isEmpty()) {
                    salidaActual.println("Entrada vacía, ingrese fila,col (Ej: 1,3)");
                    continue;
                }

                try {
                    String[] partes = jugada.split(",");
                    int fila = Integer.parseInt(partes[0]) - 1;
                    int col = Integer.parseInt(partes[1]) - 1;

                    if (fila < 0 || fila >= tablero.length || col < 0 || col >= tablero[fila].length) {
                        salidaActual.println("Coordenadas fuera de rango, intente de nuevo");
                        continue;
                    }

                    if (tablero[fila][col] == '-') {
                        tablero[fila][col] = (turno == 0 ? 'X' : 'O');
                        enviarATodos("Tablero actualizado:\n" + imprimirTablero());

                        if (hayGanador()) {
                            enviarATodos("Ganador: " + (turno == 0 ? "X" : "O"));
                            partidaTerminada = true;
                        } else if (tableroLleno()) {
                            enviarATodos("Empate, tablero lleno.");
                            partidaTerminada = true;
                        } else {
                            turno = 1 - turno;
                        }
                    } else {
                        salidaActual.println("Casilla ocupada, intente otra");
                    }
                } catch (NumberFormatException e) {
                    salidaActual.println("Entrada inválida, use formato 'fila,col' (Rango 1-3)");
                }
            }

            // Empate o ganador
            enviarATodos("¿Desean reiniciar (R) o salir (S)?");
            boolean reiniciar = true;
            for (Socket jugador : jugadores) {
                BufferedReader entrada = new BufferedReader(new InputStreamReader(jugador.getInputStream()));
                String respuesta = entrada.readLine();
                if (respuesta == null || respuesta.equalsIgnoreCase("S")) {
                    reiniciar = false;
                }
            }
            if (!reiniciar) {
                enviarATodos("Fin de servidor. Buena partida");
                break;
            }
        }

    }

    private static void enviarATodos(String mensaje) throws IOException {
        for (Socket jugador : jugadores) {
            PrintWriter salida = new PrintWriter(jugador.getOutputStream(), true);
            salida.println(mensaje);
        }
    }

    private static String imprimirTablero() {
        String salida = "";
        for (char[] fila : tablero) {
            for (char col : fila) {
                salida += "|";
                salida += col;
            }
            salida += "|";
            salida += "\n";
        }
        return salida;
    }

    private static boolean hayGanador() {
        for (int i = 0; i < 3; i++) {
            if (tablero[i][0] != '-' && tablero[i][0] == tablero[i][1] && tablero[i][1] == tablero[i][2]) {
                return true;
            }
            if (tablero[0][i] != '-' && tablero[0][i] == tablero[1][i] && tablero[1][i] == tablero[2][i]) {
                return true;
            }
        }
        if (tablero[0][0] != '-' && tablero[0][0] == tablero[1][1] && tablero[1][1] == tablero[2][2]) {
            return true;
        }
        if (tablero[0][2] != '-' && tablero[0][2] == tablero[1][1] && tablero[1][1] == tablero[2][0]) {
            return true;
        }
        return false;
    }

    private static boolean tableroLleno() {
        for (char[] fila : tablero) {
            for (char col : fila) {
                if (col == '-') {
                    return false;
                }
            }
        }
        return true;
    }

}
