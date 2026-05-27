package com.biblioteca.vista;

import com.biblioteca.modelo.Usuario;

import javax.swing.*;
import java.awt.*;

/**
 * Ventana principal del sistema de biblioteca.
 * 
 * Proporciona la interfaz principal tras el inicio de sesión,
 * mostrando un menú con las diferentes opciones de gestión:
 * - Usuarios
 * - Libros
 * - Préstamos
 * - Devoluciones
 * - Historial
 * - Cerrar sesión
 * 
 * Cada opción carga el panel correspondiente en el área central.
 * 
 * @author Mauricio León - C5G444
 * @version 1.0
 */
public class JFPrincipal extends JFrame {

    private Usuario usuarioActual;
    private JPanel panelContenido;
    
    /**
     * Crea la ventana principal del sistema.
     *
     * @param usuario Usuario autenticado en sesión.
     */
    public JFPrincipal(Usuario usuario) {
        this.usuarioActual = usuario;
        setTitle("Sistema de Biblioteca - Bienvenido: " + usuario.getNombre() + " " + usuario.getApellido());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        initComponents();
    }
    
    /**
     * Inicializa los componentes gráficos de la ventana:
     * - Panel de bienvenida.
     * - Barra de menús con las opciones de gestión.
     */
    private void initComponents() {
        panelContenido = new JPanel(new BorderLayout());
        JLabel lblBienvenida = new JLabel("Seleccione una opción del menú.", SwingConstants.CENTER);
        lblBienvenida.setFont(lblBienvenida.getFont().deriveFont(14f));
        panelContenido.add(lblBienvenida, BorderLayout.CENTER);

        add(panelContenido, BorderLayout.CENTER);
        setJMenuBar(crearMenuBar());
    }
    
    /**
     * Construye la barra de menús principal con las opciones
     * de gestión de usuarios, libros, préstamos, devoluciones,
     * historial y sesión.
     *
     * @return La {@link JMenuBar} creada.
     */
    private JMenuBar crearMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // Gestión de Usuarios
        JMenu menuUsuarios = new JMenu("Gestión Usuarios");

        JMenuItem itemRegistrarUsuario = new JMenuItem("Registrar Usuario");
        itemRegistrarUsuario.addActionListener(e -> mostrarPanel(new JPUsuarios(usuarioActual)));
        menuUsuarios.add(itemRegistrarUsuario);

        JMenuItem itemGestionUsuarios = new JMenuItem("Editar / Activar / Desactivar");
        itemGestionUsuarios.addActionListener(e -> mostrarPanel(new JPUsuarios(usuarioActual)));
        menuUsuarios.add(itemGestionUsuarios);

        menuBar.add(menuUsuarios);

        // Gestión de Libros
        JMenu menuLibros = new JMenu("Gestión Libros");

        JMenuItem itemRegistrarLibro = new JMenuItem("Registrar Libro");
        itemRegistrarLibro.addActionListener(e -> mostrarPanel(new JPLibros()));
        menuLibros.add(itemRegistrarLibro);

        JMenuItem itemListarLibros = new JMenuItem("Listar / Buscar / Editar / Eliminar");
        itemListarLibros.addActionListener(e -> mostrarPanel(new JPLibros()));
        menuLibros.add(itemListarLibros);

        menuBar.add(menuLibros);

        // Prestamos
        JMenu menuPrestamos = new JMenu("Préstamos");

        JMenuItem itemNuevoPrestamo = new JMenuItem("Registrar Préstamo");
        itemNuevoPrestamo.addActionListener(e -> mostrarPanel(new JPPrestamos(usuarioActual)));
        menuPrestamos.add(itemNuevoPrestamo);

        JMenuItem itemListarActivos = new JMenuItem("Préstamos Registrados");
        itemListarActivos.addActionListener(e -> mostrarPanel(new JPPrestamos(usuarioActual)));
        menuPrestamos.add(itemListarActivos);

        menuBar.add(menuPrestamos);

        // Devoluciones
        JMenu menuDevoluciones = new JMenu("Devoluciones");

        JMenuItem itemDevolucion = new JMenuItem("Registrar Devolución");
        itemDevolucion.addActionListener(e -> mostrarPanel(new JPDevoluciones()));
        menuDevoluciones.add(itemDevolucion);

        JMenuItem itemHistorial = new JMenuItem("Historial de Préstamos");
        itemHistorial.addActionListener(e -> mostrarPanel(new JPHistorial()));
        menuDevoluciones.add(itemHistorial);

        menuBar.add(menuDevoluciones);

        // Cerrar sesión
        JMenu menuSesion = new JMenu("Sesión");
        JMenuItem itemCerrar = new JMenuItem("Cerrar Sesión");
        itemCerrar.addActionListener(e -> {
            new JFLogin().setVisible(true);
            dispose();
        });
        menuSesion.add(itemCerrar);
        menuBar.add(menuSesion);

        return menuBar;
    }
    
    /**
     * Muestra un panel en el área central de la ventana,
     * reemplazando el contenido anterior.
     *
     * @param panel Panel a mostrar.
     */
    public void mostrarPanel(JPanel panel) {
        panelContenido.removeAll();
        panelContenido.add(panel, BorderLayout.CENTER);
        panelContenido.revalidate();
        panelContenido.repaint();
    }
}
