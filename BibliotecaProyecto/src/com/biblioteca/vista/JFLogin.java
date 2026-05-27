package com.biblioteca.vista;

import com.biblioteca.controlador.LoginControlador;
import com.biblioteca.modelo.Usuario;

import javax.swing.*;
import java.awt.*;

/**
 * Ventana de inicio de sesión del sistema de biblioteca.
 * 
 * Permite al usuario autenticarse ingresando nombre de usuario
 * y contraseña. Si las credenciales son correctas, se abre la
 * ventana principal {@link JFPrincipal}.
 * 
 * Incluye:
 * - Campos de texto para usuario y contraseña.
 * - Botón de ingreso con validación.
 * - Integración con {@link LoginControlador} para verificar credenciales.
 * 
 * @author Mauricio León - C5G444
 * @version 1.0
 */
public class JFLogin extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtContrasenia;
    private JButton btnIngresar;
    
    /**
     * Crea la ventana de inicio de sesión.
     * Configura título, tamaño y componentes gráficos.
     */
    public JFLogin() {
        setTitle("Sistema de Biblioteca - Inicio de Sesión");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(350, 220);
        setLocationRelativeTo(null);
        setResizable(false);
        initComponents();
    }
    
    /**
     * Inicializa los componentes gráficos del formulario:
     * - Etiquetas y campos de texto.
     * - Botón de ingreso.
     * - Listeners para acciones de login.
     */
    private void initComponents() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitulo = new JLabel("Inicio de Sesión", SwingConstants.CENTER);
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(Font.BOLD, 16f));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(lblTitulo, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Usuario:"), gbc);

        txtUsuario = new JTextField(18);
        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(txtUsuario, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Contraseña:"), gbc);

        txtContrasenia = new JPasswordField(18);
        gbc.gridx = 1; gbc.gridy = 2;
        panel.add(txtContrasenia, gbc);

        btnIngresar = new JButton("Ingresar");
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        panel.add(btnIngresar, gbc);

        btnIngresar.addActionListener(e -> login());
        txtContrasenia.addActionListener(e -> login());

        add(panel);
    }
    
    /**
     * Ejecuta el proceso de inicio de sesión:
     * - Valida que los campos no estén vacíos.
     * - Llama al {@link LoginControlador} para autenticar.
     * - Si es correcto, abre {@link JFPrincipal}.
     * - Si falla, muestra un mensaje de error.
     */
    private void login() {
        String usuario = txtUsuario.getText().trim();
        String contrasenia = new String(txtContrasenia.getPassword());

        if (usuario.isEmpty() || contrasenia.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese usuario y contraseña.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        LoginControlador lc = new LoginControlador();
        Usuario u = lc.login(usuario, contrasenia, 1);

        if (u != null) {
            new JFPrincipal(u).setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
            txtContrasenia.setText("");
        }
    }
    
    /**
     * Método principal de la aplicación.
     * Lanza la ventana de inicio de sesión en el hilo de Swing.
     *
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JFLogin().setVisible(true));
    }
}
