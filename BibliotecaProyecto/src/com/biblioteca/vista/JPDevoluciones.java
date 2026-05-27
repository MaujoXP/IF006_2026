package com.biblioteca.vista;

import com.biblioteca.controlador.PrestamoControlador;
import com.biblioteca.modelo.PrestamoLibro;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Panel de gestión de devoluciones de préstamos de libros.
 * 
 * Permite registrar devoluciones, simular multas por atraso y
 * consultar los préstamos activos para referencia.
 * 
 * Incluye:
 * - Formulario de devolución con cálculo de multa.
 * - Tabla de préstamos activos con autocompletado de campos.
 * - Integración con {@link PrestamoControlador} para operaciones
 *   de negocio.
 * 
 * @author Mauricio León - C5G444
 * @version 1.0
 */
public class JPDevoluciones extends JPanel {

    private PrestamoControlador controlador;

    private JTextField txtUsuarioDevol, txtIsbnDevol, txtFechaDevol;
    private JLabel lblInfoPrestamo, lblMultaSimulada;
    private JTable tablaActivos;
    private DefaultTableModel modeloTabla;
    
    /**
     * Crea el panel de devoluciones.
     * Inicializa componentes y carga préstamos activos.
     */
    public JPDevoluciones() {
        this.controlador = new PrestamoControlador();
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        initComponents();
        cargarPrestamosActivos();
    }
    
    /**
     * Inicializa los componentes gráficos del panel:
     * - Formulario de devolución.
     * - Tabla de préstamos activos.
     */
    private void initComponents() {
        // Panel superior: formulario
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBorder(new TitledBorder("Registrar Devolución"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        int row = 0;
        txtUsuarioDevol = addField(panelFormulario, gbc, row++, "Usuario (nombre de login):");
        txtIsbnDevol    = addField(panelFormulario, gbc, row++, "ISBN del libro:");
        txtFechaDevol   = addField(panelFormulario, gbc, row++, "Fecha devolución (YYYY-MM-DD):");
        txtFechaDevol.setText(LocalDate.now().toString());

        JButton btnSimular = new JButton("Simular Multa");
        gbc.gridx = 0; gbc.gridy = row++; gbc.gridwidth = 2;
        panelFormulario.add(btnSimular, gbc);

        lblInfoPrestamo = new JLabel("Préstamo: -");
        gbc.gridx = 0; gbc.gridy = row++; gbc.gridwidth = 2;
        panelFormulario.add(lblInfoPrestamo, gbc);

        lblMultaSimulada = new JLabel("Multa calculada: -");
        lblMultaSimulada.setFont(lblMultaSimulada.getFont().deriveFont(Font.BOLD));
        gbc.gridx = 0; gbc.gridy = row++; gbc.gridwidth = 2;
        panelFormulario.add(lblMultaSimulada, gbc);

        JButton btnDevolver = new JButton("Confirmar Devolución");
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 2;
        panelFormulario.add(btnDevolver, gbc);

        btnSimular.addActionListener(e -> simularMulta());
        btnDevolver.addActionListener(e -> registrarDevolucion());

        // Panel inferior: tabla de activos
        JPanel panelTabla = new JPanel(new BorderLayout(5, 5));
        panelTabla.setBorder(new TitledBorder("Préstamos Activos (para referencia)"));

        JPanel panelBtns = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnRefrescar = new JButton("Refrescar");
        panelBtns.add(btnRefrescar);
        btnRefrescar.addActionListener(e -> cargarPrestamosActivos());

        String[] cols = {"Usuario", "ISBN", "Libro", "Fecha Inicio", "Fecha Fin", "Costo (₡)"};
        modeloTabla = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tablaActivos = new JTable(modeloTabla);
        tablaActivos.getTableHeader().setReorderingAllowed(false);

        // Al seleccionar fila, autocompletar campos
        tablaActivos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row2 = tablaActivos.getSelectedRow();
                if (row2 >= 0) {
                    txtUsuarioDevol.setText((String) modeloTabla.getValueAt(row2, 0));
                    txtIsbnDevol.setText(String.valueOf(modeloTabla.getValueAt(row2, 1)));
                }
            }
        });

        panelTabla.add(panelBtns, BorderLayout.NORTH);
        panelTabla.add(new JScrollPane(tablaActivos), BorderLayout.CENTER);

        JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panelFormulario, panelTabla);
        split.setResizeWeight(0.4);
        add(split, BorderLayout.CENTER);
    }
    
    /**
     * Simula la multa de un préstamo según la fecha de devolución
     * ingresada. Muestra el resultado en el formulario.
     */
    private void simularMulta() {
        String usuario = txtUsuarioDevol.getText().trim();
        String isbnStr = txtIsbnDevol.getText().trim();
        if (usuario.isEmpty() || isbnStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese usuario e ISBN.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            long isbn = Long.parseLong(isbnStr);
            LocalDate fechaDevolucion = LocalDate.parse(txtFechaDevol.getText().trim());

            // Buscar préstamo activo
            List<PrestamoLibro> activos = controlador.listarActivos();
            PrestamoLibro prestamo = null;
            for (PrestamoLibro p : activos) {
                if (p.getUsuario().getUsuario().equals(usuario) && p.getLibro().getIsbn() == isbn) {
                    prestamo = p; break;
                }
            }
            if (prestamo == null) {
                lblInfoPrestamo.setText("No se encontró préstamo activo para ese usuario/libro.");
                lblMultaSimulada.setText("Multa calculada: -");
                return;
            }

            int multa = controlador.calcularMultaSimulada(prestamo.getFechaFin(), fechaDevolucion);
            lblInfoPrestamo.setText("Préstamo encontrado | Fecha fin: " + prestamo.getFechaFin() +
                " | Costo original: ₡" + prestamo.getCostoTotal());
            if (multa > 0) {
                long dias = java.time.temporal.ChronoUnit.DAYS.between(prestamo.getFechaFin(), fechaDevolucion);
                lblMultaSimulada.setText("Multa: ₡" + multa + " (" + dias + " días de atraso × ₡600)");
                lblMultaSimulada.setForeground(Color.RED);
            } else {
                lblMultaSimulada.setText("Sin multa (devolución a tiempo).");
                lblMultaSimulada.setForeground(new Color(0, 128, 0));
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ISBN debe ser un número.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Registra la devolución de un préstamo en la base de datos.
     * Calcula y aplica la multa si corresponde.
     */
    private void registrarDevolucion() {
        String usuario = txtUsuarioDevol.getText().trim();
        String isbnStr = txtIsbnDevol.getText().trim();
        if (usuario.isEmpty() || isbnStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete usuario e ISBN.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            long isbn = Long.parseLong(isbnStr);
            LocalDate fechaDevolucion = LocalDate.parse(txtFechaDevol.getText().trim());

            int resultado = controlador.registrarDevolucion(usuario, isbn, fechaDevolucion);

            if (resultado >= 0) {
                String msg = "Devolución registrada exitosamente.\n";
                if (resultado > 0) {
                    msg += "Multa por atraso: ₡" + resultado;
                } else {
                    msg += "Sin multa.";
                }
                JOptionPane.showMessageDialog(this, msg, "Devolución OK", JOptionPane.INFORMATION_MESSAGE);
                txtUsuarioDevol.setText(""); txtIsbnDevol.setText("");
                txtFechaDevol.setText(LocalDate.now().toString());
                lblInfoPrestamo.setText("Préstamo: -");
                lblMultaSimulada.setText("Multa calculada: -");
                lblMultaSimulada.setForeground(Color.BLACK);
                cargarPrestamosActivos();
            } else if (resultado == -2) {
                JOptionPane.showMessageDialog(this, "No se encontró préstamo activo para ese usuario/libro.", "Aviso", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar devolución.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ISBN debe ser un número.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Carga los préstamos activos desde el controlador y los
     * muestra en la tabla de referencia.
     */
    private void cargarPrestamosActivos() {
        modeloTabla.setRowCount(0);
        for (PrestamoLibro p : controlador.listarActivos()) {
            modeloTabla.addRow(new Object[]{
                p.getUsuario().getUsuario(),
                p.getLibro().getIsbn(),
                p.getLibro().getNombre(),
                p.getFechaInicio(),
                p.getFechaFin(),
                "₡" + p.getCostoTotal()
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
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 1;
        panel.add(new JLabel(label), gbc);
        JTextField tf = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = row;
        panel.add(tf, gbc);
        return tf;
    }
}
