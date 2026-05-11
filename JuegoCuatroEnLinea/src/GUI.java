/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Meowricio
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GUI extends JFrame {

    private ControladorJuego controlador;
    private JLabel[][] celdas;
    private JButton[] botones;

    public GUI(ControladorJuego controlador) {
        this.controlador = controlador;
        setTitle("4 en Línea");
        setSize(700, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Botones de arriba
        JPanel panelBotones = new JPanel(new GridLayout(1, 7));
        botones = new JButton[7];
        for (int col = 0; col < 7; col++) {
            final int columna = col;
            botones[col] = new JButton("Colocar");
            botones[col].addActionListener((ActionEvent e) -> manejarJugada(columna));
            panelBotones.add(botones[col]);
        }
        add(panelBotones, BorderLayout.NORTH);

        // Tablero
        JPanel panelTablero = new JPanel(new GridLayout(6, 7));
        celdas = new JLabel[6][7];
        for (int f = 0; f < 6; f++) {
            for (int c = 0; c < 7; c++) {
                celdas[f][c] = new JLabel(" ", SwingConstants.CENTER);
                celdas[f][c].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                celdas[f][c].setFont(new Font("Arial", Font.BOLD, 30));
                panelTablero.add(celdas[f][c]);
            }
        }
        add(panelTablero, BorderLayout.CENTER);
    }

    private void manejarJugada(int columna) {
        if (controlador.realizarJugada(columna)) {
            actualizarTablero();
            char[][] tablero = controlador.getTablero();
            int fila = -1;
            for (int f = 0; f < 6; f++) {
                if (tablero[f][columna] != '-') {
                    fila = f;
                    break; 
                }
            }

            if (fila != -1 && controlador.verificarGanador(fila, columna)) {
                JOptionPane.showMessageDialog(this, "Jugador " + controlador.getJugadorActual() + " ganó!");
                controlador.reiniciarJuego();
                actualizarTablero();
            } else if (controlador.verificarEmpate()) {
                JOptionPane.showMessageDialog(this, "Empate!");
                controlador.reiniciarJuego();
                actualizarTablero();
            } else {
                controlador.cambiarTurno();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Columna llena, intente otra");
        }
    }

    private void actualizarTablero() {
        char[][] tablero = controlador.getTablero();
        for (int f = 0; f < 6; f++) {
            for (int c = 0; c < 7; c++) {
                celdas[f][c].setText(tablero[f][c] == '-' ? " " : String.valueOf(tablero[f][c]));
            }
        }
    }

    public static void main(String[] args) {
        JuegoCuatroEnLinea juego = new JuegoCuatroEnLinea();
        ControladorJuego controlador = new ControladorJuego(juego);
        SwingUtilities.invokeLater(() -> new GUI(controlador).setVisible(true));
    }
}
