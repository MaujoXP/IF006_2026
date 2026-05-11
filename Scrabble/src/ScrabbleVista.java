/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Meowricio
 */
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class ScrabbleVista extends JFrame {

    private ScrabbleControlador controlador;

    private JButton[][] celdas;
    private static final int CELL_SIZE = 46;

    private JPanel panelFichas;
    private List<JButton> botonesFichas = new ArrayList<>();

    private JTextField txtPalabra;
    private JTextField txtCoordenada;
    private JComboBox<String> cmbDireccion;
    private JButton btnJugar;
    private JButton btnPasar;
    private JButton btnCambiar;
    private JButton btnVerificar;

    private JLabel lblTurno;
    private JLabel lblSaco;
    private JTextArea areaHistorial;
    private JLabel[] lblPuntuaciones;
    private JLabel lblMensaje;
    private JPanel panelMarcador;

    private static final Color COL_NORMAL       = new Color(0xD4B896);
    private static final Color COL_DOBLE_LETRA  = new Color(0x87CEEB);
    private static final Color COL_TRIPLE_LETRA = new Color(0x4169E1);
    private static final Color COL_DOBLE_PAL    = new Color(0xFFB6C1);
    private static final Color COL_TRIPLE_PAL   = new Color(0xFF4500);
    private static final Color COL_CENTRO       = new Color(0xFF69B4);
    private static final Color COL_FICHA        = new Color(0xF5DEB3);
    private static final Color COL_FICHA_BORDE  = new Color(0x8B6914);
    private static final Color COL_TABLERO_BG   = new Color(0x2F6B2F);
    private static final Color COL_PANEL_BG     = new Color(0x1A1A2E);
    private static final Color COL_TEXTO        = new Color(0xF0E6D3);
    private static final Color COL_ACENTO       = new Color(0xD4AF37);

    // ── Fuentes ──────────────────────────────────────────────────────────────
    private static final Font FONT_LETRA   = new Font("Serif", Font.BOLD, 14);
    private static final Font FONT_VALOR   = new Font("SansSerif", Font.PLAIN, 8);
    private static final Font FONT_TITULO  = new Font("Serif", Font.BOLD, 22);
    private static final Font FONT_LABEL   = new Font("SansSerif", Font.BOLD, 13);
    private static final Font FONT_SMALL   = new Font("SansSerif", Font.PLAIN, 11);
    private static final Font FONT_FICHA   = new Font("Serif", Font.BOLD, 20);

    // ════════════════════════════════════════════════════════════════════════
    //  CONSTRUCTOR
    // ════════════════════════════════════════════════════════════════════════
    public ScrabbleVista() {
        setTitle("SCRABBLE - Juego de Palabras");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setBackground(COL_PANEL_BG);

        controlador = new ScrabbleControlador(this);

        construirUI();
        mostrarDialogoInicio();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void construirUI() {
        setLayout(new BorderLayout(8, 8));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(10, 10, 10, 10));

        // Título
        JLabel titulo = new JLabel("S C R A B B L E", SwingConstants.CENTER);
        titulo.setFont(FONT_TITULO);
        titulo.setForeground(COL_ACENTO);
        titulo.setBorder(new EmptyBorder(4, 0, 8, 0));
        add(titulo, BorderLayout.NORTH);

        // Centro: tablero + panel derecho
        JPanel centro = new JPanel(new BorderLayout(10, 0));
        centro.setOpaque(false);
        centro.add(construirPanelTablero(), BorderLayout.CENTER);
        centro.add(construirPanelDerecho(), BorderLayout.EAST);
        add(centro, BorderLayout.CENTER);

        // Sur: mensaje de estado
        lblMensaje = new JLabel(" ", SwingConstants.CENTER);
        lblMensaje.setFont(FONT_SMALL);
        lblMensaje.setForeground(COL_TEXTO);
        lblMensaje.setBorder(new EmptyBorder(6, 0, 0, 0));
        add(lblMensaje, BorderLayout.SOUTH);
    }

    // Construye el tablero 15x15 con etiquetas de columna y fila 
    private JPanel construirPanelTablero() {
        JPanel contenedor = new JPanel(new BorderLayout(2, 2));
        contenedor.setOpaque(false);

        // Etiquetas de columnas (A-O)
        JPanel colLabels = new JPanel(new GridLayout(1, Scrabble.TAMANIO + 1));
        colLabels.setOpaque(false);
        colLabels.add(new JLabel(""));
        for (int c = 0; c < Scrabble.TAMANIO; c++) {
            JLabel lbl = new JLabel(String.valueOf((char) ('A' + c)), SwingConstants.CENTER);
            lbl.setFont(FONT_SMALL);
            lbl.setForeground(COL_ACENTO);
            colLabels.add(lbl);
        }
        contenedor.add(colLabels, BorderLayout.NORTH);

        // Tablero + etiquetas de fila
        JPanel gridWrapper = new JPanel(new BorderLayout(2, 0));
        gridWrapper.setOpaque(false);

        // Etiquetas de fila (1-15)
        JPanel rowLabels = new JPanel(new GridLayout(Scrabble.TAMANIO, 1));
        rowLabels.setOpaque(false);
        for (int r = 0; r < Scrabble.TAMANIO; r++) {
            JLabel lbl = new JLabel(String.valueOf(r + 1), SwingConstants.CENTER);
            lbl.setFont(FONT_SMALL);
            lbl.setForeground(COL_ACENTO);
            lbl.setPreferredSize(new Dimension(22, CELL_SIZE));
            rowLabels.add(lbl);
        }
        gridWrapper.add(rowLabels, BorderLayout.WEST);

        // Grid de celdas
        JPanel grid = new JPanel(new GridLayout(Scrabble.TAMANIO, Scrabble.TAMANIO, 1, 1));
        grid.setBackground(COL_TABLERO_BG);
        grid.setBorder(new LineBorder(COL_TABLERO_BG, 2));
        celdas = new JButton[Scrabble.TAMANIO][Scrabble.TAMANIO];

        for (int r = 0; r < Scrabble.TAMANIO; r++) {
            for (int c = 0; c < Scrabble.TAMANIO; c++) {
                celdas[r][c] = crearCelda(r, c, Scrabble.TipoCasilla.NORMAL);
                grid.add(celdas[r][c]);
            }
        }
        gridWrapper.add(grid, BorderLayout.CENTER);
        contenedor.add(gridWrapper, BorderLayout.CENTER);

        // Leyenda
        contenedor.add(construirLeyenda(), BorderLayout.SOUTH);

        return contenedor;
    }

    // Crea una celda del tablero con el color y texto según su tipo 
    private JButton crearCelda(int r, int c, Scrabble.TipoCasilla tipo) {
        JButton btn = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Subíndice del valor de la letra
                String texto = getText();
                if (!texto.isEmpty() && !texto.contains("\n")) {
                    // Ya pintado por Swing
                }
            }
        };
        btn.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
        btn.setMargin(new Insets(0, 0, 0, 0));
        btn.setFocusPainted(false);
        btn.setBorderPainted(true);
        btn.setBorder(new LineBorder(new Color(0x1A7A1A), 1));
        btn.setFont(FONT_LETRA);
        aplicarColorCelda(btn, tipo, ' ');

        // Click en celda → rellena coordenada
        final int fr = r, fc = c;
        btn.addActionListener(e -> {
            String coord = String.valueOf((char) ('A' + fc)) + (fr + 1);
            txtCoordenada.setText(coord);
        });

        return btn;
    }

    // Aplica color según tipo de casilla y si tiene ficha  
    private void aplicarColorCelda(JButton btn, Scrabble.TipoCasilla tipo, char letra) {
        if (letra != ' ') {
            // Ficha colocada
            btn.setBackground(COL_FICHA);
            btn.setForeground(new Color(0x3A2000));
            return;
        }
        Color bg;
        String texto = "";
        switch (tipo) {
            case TRIPLE_PALABRA:  bg = COL_TRIPLE_PAL; texto = "3P"; break;
            case DOBLE_PALABRA:   bg = COL_DOBLE_PAL;  texto = "2P"; break;
            case TRIPLE_LETRA:    bg = COL_TRIPLE_LETRA; texto = "3L"; break;
            case DOBLE_LETRA:     bg = COL_DOBLE_LETRA; texto = "2L"; break;
            case CENTRO:          bg = COL_CENTRO;     texto = "★"; break;
            default:              bg = COL_NORMAL;     break;
        }
        btn.setBackground(bg);
        if (letra == ' ') btn.setText(texto);
        btn.setFont(texto.equals("★") ? new Font("Serif", Font.BOLD, 16) : new Font("SansSerif", Font.BOLD, 9));
        btn.setForeground(letra != ' ' ? Color.BLACK : new Color(0, 0, 0, 160));
    }

    // Panel derecho: marcador, fichas, controles, historial 
    private JPanel construirPanelDerecho() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(280, 100));

        panel.add(construirPanelMarcador());
        panel.add(Box.createVerticalStrut(8));
        panel.add(construirPanelFichasJugador());
        panel.add(Box.createVerticalStrut(8));
        panel.add(construirPanelControles());
        panel.add(Box.createVerticalStrut(8));
        panel.add(construirPanelHistorial());

        return panel;
    }

    // Panel de marcador y turno 
    private JPanel construirPanelMarcador() {
        JPanel p = crearPanelEstilo("MARCADOR");
        panelMarcador = new JPanel(new GridLayout(0, 1, 2, 2));
        panelMarcador.setOpaque(false);

        lblTurno = new JLabel("Turno: —", SwingConstants.CENTER);
        lblTurno.setFont(FONT_LABEL);
        lblTurno.setForeground(COL_ACENTO);
        p.add(lblTurno);

        lblSaco = new JLabel("Fichas en saco: —", SwingConstants.CENTER);
        lblSaco.setFont(FONT_SMALL);
        lblSaco.setForeground(COL_TEXTO);
        p.add(lblSaco);

        p.add(panelMarcador);
        return p;
    }

    // Panel de fichas del jugador actual  
    private JPanel construirPanelFichasJugador() {
        JPanel p = crearPanelEstilo("TUS FICHAS");
        panelFichas = new JPanel(new FlowLayout(FlowLayout.CENTER, 4, 4));
        panelFichas.setOpaque(false);
        p.add(panelFichas);
        return p;
    }

    // Panel de controles para jugar  
    private JPanel construirPanelControles() {
        JPanel p = crearPanelEstilo("JUGAR");

        // Palabra
        p.add(crearLabel("Palabra:"));
        txtPalabra = crearTextField();
        txtPalabra.setToolTipText("Escribe la palabra a colocar");
        p.add(txtPalabra);

        // Coordenada
        p.add(crearLabel("Coordenada (ej: H8):"));
        txtCoordenada = crearTextField();
        txtCoordenada.setToolTipText("O haz clic en una casilla del tablero");
        p.add(txtCoordenada);

        // Dirección
        p.add(crearLabel("Dirección:"));
        cmbDireccion = new JComboBox<>(new String[]{"Horizontal (H)", "Vertical (V)"});
        cmbDireccion.setBackground(new Color(0x2A2A4A));
        cmbDireccion.setForeground(COL_TEXTO);
        cmbDireccion.setFont(FONT_SMALL);
        cmbDireccion.setMaximumSize(new Dimension(Integer.MAX_VALUE, 28));
        p.add(cmbDireccion);

        p.add(Box.createVerticalStrut(6));

        // Botones principales
        btnJugar = crearBoton("▶ COLOCAR PALABRA", COL_ACENTO, Color.BLACK);
        btnJugar.addActionListener(e -> accionJugar());
        p.add(btnJugar);

        JPanel botonesSec = new JPanel(new GridLayout(1, 3, 4, 0));
        botonesSec.setOpaque(false);
        botonesSec.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));

        btnPasar   = crearBotonSmall("Pasar", new Color(0x6C757D));
        btnCambiar = crearBotonSmall("Cambiar", new Color(0x17A2B8));
        btnVerificar = crearBotonSmall("Verificar", new Color(0x28A745));

        btnPasar.addActionListener(e -> controlador.procesarPase());
        btnCambiar.addActionListener(e -> accionCambiar());
        btnVerificar.addActionListener(e -> accionVerificar());

        botonesSec.add(btnPasar);
        botonesSec.add(btnCambiar);
        botonesSec.add(btnVerificar);
        p.add(botonesSec);

        // Atajos de teclado
        txtPalabra.addActionListener(e -> txtCoordenada.requestFocus());
        txtCoordenada.addActionListener(e -> accionJugar());

        return p;
    }

    // Panel del historial de jugadas 
    private JPanel construirPanelHistorial() {
        JPanel p = crearPanelEstilo("HISTORIAL");
        areaHistorial = new JTextArea(8, 26);
        areaHistorial.setEditable(false);
        areaHistorial.setFont(new Font("Monospaced", Font.PLAIN, 11));
        areaHistorial.setBackground(new Color(0x0F0F1F));
        areaHistorial.setForeground(new Color(0x90EE90));
        areaHistorial.setLineWrap(true);
        areaHistorial.setWrapStyleWord(true);

        JScrollPane scroll = new JScrollPane(areaHistorial);
        scroll.setMaximumSize(new Dimension(Integer.MAX_VALUE, 160));
        scroll.setPreferredSize(new Dimension(260, 160));
        scroll.setBorder(new LineBorder(COL_ACENTO, 1));
        p.add(scroll);
        return p;
    }

    // Leyenda de colores del tablero  
    private JPanel construirLeyenda() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER, 6, 2));
        p.setOpaque(false);
        agregarItemLeyenda(p, COL_TRIPLE_PAL,   "3×Pal");
        agregarItemLeyenda(p, COL_DOBLE_PAL,    "2×Pal");
        agregarItemLeyenda(p, COL_TRIPLE_LETRA, "3×Let");
        agregarItemLeyenda(p, COL_DOBLE_LETRA,  "2×Let");
        agregarItemLeyenda(p, COL_CENTRO,       "★Centro");
        return p;
    }

    private void agregarItemLeyenda(JPanel p, Color color, String texto) {
        JLabel cuadro = new JLabel("  ");
        cuadro.setOpaque(true);
        cuadro.setBackground(color);
        cuadro.setBorder(new LineBorder(Color.BLACK, 1));
        JLabel txt = new JLabel(texto);
        txt.setFont(new Font("SansSerif", Font.PLAIN, 10));
        txt.setForeground(COL_TEXTO);
        p.add(cuadro);
        p.add(txt);
    }
 
    private void accionJugar() {
        String palabra  = txtPalabra.getText();
        String coord    = txtCoordenada.getText();
        String dir      = cmbDireccion.getSelectedIndex() == 0 ? "H" : "V";
        controlador.procesarJugada(palabra, coord, dir);
        txtPalabra.setText("");
        txtPalabra.requestFocus();
    }

    private void accionCambiar() {
        String fichas = JOptionPane.showInputDialog(this,
                "Escribe las letras que quieres cambiar (ej: AEI):",
                "Cambiar Fichas", JOptionPane.QUESTION_MESSAGE);
        if (fichas != null && !fichas.trim().isEmpty())
            controlador.procesarCambioFichas(fichas);
    }

    private void accionVerificar() {
        String palabra = txtPalabra.getText().trim();
        if (palabra.isEmpty()) {
            palabra = JOptionPane.showInputDialog(this,
                    "¿Qué palabra quieres verificar?",
                    "Verificar Palabra", JOptionPane.QUESTION_MESSAGE);
        }
        if (palabra != null && !palabra.isEmpty())
            controlador.verificarPalabra(palabra);
    }
 
    private void mostrarDialogoInicio() {
        JDialog dialogo = new JDialog(this, "Nueva Partida de Scrabble", true);
        dialogo.setLayout(new BorderLayout(10, 10));
        dialogo.getContentPane().setBackground(COL_PANEL_BG);
        dialogo.setSize(400, 340);
        dialogo.setLocationRelativeTo(this);

        // Título
        JLabel tit = new JLabel("Nueva Partida", SwingConstants.CENTER);
        tit.setFont(FONT_TITULO);
        tit.setForeground(COL_ACENTO);
        tit.setBorder(new EmptyBorder(14, 0, 6, 0));
        dialogo.add(tit, BorderLayout.NORTH);

        // Campos de jugadores
        JPanel campos = new JPanel(new GridLayout(0, 2, 8, 8));
        campos.setOpaque(false);
        campos.setBorder(new EmptyBorder(10, 20, 10, 20));

        JTextField[] nombresFields = new JTextField[4];
        String[] defaults = {"Jugador 1", "Jugador 2", "", ""};
        for (int i = 0; i < 4; i++) {
            JLabel lbl = new JLabel("Jugador " + (i + 1) + ":");
            lbl.setFont(FONT_LABEL);
            lbl.setForeground(i < 2 ? COL_TEXTO : new Color(0x888888));
            campos.add(lbl);

            nombresFields[i] = new JTextField(defaults[i]);
            nombresFields[i].setBackground(new Color(0x2A2A4A));
            nombresFields[i].setForeground(COL_TEXTO);
            nombresFields[i].setCaretColor(COL_ACENTO);
            nombresFields[i].setFont(FONT_LABEL);
            nombresFields[i].setBorder(BorderFactory.createCompoundBorder(
                    new LineBorder(COL_ACENTO, 1),
                    new EmptyBorder(4, 6, 4, 6)));
            campos.add(nombresFields[i]);
        }
        dialogo.add(campos, BorderLayout.CENTER);

        JLabel nota = new JLabel("(Deja vacío los jugadores que no uses)", SwingConstants.CENTER);
        nota.setFont(FONT_SMALL);
        nota.setForeground(new Color(0x888888));

        JButton btnIniciar = crearBoton("▶ INICIAR PARTIDA", COL_ACENTO, Color.BLACK);
        btnIniciar.addActionListener(e -> {
            List<String> nombres = new ArrayList<>();
            for (JTextField f : nombresFields) {
                String n = f.getText().trim();
                if (!n.isEmpty()) nombres.add(n);
            }
            if (nombres.isEmpty()) nombres.add("Jugador 1");
            dialogo.dispose();
            controlador.iniciarPartida(nombres);
        });

        JPanel sur = new JPanel(new BorderLayout(0, 4));
        sur.setOpaque(false);
        sur.setBorder(new EmptyBorder(0, 20, 14, 20));
        sur.add(nota, BorderLayout.NORTH);
        sur.add(btnIniciar, BorderLayout.CENTER);
        dialogo.add(sur, BorderLayout.SOUTH);

        dialogo.setVisible(true);
    }
 
    // Actualiza la representación gráfica del tablero 
    public void actualizarTablero(char[][] tablero, Scrabble.TipoCasilla[][] especiales) {
        for (int r = 0; r < Scrabble.TAMANIO; r++) {
            for (int c = 0; c < Scrabble.TAMANIO; c++) {
                char letra = tablero[r][c];
                JButton celda = celdas[r][c];

                if (letra != ' ') {
                    // Ficha colocada: mostrar letra grande + valor pequeño
                    celda.setText("<html><center><b style='font-size:12px'>" + letra
                            + "</b></center></html>");
                    celda.setBackground(COL_FICHA);
                    celda.setForeground(new Color(0x3A2000));
                    celda.setFont(FONT_LETRA);
                    celda.setBorder(BorderFactory.createCompoundBorder(
                            new LineBorder(COL_FICHA_BORDE, 1),
                            new BevelBorder(BevelBorder.RAISED)));
                } else {
                    // Casilla vacía: restaurar tipo
                    Scrabble.TipoCasilla tipo = especiales[r][c];
                    aplicarColorCelda(celda, tipo, ' ');
                    celda.setBorder(new LineBorder(new Color(0x1A7A1A), 1));
                }
            }
        }
        repaint();
    }

    // Actualiza turno, fichas y puntuaciones 
    public void actualizarTurno(String nombreJugador, List<Character> fichas,
                                 List<Integer> puntuaciones) {
        lblTurno.setText("Turno: " + nombreJugador);

        // Actualizar fichas
        panelFichas.removeAll();
        botonesFichas.clear();
        for (char c : fichas) {
            JButton btn = crearBotonFicha(c);
            botonesFichas.add(btn);
            panelFichas.add(btn);
        }
        panelFichas.revalidate();
        panelFichas.repaint();

        // Actualizar marcador
        panelMarcador.removeAll();
        Scrabble modelo = controlador.getModelo();
        if (modelo != null) {
            for (int i = 0; i < modelo.getCantidadJugadores(); i++) {
                String nombre = modelo.getNombreJugador(i);
                int pts = puntuaciones.get(i);
                boolean esActual = nombre.equals(nombreJugador);

                JLabel lbl = new JLabel(nombre + ": " + pts + " pts", SwingConstants.CENTER);
                lbl.setFont(FONT_LABEL);
                lbl.setForeground(esActual ? COL_ACENTO : COL_TEXTO);
                if (esActual) {
                    lbl.setBorder(BorderFactory.createCompoundBorder(
                            new LineBorder(COL_ACENTO, 1),
                            new EmptyBorder(2, 4, 2, 4)));
                }
                panelMarcador.add(lbl);
            }
        }
        panelMarcador.revalidate();
        panelMarcador.repaint();
    }

    // Muestra cuántas fichas quedan en el saco 
    public void mostrarFichasEnSaco(int cantidad) {
        lblSaco.setText("Fichas en saco: " + cantidad);
    }

    // Muestra un mensaje de éxito/información 
    public void mostrarMensaje(String mensaje) {
        lblMensaje.setForeground(new Color(0x90EE90));
        lblMensaje.setText(mensaje);
    }

    // Muestra un mensaje de error  
    public void mostrarError(String error) {
        lblMensaje.setForeground(new Color(0xFF6B6B));
        lblMensaje.setText("⚠ " + error);
    }

    // Actualiza el historial de jugadas 
    public void actualizarHistorial(java.util.List<String> historial) {
        StringBuilder sb = new StringBuilder();
        for (int i = historial.size() - 1; i >= 0; i--) {
            sb.append(historial.get(i)).append("\n");
        }
        areaHistorial.setText(sb.toString());
        areaHistorial.setCaretPosition(0);
    }

    // Muestra el resultado final del juego  
    public void mostrarFinDeJuego(String resumen, String ganador, List<Integer> puntuaciones) {
        btnJugar.setEnabled(false);
        btnPasar.setEnabled(false);
        btnCambiar.setEnabled(false);

        JOptionPane.showMessageDialog(this, resumen,
                "¡Juego Terminado! Ganó: " + ganador,
                JOptionPane.INFORMATION_MESSAGE);

        // Preguntar si desea nueva partida
        int op = JOptionPane.showConfirmDialog(this,
                "¿Deseas iniciar una nueva partida?",
                "Nueva Partida", JOptionPane.YES_NO_OPTION);
        if (op == JOptionPane.YES_OPTION) {
            btnJugar.setEnabled(true);
            btnPasar.setEnabled(true);
            btnCambiar.setEnabled(true);
            mostrarDialogoInicio();
        }
    }
 
    // Crea un panel con borde y título estilizado  
    private JPanel crearPanelEstilo(String titulo) {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setOpaque(false);

        TitledBorder borde = BorderFactory.createTitledBorder(
                new LineBorder(COL_ACENTO, 1), titulo);
        borde.setTitleFont(new Font("SansSerif", Font.BOLD, 11));
        borde.setTitleColor(COL_ACENTO);
        p.setBorder(BorderFactory.createCompoundBorder(
                borde, new EmptyBorder(4, 6, 4, 6)));

        return p;
    }

    private JLabel crearLabel(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(FONT_SMALL);
        lbl.setForeground(COL_TEXTO);
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        return lbl;
    }

    private JTextField crearTextField() {
        JTextField tf = new JTextField();
        tf.setBackground(new Color(0x2A2A4A));
        tf.setForeground(COL_TEXTO);
        tf.setCaretColor(COL_ACENTO);
        tf.setFont(FONT_LABEL);
        tf.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(COL_ACENTO, 1),
                new EmptyBorder(3, 6, 3, 6)));
        tf.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        return tf;
    }

    private JButton crearBoton(String texto, Color bg, Color fg) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("SansSerif", Font.BOLD, 13));
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(bg.darker(), 2),
                new EmptyBorder(6, 12, 6, 12)));
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        btn.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) { btn.setBackground(bg.brighter()); }
            @Override public void mouseExited(MouseEvent e)  { btn.setBackground(bg); }
        });
        return btn;
    }

    private JButton crearBotonSmall(String texto, Color bg) {
        JButton btn = crearBoton(texto, bg, Color.WHITE);
        btn.setFont(new Font("SansSerif", Font.BOLD, 11));
        return btn;
    }

    /** Crea un botón de ficha estilo Scrabble (madera) */
    private JButton crearBotonFicha(char letra) {
        JButton btn = new JButton("<html><center><b>" + letra
                + "</b><br/><span style='font-size:7px'>"
                + controlador.getModelo().valorLetra(letra)
                + "</span></center></html>") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                // Fondo madera
                GradientPaint gp = new GradientPaint(0, 0, new Color(0xF5DEB3),
                        getWidth(), getHeight(), new Color(0xDEB887));
                g2.setPaint(gp);
                g2.fillRoundRect(1, 1, getWidth()-2, getHeight()-2, 6, 6);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btn.setPreferredSize(new Dimension(38, 42));
        btn.setMinimumSize(new Dimension(38, 42));
        btn.setMaximumSize(new Dimension(38, 42));
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
        btn.setFont(FONT_FICHA);
        btn.setForeground(new Color(0x3A2000));
        btn.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(COL_FICHA_BORDE, 1),
                new BevelBorder(BevelBorder.RAISED, COL_FICHA.brighter(), COL_FICHA_BORDE)));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Click en ficha → agrega letra a la palabra
        btn.addActionListener(e -> {
            String actual = txtPalabra.getText().toUpperCase();
            txtPalabra.setText(actual + letra);
            txtPalabra.requestFocus();
        });
        return btn;
    }
 
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(ScrabbleVista::new);
    }
}


