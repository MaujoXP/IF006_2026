package com.biblioteca.vista;

import com.biblioteca.controlador.PrestamoControlador;
import com.biblioteca.modelo.PrestamoLibro;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Panel de historial de préstamos de la biblioteca.
 * 
 * Permite consultar todos los préstamos realizados (activos e inactivos),
 * con opción de filtrar por usuario específico. 
 * 
 * Incluye:
 * - Filtro por nombre de usuario.
 * - Tabla con ordenamiento por columnas.
 * - Integración con {@link PrestamoControlador} para obtener datos.
 * 
 * Columnas mostradas:
 * Usuario, ISBN, Libro, Fechas de inicio/fin, Costo, Multa y Estado.
 * 
 * @author Mauricio León - C5G444
 * @version 1.0
 */
public class JPHistorial extends JPanel {

    private PrestamoControlador controlador;
    private JTable tabla;
    private DefaultTableModel modelo;
    private JTextField txtFiltroUsuario;
    
    /**
     * Crea el panel de historial de préstamos.
     * Inicializa componentes y carga todos los registros.
     */
    public JPHistorial() {
        this.controlador = new PrestamoControlador();
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        initComponents();
        cargarTodos();
    }
    
    /**
     * Inicializa los componentes gráficos del panel:
     * - Panel de filtros con campo de usuario.
     * - Botones de filtrado y mostrar todos.
     * - Tabla de historial con ordenamiento.
     */
    private void initComponents() {
        // Panel de filtros
        JPanel panelFiltro = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelFiltro.setBorder(new TitledBorder("Filtrar Historial"));

        txtFiltroUsuario = new JTextField(18);
        JButton btnFiltrar    = new JButton("Filtrar por Usuario");
        JButton btnMostrarTodos = new JButton("Mostrar Todos");

        panelFiltro.add(new JLabel("Usuario:"));
        panelFiltro.add(txtFiltroUsuario);
        panelFiltro.add(btnFiltrar);
        panelFiltro.add(btnMostrarTodos);

        btnFiltrar.addActionListener(e -> filtrarPorUsuario());
        txtFiltroUsuario.addActionListener(e -> filtrarPorUsuario());
        btnMostrarTodos.addActionListener(e -> cargarTodos());

        // Tabla
        String[] cols = {"Usuario", "ISBN", "Libro", "Fecha Inicio", "Fecha Fin", "Costo (₡)", "Multa (₡)", "Estado"};
        modelo = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tabla = new JTable(modelo);
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.setAutoCreateRowSorter(true);

        JPanel panelInfo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblNota = new JLabel("Haga clic en una columna para ordenar.");
        lblNota.setForeground(Color.GRAY);
        panelInfo.add(lblNota);

        add(panelFiltro, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
        add(panelInfo, BorderLayout.SOUTH);
    }
    
    /**
     * Carga todos los préstamos (activos e inactivos) desde el controlador
     * y los muestra en la tabla.
     */
    private void cargarTodos() {
        modelo.setRowCount(0);
        List<PrestamoLibro> lista = controlador.listarTodos();
        for (PrestamoLibro p : lista) {
            modelo.addRow(new Object[]{
                p.getUsuario().getUsuario(),
                p.getLibro().getIsbn(),
                p.getLibro().getNombre(),
                p.getFechaInicio(),
                p.getFechaFin(),
                "₡" + p.getCostoTotal(),
                "₡" + p.getMulta(),
                p.isActivo() ? "Activo" : "Devuelto"
            });
        }
    }
    
    /**
     * Filtra los préstamos por nombre de usuario ingresado en el campo
     * de texto. Si no se encuentra ningún préstamo, muestra un aviso.
     */
    private void filtrarPorUsuario() {
        String usuario = txtFiltroUsuario.getText().trim();
        if (usuario.isEmpty()) { cargarTodos(); return; }
        modelo.setRowCount(0);
        List<PrestamoLibro> lista = controlador.listarPorUsuario(usuario);
        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontraron préstamos para el usuario: " + usuario, "Sin resultados", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        for (PrestamoLibro p : lista) {
            modelo.addRow(new Object[]{
                p.getUsuario().getUsuario(),
                p.getLibro().getIsbn(),
                p.getLibro().getNombre(),
                p.getFechaInicio(),
                p.getFechaFin(),
                "₡" + p.getCostoTotal(),
                "₡" + p.getMulta(),
                p.isActivo() ? "Activo" : "Devuelto"
            });
        }
    }
}
