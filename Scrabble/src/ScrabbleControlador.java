/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Meowricio
 */
import java.util.*;
 
public class ScrabbleControlador {

    private Scrabble modelo;
    private ScrabbleVista vista;
 
    public ScrabbleControlador(ScrabbleVista vista) {
        this.vista = vista;
    }
 
    // Inicia una nueva partida con los nombres de jugadores indicados 
    public void iniciarPartida(List<String> jugadores) {
        if (jugadores == null || jugadores.isEmpty()) {
            vista.mostrarError("Debe haber al menos un jugador.");
            return;
        }
        if (jugadores.size() > 4) {
            vista.mostrarError("El juego admite máximo 4 jugadores.");
            return;
        }

        // Limpiar y validar nombres
        List<String> nombresLimpios = new ArrayList<>();
        for (String nombre : jugadores) {
            String n = nombre.trim();
            if (n.isEmpty()) n = "Jugador " + (nombresLimpios.size() + 1);
            nombresLimpios.add(n);
        }

        modelo = new Scrabble(nombresLimpios);
        vista.actualizarTablero(modelo.getTablero(), modelo.getEspeciales());
        vista.actualizarTurno(
            modelo.getNombreJugador(modelo.getTurnoActual()),
            modelo.getFichasJugador(modelo.getTurnoActual()),
            modelo.getPuntuaciones()
        );
        vista.mostrarMensaje("¡Partida iniciada! La primera palabra debe pasar por el centro (H8).");
        vista.mostrarFichasEnSaco(modelo.getFichasEnSaco());
    }
 
    public void procesarJugada(String palabraRaw, String coordRaw, String direccionRaw) {
        if (modelo == null) {
            vista.mostrarError("Primero debe iniciar una partida.");
            return;
        }
        if (modelo.isJuegoTerminado()) {
            vista.mostrarError("El juego ha terminado.");
            return;
        }

        // Limpiar entradas (hileras)
        String palabra    = palabraRaw.trim().toUpperCase();
        String coord      = coordRaw.trim().toUpperCase();
        String direccion  = direccionRaw.trim().toUpperCase();

        // Validar palabra
        if (palabra.isEmpty()) {
            vista.mostrarError("Debe ingresar una palabra.");
            return;
        }
        if (!palabra.matches("[A-ZÁÉÍÓÚÜÑ]+")) {
            vista.mostrarError("La palabra solo puede contener letras.");
            return;
        }

        // Parsear coordenadas
        int[] rc = parsearCoordenada(coord);
        if (rc == null) {
            vista.mostrarError("Coordenada inválida. Use formato como 'H8', '7 7' o '7,7'.");
            return;
        }
        int fila = rc[0];
        int columna = rc[1];

        // Parsear dirección
        boolean horizontal;
        if (direccion.startsWith("H")) {
            horizontal = true;
        } else if (direccion.startsWith("V")) {
            horizontal = false;
        } else {
            vista.mostrarError("Dirección inválida. Use 'H' para horizontal o 'V' para vertical.");
            return;
        }

        // Ejecutar jugada en el modelo
        Scrabble.ResultadoJugada resultado = modelo.colocarPalabra(palabra, fila, columna, horizontal);

        if (resultado.exito) {
            vista.actualizarTablero(modelo.getTablero(), modelo.getEspeciales());
            vista.mostrarMensaje("✔ " + resultado.mensaje);
            actualizarVistaPostJugada();
        } else {
            vista.mostrarError("✘ " + resultado.mensaje);
        }
    }

    // El jugador actual pasa su turno  
    public void procesarPase() {
        if (modelo == null || modelo.isJuegoTerminado()) return;

        Scrabble.ResultadoJugada r = modelo.pasarTurno();
        vista.mostrarMensaje(r.mensaje);
        actualizarVistaPostJugada();
    }
 
    public void procesarCambioFichas(String fichasStr) {
        if (modelo == null || modelo.isJuegoTerminado()) return;

        fichasStr = fichasStr.trim().toUpperCase();
        if (fichasStr.isEmpty()) {
            vista.mostrarError("Debe indicar qué fichas cambiar.");
            return;
        }

        List<Character> fichas = new ArrayList<>();
        for (char c : fichasStr.toCharArray()) fichas.add(c);

        Scrabble.ResultadoJugada r = modelo.cambiarFichas(fichas);
        if (r.exito) {
            vista.mostrarMensaje(r.mensaje);
            actualizarVistaPostJugada();
        } else {
            vista.mostrarError(r.mensaje);
        }
    }

    // Agrega una palabra al diccionario del juego  
    public void agregarAlDiccionario(String palabra) {
        if (modelo == null) return;
        String p = palabra.trim().toUpperCase();
        if (p.isEmpty() || !p.matches("[A-ZÁÉÍÓÚÜÑ]+")) {
            vista.mostrarError("Palabra inválida para agregar al diccionario.");
            return;
        }
        modelo.agregarPalabra(p);
        vista.mostrarMensaje("Palabra '" + p + "' agregada al diccionario.");
    }

    // Verifica si una palabra existe en el diccionario 
    public void verificarPalabra(String palabra) {
        if (modelo == null) return;
        String p = palabra.trim().toUpperCase();
        boolean valida = modelo.esPalabraValida(p);
        vista.mostrarMensaje("'" + p + "' " + (valida ? "✔ es válida" : "✘ no está en el diccionario"));
    }
 
    private int[] parsearCoordenada(String coord) {
        coord = coord.replaceAll("\\s+", "").replace(",", "");

        // Formato numérico puro: "77" o con separador "7 7"
        if (coord.matches("\\d+")) {
            // Solo dígitos, formato "RC" sin separador
            if (coord.length() == 2) {
                int f = coord.charAt(0) - '0';
                int c = coord.charAt(1) - '0';
                if (esValido(f, c)) return new int[]{f, c};
            }
            if (coord.length() == 4) {
                int f = Integer.parseInt(coord.substring(0, 2));
                int c = Integer.parseInt(coord.substring(2, 4));
                if (esValido(f, c)) return new int[]{f, c};
            }
            return null;
        }

        // Formato letra+número o número+letra (estilo Scrabble real: A1 – O15)
        if (coord.matches("[A-O]\\d{1,2}")) {
            int col = coord.charAt(0) - 'A';
            int fila = Integer.parseInt(coord.substring(1)) - 1;
            if (esValido(fila, col)) return new int[]{fila, col};
        }
        if (coord.matches("\\d{1,2}[A-O]")) {
            int fila = Integer.parseInt(coord.substring(0, coord.length()-1)) - 1;
            int col  = coord.charAt(coord.length()-1) - 'A';
            if (esValido(fila, col)) return new int[]{fila, col};
        }

        // Formato "fila columna" separados
        String[] partes = coord.split("[,;\\s]+");
        if (partes.length == 2) {
            try {
                int f = Integer.parseInt(partes[0]);
                int c = Integer.parseInt(partes[1]);
                if (esValido(f, c)) return new int[]{f, c};
            } catch (NumberFormatException ignored) {}
        }

        return null;
    }

    private boolean esValido(int fila, int col) {
        return fila >= 0 && fila < Scrabble.TAMANIO && col >= 0 && col < Scrabble.TAMANIO;
    }
 
    private void actualizarVistaPostJugada() {
        if (modelo.isJuegoTerminado()) {
            mostrarFinDeJuego();
        } else {
            int turno = modelo.getTurnoActual();
            vista.actualizarTurno(
                modelo.getNombreJugador(turno),
                modelo.getFichasJugador(turno),
                modelo.getPuntuaciones()
            );
            vista.mostrarFichasEnSaco(modelo.getFichasEnSaco());
            vista.actualizarHistorial(modelo.getHistorial());
        }
    }

    private void mostrarFinDeJuego() {
        int ganadorIdx = modelo.getGanador();
        String ganador = modelo.getNombreJugador(ganadorIdx);
        int puntosGanador = modelo.getPuntuacion(ganadorIdx);

        StringBuilder sb = new StringBuilder();
        sb.append("══════════════════════════════\n");
        sb.append("        ¡JUEGO TERMINADO!     \n");
        sb.append("══════════════════════════════\n");
        sb.append("  🏆 Ganador: ").append(ganador).append("\n");
        sb.append("══════════════════════════════\n");
        sb.append("       Puntuaciones:\n");
        for (int i = 0; i < modelo.getCantidadJugadores(); i++) {
            sb.append("  ").append(modelo.getNombreJugador(i))
              .append(": ").append(modelo.getPuntuacion(i)).append(" pts\n");
        }
        sb.append("══════════════════════════════");

        vista.mostrarFinDeJuego(sb.toString(), ganador, modelo.getPuntuaciones());
        vista.actualizarHistorial(modelo.getHistorial());
    }
 
    public Scrabble getModelo() { return modelo; }

    // Representación en texto del tablero (para depuración o vista de texto)  
    public String tableroATexto() {
        if (modelo == null) return "";
        StringBuilder sb = new StringBuilder();
        char[][] t = modelo.getTablero();
        sb.append("   A B C D E F G H I J K L M N O\n");
        for (int r = 0; r < Scrabble.TAMANIO; r++) {
            sb.append(String.format("%2d ", r + 1));
            for (int c = 0; c < Scrabble.TAMANIO; c++) {
                char ch = t[r][c];
                sb.append(ch == ' ' ? "." : ch).append(' ');
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // Retorna las fichas del jugador actual como String 
    public String fichasActualesATexto() {
        if (modelo == null) return "";
        List<Character> fichas = modelo.getFichasJugador(modelo.getTurnoActual());
        StringBuilder sb = new StringBuilder("Fichas: [");
        for (int i = 0; i < fichas.size(); i++) {
            sb.append(fichas.get(i));
            if (i < fichas.size() - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    // Retorna el marcador actual como String formateado
    public String marcadorATexto() {
        if (modelo == null) return "";
        StringBuilder sb = new StringBuilder("Marcador | ");
        for (int i = 0; i < modelo.getCantidadJugadores(); i++) {
            sb.append(modelo.getNombreJugador(i))
              .append(": ").append(modelo.getPuntuacion(i));
            if (i < modelo.getCantidadJugadores() - 1) sb.append(" | ");
        }
        return sb.toString();
    }
}


