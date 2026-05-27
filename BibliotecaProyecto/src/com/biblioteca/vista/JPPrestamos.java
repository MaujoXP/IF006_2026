package com.biblioteca.vista;

import com.biblioteca.controlador.PrestamoControlador;
import com.biblioteca.modelo.PrestamoLibro;
import com.biblioteca.modelo.Usuario;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


/**
 * Panel de gestión de préstamos de la biblioteca.
 * 
 * Permite realizar las siguientes operaciones:
 * - Registrar nuevos préstamos de libros.
 * - Consultar préstamos activos con días restantes.
 * - Consultar préstamos inactivos con multas aplicadas.
 * 
 * Integra la lógica de negocio a través de {@link PrestamoControlador}.
 * 
 * Columnas mostradas:
 * Usuario, ISBN, Libro, Fechas de inicio/fin, Costo, Días restantes o Multa.
 * 
 * @author Mauricio León - C5G444
 * @version 1.0
 */
public class JPPrestamos extends JPanel {

    private PrestamoControlador controlador;
    private Usuario usuarioActual;

    private JTextField txtUsuarioPrestamo, txtIsbnPrestamo, txtFechaInicio, txtFechaFin;
    private JLabel lblCostoEstimado;
    private JTable tablaActivos;
    private DefaultTableModel modeloActivos;
    private JTable tablaInactivos;
    private DefaultTableModel modeloInactivos;

    /**
     * Crea el panel de gestión de préstamos.
     *
     * @param usuarioActual Usuario autenticado en sesión.
     */
    public JPPrestamos(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
        this.controlador = new PrestamoControlador();
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        initComponents();
        cargarPrestamosActivos();
    }
    
    /**
     * Inicializa las pestañas del panel:
     * - Registrar Préstamo
     * - Préstamos Activos
     * - Préstamos Inactivos
     */
    private void initComponents() {
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Registrar Préstamo", crearPanelRegistro());
        tabs.addTab("Préstamos Activos", crearPanelActivos());
        tabs.addTab("Préstamos Inactivos", crearPanelInactivos());
        add(tabs, BorderLayout.CENTER);
    }
    
    /**
     * Construye el panel de registro de nuevos préstamos.
     *
     * @return Panel con formulario de registro.
     */
    private JPanel crearPanelRegistro() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new TitledBorder("Nuevo Préstamo"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        int row = 0;

        txtUsuarioPrestamo = addField(panel, gbc, row++, "Usuario (nombre de login):");
        txtIsbnPrestamo = addField(panel, gbc, row++, "ISBN del libro:");
        txtFechaInicio = addField(panel, gbc, row++, "Fecha inicio (YYYY-MM-DD):");
        txtFechaFin = addField(panel, gbc, row++, "Fecha fin (YYYY-MM-DD):");

        // Sugerir fechas
        txtFechaInicio.setText(LocalDate.now().toString());
        txtFechaFin.setText(LocalDate.now().plusDays(7).toString());

        // Costo estimado
        JButton btnCalcular = new JButton("Calcular Costo Estimado");
        gbc.gridx = 0;
        gbc.gridy = row++;
        gbc.gridwidth = 2;
        panel.add(btnCalcular, gbc);

        lblCostoEstimado = new JLabel("Costo estimado: -");
        lblCostoEstimado.setFont(lblCostoEstimado.getFont().deriveFont(Font.BOLD));
        gbc.gridx = 0;
        gbc.gridy = row++;
        gbc.gridwidth = 2;
        panel.add(lblCostoEstimado, gbc);

        JButton btnRegistrar = new JButton("Registrar Préstamo");
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        panel.add(btnRegistrar, gbc);

        btnCalcular.addActionListener(e -> calcularCosto());
        btnRegistrar.addActionListener(e -> registrarPrestamo());

        return panel;
    }
    
    /**
     * Construye el panel de préstamos activos.
     *
     * @return Panel con tabla de préstamos activos.
     */
    private JPanel crearPanelActivos() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(new TitledBorder("Préstamos Activos"));

        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnRefrescar = new JButton("Refrescar");
        panelSuperior.add(btnRefrescar);
        btnRefrescar.addActionListener(e -> cargarPrestamosActivos());

        String[] cols = {"Usuario", "ISBN", "Libro", "Fecha Inicio", "Fecha Fin", "Costo (₡)", "Días Restantes"};
        modeloActivos = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };
        tablaActivos = new JTable(modeloActivos);
        tablaActivos.getTableHeader().setReorderingAllowed(false);
        JScrollPane scroll = new JScrollPane(tablaActivos);

        panel.add(panelSuperior, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);
        return panel;
    }
    
    /**
     * Construye el panel de préstamos inactivos.
     *
     * @return Panel con tabla de préstamos inactivos.
     */
    private JPanel crearPanelInactivos() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(new TitledBorder("Préstamos Inactivos"));

        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnRefrescar = new JButton("Refrescar");
        panelSuperior.add(btnRefrescar);
        btnRefrescar.addActionListener(e -> cargarPrestamosInactivos());

        String[] cols = {"Usuario", "ISBN", "Libro", "Fecha Inicio", "Fecha Fin", "Costo (₡)", "Multa (₡)"};
        modeloInactivos = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };
        tablaInactivos = new JTable(modeloInactivos);
        tablaInactivos.getTableHeader().setReorderingAllowed(false);
        JScrollPane scroll = new JScrollPane(tablaInactivos);

        panel.add(panelSuperior, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);
        return panel;
    }
    
    /**
     * Calcula el costo estimado de un préstamo según las fechas
     * ingresadas y lo muestra en el formulario.
     */
    private void calcularCosto() {
        try {
            LocalDate ini = LocalDate.parse(txtFechaInicio.getText().trim());
            LocalDate fin = LocalDate.parse(txtFechaFin.getText().trim());
            if (!fin.isAfter(ini)) {
                JOptionPane.showMessageDialog(this, "La fecha fin debe ser posterior a la de inicio.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int costo = controlador.calcularCosto(ini, fin);
            lblCostoEstimado.setText("Costo estimado: ₡" + costo + " ("
                    + java.time.temporal.ChronoUnit.DAYS.between(ini, fin) + " días × ₡350)");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Formato de fecha inválido (use YYYY-MM-DD).", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Registra un nuevo préstamo en la base de datos.
     * Valida los campos y muestra mensajes de error o éxito.
     */
    private void registrarPrestamo() {
        String usuario = txtUsuarioPrestamo.getText().trim();
        String isbnStr = txtIsbnPrestamo.getText().trim();

        if (usuario.isEmpty() || isbnStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Long isbn = Long.parseLong(isbnStr);
            LocalDate ini = LocalDate.parse(txtFechaInicio.getText().trim());
            LocalDate fin = LocalDate.parse(txtFechaFin.getText().trim());

            if (!fin.isAfter(ini)) {
                JOptionPane.showMessageDialog(this, "La fecha fin debe ser posterior a la de inicio.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int resultado = controlador.registrarPrestamo(usuario, isbn, ini, fin);

            if (resultado > 0) {
                int costo = controlador.calcularCosto(ini, fin);
                JOptionPane.showMessageDialog(this,
                        "Préstamo registrado exitosamente.\nID: " + resultado
                        + "\nCosto total: ₡" + costo
                        + "\nFecha devolución: " + fin);
                limpiarFormulario();
                cargarPrestamosActivos();
            } else if (resultado == -2) {
                JOptionPane.showMessageDialog(this, "El libro no está disponible (sin ejemplares).", "Sin disponibilidad", JOptionPane.WARNING_MESSAGE);
            } else if (resultado == -3) {
                JOptionPane.showMessageDialog(this, "Usuario no encontrado: " + usuario, "Error", JOptionPane.ERROR_MESSAGE);
            } else if (resultado == -4) {
                JOptionPane.showMessageDialog(this, "Libro no encontrado con ISBN: " + isbn, "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar préstamo.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El ISBN debe ser un número.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Carga los préstamos activos desde el controlador y los
     * muestra en la tabla, incluyendo días restantes o vencidos.
     */
    private void cargarPrestamosActivos() {
        modeloActivos.setRowCount(0);
        List<PrestamoLibro> lista = controlador.listarActivos();
        LocalDate hoy = LocalDate.now();
        for (PrestamoLibro p : lista) {
            long diasRestantes = java.time.temporal.ChronoUnit.DAYS.between(hoy, p.getFechaFin());
            String diasStr = diasRestantes >= 0 ? String.valueOf(diasRestantes) : "VENCIDO (" + Math.abs(diasRestantes) + " días)";
            modeloActivos.addRow(new Object[]{
                p.getUsuario().getUsuario(),
                p.getLibro().getIsbn(),
                p.getLibro().getNombre(),
                p.getFechaInicio(),
                p.getFechaFin(),
                "₡" + p.getCostoTotal(),
                diasStr
            });
        }
    }
    
    /**
     * Carga los préstamos inactivos desde el controlador y los
     * muestra en la tabla, incluyendo multas aplicadas.
     */
    private void cargarPrestamosInactivos() {
        modeloInactivos.setRowCount(0);
        List<PrestamoLibro> lista = controlador.listarInactivos();
        for (PrestamoLibro p : lista) {
            modeloInactivos.addRow(new Object[]{
                p.getUsuario().getUsuario(),
                p.getLibro().getIsbn(),
                p.getLibro().getNombre(),
                p.getFechaInicio(),
                p.getFechaFin(),
                "₡" + p.getCostoTotal(),
                "₡" + p.getMulta()
            });
        }
    }
    
    /**
     * Agrega un par etiqueta + campo de texto a un panel con
     * {@link GridBagLayout}.
     *
     * @param panel Panel destino.
     * @param gbc Restricciones de layout activas.
     * @param row Fila donde se colocará el campo.
     * @param label Texto de la etiqueta.
     * @return El {@link JTextField} creado.
     */
    private JTextField addField(JPanel panel, GridBagConstraints gbc, int row, String label) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        panel.add(new JLabel(label), gbc);
        JTextField tf = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = row;
        panel.add(tf, gbc);
        return tf;
    }
    
    /**
     * Limpia los campos del formulario de registro de préstamos.
     */
    private void limpiarFormulario() {
        txtUsuarioPrestamo.setText("");
        txtIsbnPrestamo.setText("");
        txtFechaInicio.setText(LocalDate.now().toString());
        txtFechaFin.setText(LocalDate.now().plusDays(7).toString());
        lblCostoEstimado.setText("Costo estimado: -");
    }
}
