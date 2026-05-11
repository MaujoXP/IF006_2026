/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Meowricio
 */
import java.util.*;

public class Scrabble {

    public static final int TAMANIO = 15;
    public static final int FICHAS_POR_JUGADOR = 7;

    public enum TipoCasilla {
        NORMAL, DOBLE_LETRA, TRIPLE_LETRA, DOBLE_PALABRA, TRIPLE_PALABRA, CENTRO
    }

    // Tablero
    private char[][] tablero;
    private TipoCasilla[][] especiales;

    // Jugadores
    private List<String> nombresJugadores;
    private List<List<Character>> fichasJugadores;
    private List<Integer> puntuaciones;
    private int turnoActual;

    // Fichas
    private List<Character> saco;

    private Set<String> diccionario;

    // Valores de letras en español
    private static final Map<Character, Integer> VALORES = new HashMap<>();

    static {
        // 1 punto
        for (char c : "AEIOUSLNTRS".toCharArray()) {
            VALORES.put(c, 1);
        }
        // 2 puntos
        for (char c : "DLNR".toCharArray()) {
            VALORES.put(c, 2);
        }
        // 3 puntos
        for (char c : "BCMP".toCharArray()) {
            VALORES.put(c, 3);
        }
        // 4 puntos
        for (char c : "GFV".toCharArray()) {
            VALORES.put(c, 4);
        }
        // 5 puntos
        for (char c : "HY".toCharArray()) {
            VALORES.put(c, 5);
        }
        // 8 puntos
        for (char c : "KQWX".toCharArray()) {
            VALORES.put(c, 8);
        }
        // 10 puntos
        for (char c : "JZÑ".toCharArray()) {
            VALORES.put(c, 10);
        }
        // Comodín
        VALORES.put(' ', 0);
    }

    // Distribución de fichas (letra -> cantidad)
    private static final Map<Character, Integer> DISTRIBUCION = new LinkedHashMap<>();

    static {
        DISTRIBUCION.put('A', 12);
        DISTRIBUCION.put('B', 2);
        DISTRIBUCION.put('C', 4);
        DISTRIBUCION.put('D', 5);
        DISTRIBUCION.put('E', 12);
        DISTRIBUCION.put('F', 1);
        DISTRIBUCION.put('G', 2);
        DISTRIBUCION.put('H', 2);
        DISTRIBUCION.put('I', 6);
        DISTRIBUCION.put('J', 1);
        DISTRIBUCION.put('K', 1);
        DISTRIBUCION.put('L', 4);
        DISTRIBUCION.put('M', 2);
        DISTRIBUCION.put('N', 5);
        DISTRIBUCION.put('Ñ', 1);
        DISTRIBUCION.put('O', 9);
        DISTRIBUCION.put('P', 2);
        DISTRIBUCION.put('Q', 1);
        DISTRIBUCION.put('R', 5);
        DISTRIBUCION.put('S', 6);
        DISTRIBUCION.put('T', 4);
        DISTRIBUCION.put('U', 5);
        DISTRIBUCION.put('V', 1);
        DISTRIBUCION.put('W', 1);
        DISTRIBUCION.put('X', 1);
        DISTRIBUCION.put('Y', 1);
        DISTRIBUCION.put('Z', 1);
        DISTRIBUCION.put(' ', 2);  // comodines
    }

    // Historial de jugadas
    private List<String> historial;

    // Estado de la partida
    private boolean primeraJugada;
    private boolean juegoTerminado;
    private int pasesConsecutivos;

    public Scrabble(List<String> jugadores) {
        this.nombresJugadores = new ArrayList<>(jugadores);
        this.tablero = new char[TAMANIO][TAMANIO];
        this.especiales = new TipoCasilla[TAMANIO][TAMANIO];
        this.fichasJugadores = new ArrayList<>();
        this.puntuaciones = new ArrayList<>();
        this.historial = new ArrayList<>();
        this.primeraJugada = true;
        this.juegoTerminado = false;
        this.pasesConsecutivos = 0;

        // Tablero vacío
        for (char[] fila : tablero) {
            Arrays.fill(fila, ' ');
        }

        inicializarCasillasEspeciales();
        inicializarDiccionario();
        inicializarSaco();
        inicializarJugadores();
    }

    private void inicializarCasillasEspeciales() {
        for (TipoCasilla[] fila : especiales) {
            Arrays.fill(fila, TipoCasilla.NORMAL);
        }

        especiales[7][7] = TipoCasilla.CENTRO;

        // Triple Palabra (TW)
        int[][] tw = {{0, 0}, {0, 7}, {0, 14}, {7, 0}, {7, 14}, {14, 0}, {14, 7}, {14, 14}};
        for (int[] p : tw) {
            especiales[p[0]][p[1]] = TipoCasilla.TRIPLE_PALABRA;
        }

        // Doble Palabra (DW)
        int[][] dw = {
            {1, 1}, {2, 2}, {3, 3}, {4, 4}, {10, 10}, {11, 11}, {12, 12}, {13, 13},
            {1, 13}, {2, 12}, {3, 11}, {4, 10}, {10, 4}, {11, 3}, {12, 2}, {13, 1}
        };
        for (int[] p : dw) {
            especiales[p[0]][p[1]] = TipoCasilla.DOBLE_PALABRA;
        }

        // Triple Letra (TL)
        int[][] tl = {
            {1, 5}, {1, 9}, {5, 1}, {5, 5}, {5, 9}, {5, 13},
            {9, 1}, {9, 5}, {9, 9}, {9, 13}, {13, 5}, {13, 9}
        };
        for (int[] p : tl) {
            especiales[p[0]][p[1]] = TipoCasilla.TRIPLE_LETRA;
        }

        // Doble Letra (DL)
        int[][] dl = {
            {0, 3}, {0, 11}, {2, 6}, {2, 8}, {3, 0}, {3, 7}, {3, 14},
            {6, 2}, {6, 6}, {6, 8}, {6, 12}, {7, 3}, {7, 11},
            {8, 2}, {8, 6}, {8, 8}, {8, 12}, {11, 0}, {11, 7}, {11, 14},
            {12, 6}, {12, 8}, {14, 3}, {14, 11}
        };
        for (int[] p : dl) {
            especiales[p[0]][p[1]] = TipoCasilla.DOBLE_LETRA;
        }
    }

    // Diccionario básico de palabras válidas en español
    private void inicializarDiccionario() {
        diccionario = new HashSet<>(Arrays.asList(
                "AMOR", "CASA", "MESA", "SILLA", "LIBRO", "PAPEL", "AGUA", "FUEGO", "TIERRA", "AIRE",
                "SOL", "LUNA", "MAR", "RIO", "LAGO", "BOSQUE", "FLOR", "ARBOL", "PIEDRA", "CIELO",
                "VIDA", "MUERTE", "PAZ", "GUERRA", "JUEGO", "MESA", "CAMA", "PUERTA", "VENTANA", "TECHO",
                "PERRO", "GATO", "PATO", "PATO", "LEON", "TIGRE", "OSO", "LOBO", "ZORRO", "RATA",
                "PAN", "SAL", "AZUCAR", "LECHE", "CARNE", "POLLO", "ARROZ", "SOPA", "VINO", "CAFE",
                "MANO", "PIE", "OJO", "NARIZ", "BOCA", "CARA", "PELO", "BRAZO", "PIERNA", "CUERPO",
                "ROJO", "AZUL", "VERDE", "NEGRO", "BLANCO", "AMARILLO", "ROSA", "MORADO", "GRIS", "CAFE",
                "UNO", "DOS", "TRES", "CUATRO", "CINCO", "SEIS", "SIETE", "OCHO", "NUEVE", "DIEZ",
                "HOLA", "ADIOS", "GRACIAS", "FAVOR", "BIEN", "MAL", "SI", "NO", "QUIZAS", "NUNCA",
                "COMER", "BEBER", "DORMIR", "CORRER", "SALTAR", "LEER", "ESCRIBIR", "HABLAR", "OIR", "VER",
                "SOLO", "CON", "SIN", "POR", "PARA", "ANTE", "BAJO", "CADA", "COMO", "CUANDO",
                "CIUDAD", "PAIS", "MUNDO", "TIERRA", "ESPACIO", "TIEMPO", "DINERO", "TRABAJO", "FAMILIA", "AMIGO",
                "NINO", "NINA", "HOMBRE", "MUJER", "PADRE", "MADRE", "HIJO", "HIJA", "HERMANO", "HERMANA",
                "CARRO", "TREN", "AVION", "BARCO", "MOTO", "BICI", "BUS", "TAXI", "METRO", "AUTO",
                "MUSICA", "ARTE", "CINE", "TEATRO", "DANZA", "PINTURA", "FOTO", "VIDEO", "RADIO", "TELE",
                "ROSA", "LILA", "NUBE", "PIEL", "ALMA", "VELA", "MESA", "PISO", "MURO", "LLAVE",
                "SOBRE", "ENTRE", "HACIA", "DESDE", "HASTA", "SEGUN", "DURANTE", "AUNQUE", "PORQUE", "SINO",
                "LETRA", "HILERA", "CADENA", "TEXTO", "VOCAL", "CONSONANTE", "PALABRA", "FRASE", "PARRAFO", "ORACION",
                "DATO", "TIPO", "CASO", "PLAN", "META", "IDEA", "MODO", "PASO", "BASE", "NIVEL",
                "JAVA", "CLASE", "METODO", "OBJETO", "ARRAY", "LISTA", "MAPA", "NODO", "COLA", "PILA",
                "SOL", "ROL", "RED", "GAS", "LUZ", "VEZ", "PIE", "FIN", "FIN", "MAR",
                "PATO", "POLO", "BOLA", "TELA", "LANA", "SEDA", "LINO", "HILO", "NUDO", "LAZO",
                "PLAZA", "CALLE", "PARQUE", "JARDIN", "PUENTE", "CAMINO", "RUTA", "SENDA", "PISTA", "FINCA",
                "CALOR", "FRIO", "VIENTO", "LLUVIA", "NIEVE", "TORMENTA", "RAYO", "TRUENO", "NIEBLA", "ROCIO"
        ));
    }

    // Crea y mezcla el saco de fichas
    private void inicializarSaco() {
        saco = new ArrayList<>();
        for (Map.Entry<Character, Integer> e : DISTRIBUCION.entrySet()) {
            for (int i = 0; i < e.getValue(); i++) {
                saco.add(e.getKey());
            }
        }
        Collections.shuffle(saco);
    }

    private void inicializarJugadores() {
        for (int i = 0; i < nombresJugadores.size(); i++) {
            fichasJugadores.add(new ArrayList<>());
            puntuaciones.add(0);
            robarFichas(i, FICHAS_POR_JUGADOR);
        }
        turnoActual = 0;
    }

    // Jugadas
    public ResultadoJugada colocarPalabra(String palabra, int fila, int columna, boolean horizontal) {
        palabra = palabra.toUpperCase().trim();

        // Validar que la palabra esté en el diccionario
        if (!diccionario.contains(palabra)) {
            return new ResultadoJugada(false, "La palabra '" + palabra + "' no está en el diccionario.", 0);
        }

        // Verificar que cabe en el tablero
        if (horizontal && columna + palabra.length() > TAMANIO) {
            return new ResultadoJugada(false, "La palabra no cabe horizontalmente.", 0);
        }
        if (!horizontal && fila + palabra.length() > TAMANIO) {
            return new ResultadoJugada(false, "La palabra no cabe verticalmente.", 0);
        }

        // Verificar que el jugador tiene las fichas necesarias (respetando letras ya en tablero)
        List<Character> fichasUsadas = fichasNecesarias(palabra, fila, columna, horizontal);
        if (fichasUsadas == null) {
            return new ResultadoJugada(false, "Conflicto con letras ya colocadas en el tablero.", 0);
        }

        List<Character> fichasJugador = fichasJugadores.get(turnoActual);
        if (!tieneTodasFichas(fichasJugador, fichasUsadas)) {
            return new ResultadoJugada(false, "No tienes todas las fichas necesarias.", 0);
        }

        // Primera jugada debe pasar por el centro (7,7)
        if (primeraJugada) {
            boolean pasaPorCentro = false;
            for (int i = 0; i < palabra.length(); i++) {
                int r = horizontal ? fila : fila + i;
                int c = horizontal ? columna + i : columna;
                if (r == 7 && c == 7) {
                    pasaPorCentro = true;
                    break;
                }
            }
            if (!pasaPorCentro) {
                return new ResultadoJugada(false, "La primera palabra debe pasar por el centro (H8).", 0);
            }
        } else {
            // Palabras posteriores deben conectar con una letra ya colocada
            if (!conectaConTablero(palabra, fila, columna, horizontal)) {
                return new ResultadoJugada(false, "La palabra debe conectar con letras ya colocadas.", 0);
            }
        }

        // Calcular puntuación
        int puntos = calcularPuntos(palabra, fila, columna, horizontal);

        // Colocar la palabra en el tablero
        for (int i = 0; i < palabra.length(); i++) {
            int r = horizontal ? fila : fila + i;
            int c = horizontal ? columna + i : columna;
            tablero[r][c] = palabra.charAt(i);
        }

        // Quitar fichas usadas del jugador y robar nuevas
        for (char f : fichasUsadas) {
            fichasJugador.remove((Character) f);
        }
        robarFichas(turnoActual, fichasUsadas.size());

        // Actualizar puntuación
        puntuaciones.set(turnoActual, puntuaciones.get(turnoActual) + puntos);

        // Registrar en historial
        String dir = horizontal ? "H" : "V";
        String jugada = String.format("%s jugó '%s' en (%d,%d) %s → %d puntos",
                nombresJugadores.get(turnoActual), palabra, fila, columna, dir, puntos);
        historial.add(jugada);

        primeraJugada = false;
        pasesConsecutivos = 0;

        // Verificar fin de juego
        verificarFinJuego();

        // Pasar turno
        avanzarTurno();

        return new ResultadoJugada(true, jugada, puntos);
    }

    public ResultadoJugada pasarTurno() {
        pasesConsecutivos++;
        String msg = nombresJugadores.get(turnoActual) + " pasó su turno.";
        historial.add(msg);
        if (pasesConsecutivos >= nombresJugadores.size() * 2) {
            juegoTerminado = true;
        }
        avanzarTurno();
        return new ResultadoJugada(true, msg, 0);
    }

    // Jugador cambia fichas actuales
    public ResultadoJugada cambiarFichas(List<Character> fichasACambiar) {
        if (saco.size() < fichasACambiar.size()) {
            return new ResultadoJugada(false, "No hay suficientes fichas en el saco.", 0);
        }

        List<Character> mano = fichasJugadores.get(turnoActual);
        for (char f : fichasACambiar) {
            if (!mano.contains(f)) {
                return new ResultadoJugada(false, "No tienes la ficha '" + f + "'.", 0);
            }
        }

        // Devolver fichas al saco
        for (char f : fichasACambiar) {
            mano.remove((Character) f);
            saco.add(f);
        }
        Collections.shuffle(saco);

        // Robar nuevas
        robarFichas(turnoActual, fichasACambiar.size());

        pasesConsecutivos++;
        String msg = nombresJugadores.get(turnoActual) + " cambió " + fichasACambiar.size() + " ficha(s).";
        historial.add(msg);
        avanzarTurno();
        return new ResultadoJugada(true, msg, 0);
    }

    // Determina qué fichas debe usar el jugador (las letras no presentes en el tablero)
    private List<Character> fichasNecesarias(String palabra, int fila, int col, boolean horiz) {
        List<Character> necesarias = new ArrayList<>();
        for (int i = 0; i < palabra.length(); i++) {
            int r = horiz ? fila : fila + i;
            int c = horiz ? col + i : col;
            char enTablero = tablero[r][c];
            char delaPalabra = palabra.charAt(i);
            if (enTablero == ' ') {
                necesarias.add(delaPalabra);
            } else if (enTablero != delaPalabra) {
                return null; // conflicto
            }
        }
        return necesarias;
    }

    // Verifica que el jugador tenga todas las fichas requeridas 
    private boolean tieneTodasFichas(List<Character> mano, List<Character> requeridas) {
        List<Character> copia = new ArrayList<>(mano);
        for (char f : requeridas) {
            if (!copia.remove((Character) f)) {
                // intentar con comodín
                if (!copia.remove((Character) ' ')) {
                    return false;
                }
            }
        }
        return true;
    }

    // Verifica que la palabra conecte con alguna letra del tabler
    private boolean conectaConTablero(String palabra, int fila, int col, boolean horiz) {
        for (int i = 0; i < palabra.length(); i++) {
            int r = horiz ? fila : fila + i;
            int c = horiz ? col + i : col;
            // La casilla ya tiene letra
            if (tablero[r][c] != ' ') {
                return true;
            }
            // Una casilla adyacente tiene letra
            if (r > 0 && tablero[r - 1][c] != ' ') {
                return true;
            }
            if (r < TAMANIO - 1 && tablero[r + 1][c] != ' ') {
                return true;
            }
            if (c > 0 && tablero[r][c - 1] != ' ') {
                return true;
            }
            if (c < TAMANIO - 1 && tablero[r][c + 1] != ' ') {
                return true;
            }
        }
        return false;
    }

    // Calcula los puntos de la jugada aplicando multiplicadores
    private int calcularPuntos(String palabra, int fila, int col, boolean horiz) {
        int puntos = 0;
        int multPalabra = 1;

        for (int i = 0; i < palabra.length(); i++) {
            int r = horiz ? fila : fila + i;
            int c = horiz ? col + i : col;

            // Si la casilla ya tenía letra, no aplica bonificación
            if (tablero[r][c] != ' ') {
                puntos += valorLetra(palabra.charAt(i));
                continue;
            }

            int valLetra = valorLetra(palabra.charAt(i));
            TipoCasilla tipo = especiales[r][c];

            switch (tipo) {
                case DOBLE_LETRA:
                    valLetra *= 2;
                    break;
                case TRIPLE_LETRA:
                    valLetra *= 3;
                    break;
                case DOBLE_PALABRA:
                case CENTRO:
                    multPalabra *= 2;
                    break;
                case TRIPLE_PALABRA:
                    multPalabra *= 3;
                    break;
                default:
                    break;
            }
            puntos += valLetra;
        }

        puntos *= multPalabra;

        // Bingo: 50 puntos extra si usa las 7 fichas
        List<Character> usadas = fichasNecesarias(palabra, fila, col, horiz);
        if (usadas != null && usadas.size() == FICHAS_POR_JUGADOR) {
            puntos += 50;
        }

        return puntos;
    }

    // Roba fichas del saco para el jugador indicado
    private void robarFichas(int jugadorIdx, int cantidad) {
        List<Character> mano = fichasJugadores.get(jugadorIdx);
        for (int i = 0; i < cantidad && !saco.isEmpty(); i++) {
            mano.add(saco.remove(0));
        }
    }

    // Avanza al siguiente turno
    private void avanzarTurno() {
        turnoActual = (turnoActual + 1) % nombresJugadores.size();
    }

    // Verifica si el juego debe terminar
    private void verificarFinJuego() {
        // Termina si un jugador se queda sin fichas y el saco está vacío
        for (int i = 0; i < nombresJugadores.size(); i++) {
            if (fichasJugadores.get(i).isEmpty() && saco.isEmpty()) {
                juegoTerminado = true;
                // Descontar fichas restantes de los demás
                for (int j = 0; j < nombresJugadores.size(); j++) {
                    if (j != i) {
                        int descuento = fichasJugadores.get(j).stream()
                                .mapToInt(c -> valorLetra(c)).sum();
                        puntuaciones.set(j, puntuaciones.get(j) - descuento);
                        puntuaciones.set(i, puntuaciones.get(i) + descuento);
                    }
                }
                return;
            }
        }
    }

    // Valor en puntos de una letra
    public int valorLetra(char c) {
        return VALORES.getOrDefault(Character.toUpperCase(c), 0);
    }

    // Agrega una palabra al diccionario 
    public void agregarPalabra(String palabra) {
        diccionario.add(palabra.toUpperCase().trim());
    }

    //Verifica si una palabra está en el diccionario 
    public boolean esPalabraValida(String palabra) {
        return diccionario.contains(palabra.toUpperCase().trim());
    }

    public char[][] getTablero() {
        return tablero;
    }

    public TipoCasilla[][] getEspeciales() {
        return especiales;
    }

    public List<Character> getFichasJugador(int idx) {
        return fichasJugadores.get(idx);
    }

    public int getPuntuacion(int idx) {
        return puntuaciones.get(idx);
    }

    public String getNombreJugador(int idx) {
        return nombresJugadores.get(idx);
    }

    public int getTurnoActual() {
        return turnoActual;
    }

    public int getCantidadJugadores() {
        return nombresJugadores.size();
    }

    public List<String> getHistorial() {
        return Collections.unmodifiableList(historial);
    }

    public boolean isJuegoTerminado() {
        return juegoTerminado;
    }

    public int getFichasEnSaco() {
        return saco.size();
    }

    public boolean isPrimeraJugada() {
        return primeraJugada;
    }

    public List<Integer> getPuntuaciones() {
        return Collections.unmodifiableList(puntuaciones);
    }

    // Retorna el índice del ganador (mayor puntuación)
    public int getGanador() {
        int max = -1, idx = 0;
        for (int i = 0; i < puntuaciones.size(); i++) {
            if (puntuaciones.get(i) > max) {
                max = puntuaciones.get(i);
                idx = i;
            }
        }
        return idx;
    }

    // Mini clase interna: Resultado de una jugada
    public static class ResultadoJugada {

        public final boolean exito;
        public final String mensaje;
        public final int puntos;

        public ResultadoJugada(boolean exito, String mensaje, int puntos) {
            this.exito = exito;
            this.mensaje = mensaje;
            this.puntos = puntos;
        }
    }
}
