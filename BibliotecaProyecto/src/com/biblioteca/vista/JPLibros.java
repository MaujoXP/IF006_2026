package com.biblioteca.vista;

import com.biblioteca.controlador.LibroControlador;
import com.biblioteca.modelo.Libro;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Panel de gestión de libros de la biblioteca.
 * 
 * Permite realizar las siguientes operaciones:
 * - Listar y buscar libros por diferentes criterios.
 * - Registrar nuevos libros con datos de autor, categoría y editorial.
 * - Editar la cantidad disponible de ejemplares.
 * - Eliminar libros (si no tienen préstamos activos).
 * 
 * Integra la lógica de negocio a través de {@link LibroControlador}.
 * 
 * Columnas mostradas en la tabla:
 * ISBN, Nombre, Autor, Categoría, Editorial, Cantidad y Estado (Activo/Inactivo).
 * 
 * @author Mauricio León - C5G444
 * @version 1.0
 */
public class JPLibros extends JPanel {

    private LibroControlador controlador;
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private JComboBox<String> cmbCriterioBusqueda;
    private JTextField txtBusqueda;
    private JTextField txtIsbn, txtNombre, txtCantidad;
    private JComboBox<String> cmbCategoria, cmbEditorial;
    private JTextField txtAutorNombre, txtAutorNacionalidad, txtAutorSeudonimo, txtAutorFecha;
    private JTextField txtIsbnEditar, txtNuevaCantidad;
    
    /**
     * Crea el panel de gestión de libros.
     * Inicializa componentes y carga todos los registros.
     */
    public JPLibros() {
        this.controlador = new LibroControlador();
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        initComponents();
        cargarTabla(controlador.listarTodos());
    }
    
    /**
     * Inicializa las pestañas del panel:
     * - Listar/Buscar
     * - Registrar Libro
     * - Editar/Eliminar
     */
    private void initComponents() {
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Listar / Buscar", crearPanelLista());
        tabs.addTab("Registrar Libro", crearPanelRegistro());
        tabs.addTab("Editar / Eliminar", crearPanelEditar());
        add(tabs, BorderLayout.CENTER);
    }

    /**
     * Construye el panel de listado y búsqueda de libros.
     *
     * @return Panel con tabla y filtros de búsqueda.
     */
    private JPanel crearPanelLista() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));

        // Búsqueda
        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBusqueda.setBorder(new TitledBorder("Buscar Libros"));
        cmbCriterioBusqueda = new JComboBox<>(new String[]{"nombre", "autor", "categoria", "editorial", "isbn"});
        txtBusqueda = new JTextField(20);
        JButton btnBuscar = new JButton("Buscar");
        JButton btnMostrarTodos = new JButton("Mostrar Todos");

        panelBusqueda.add(new JLabel("Criterio:"));
        panelBusqueda.add(cmbCriterioBusqueda);
        panelBusqueda.add(new JLabel("Valor:"));
        panelBusqueda.add(txtBusqueda);
        panelBusqueda.add(btnBuscar);
        panelBusqueda.add(btnMostrarTodos);

        btnBuscar.addActionListener(e -> buscar());
        txtBusqueda.addActionListener(e -> buscar());
        btnMostrarTodos.addActionListener(e -> cargarTabla(controlador.listarTodos()));

        // Tabla
        String[] columnas = {"ISBN", "Nombre", "Autor", "Categoría", "Editorial", "Cantidad", "Activo"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tabla = new JTable(modeloTabla);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.getTableHeader().setReorderingAllowed(false);
        JScrollPane scroll = new JScrollPane(tabla);

        panel.add(panelBusqueda, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);
        return panel;
    }

    /**
     * Construye el panel de registro de nuevos libros.
     *
     * @return Panel con formulario de registro.
     */
    private JPanel crearPanelRegistro() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new TitledBorder("Nuevo Libro"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 8, 4, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        int row = 0;
        txtIsbn     = addField(panel, gbc, row++, "ISBN:");
        txtNombre   = addField(panel, gbc, row++, "Nombre del Libro:");
        txtCantidad = addField(panel, gbc, row++, "Cantidad:");

        gbc.gridx = 0; gbc.gridy = row;
        panel.add(new JLabel("Categoría:"), gbc);
        List<String> cats = controlador.getCategorias();
        cmbCategoria = new JComboBox<>(cats.toArray(new String[0]));
        gbc.gridx = 1; gbc.gridy = row++;
        panel.add(cmbCategoria, gbc);

        gbc.gridx = 0; gbc.gridy = row;
        panel.add(new JLabel("Editorial:"), gbc);
        List<String> eds = controlador.getEditoriales();
        cmbEditorial = new JComboBox<>(eds.toArray(new String[0]));
        gbc.gridx = 1; gbc.gridy = row++;
        panel.add(cmbEditorial, gbc);

        // Autor
        JLabel lblAutor = new JLabel("-- Datos del Autor --");
        lblAutor.setFont(lblAutor.getFont().deriveFont(Font.BOLD));
        gbc.gridx = 0; gbc.gridy = row++; gbc.gridwidth = 2;
        panel.add(lblAutor, gbc);
        gbc.gridwidth = 1;

        txtAutorNombre       = addField(panel, gbc, row++, "Nombre completo:");
        txtAutorNacionalidad = addField(panel, gbc, row++, "Nacionalidad:");
        txtAutorSeudonimo    = addField(panel, gbc, row++, "Seudónimo:");
        txtAutorFecha        = addField(panel, gbc, row++, "Fecha nacimiento (YYYY-MM-DD):");

        JButton btnRegistrar = new JButton("Registrar Libro");
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 2;
        panel.add(btnRegistrar, gbc);

        btnRegistrar.addActionListener(e -> registrarLibro());

        JScrollPane scroll = new JScrollPane(panel);
        scroll.setBorder(null);
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.add(scroll);
        return wrapper;
    }

    /**
     * Construye el panel de edición y eliminación de libros.
     *
     * @return Panel con formulario de edición.
     */
    private JPanel crearPanelEditar() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new TitledBorder("Editar Cantidad / Eliminar"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 10, 6, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        int row = 0;
        txtIsbnEditar    = addField(panel, gbc, row++, "ISBN del Libro:");
        txtNuevaCantidad = addField(panel, gbc, row++, "Nueva Cantidad:");

        JButton btnActualizar = new JButton("Actualizar Cantidad");
        gbc.gridx = 0; gbc.gridy = row++; gbc.gridwidth = 2;
        panel.add(btnActualizar, gbc);

        JButton btnEliminar = new JButton("Eliminar Libro");
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 2;
        panel.add(btnEliminar, gbc);

        btnActualizar.addActionListener(e -> actualizarCantidad());
        btnEliminar.addActionListener(e -> eliminarLibro());

        return panel;
    }

    /**
     * Ejecuta la búsqueda de libros según el criterio y valor ingresados.
     * Si no se ingresa valor, carga todos los libros.
     */
    private void buscar() {
        String criterio = (String) cmbCriterioBusqueda.getSelectedItem();
        String valor = txtBusqueda.getText().trim();
        if (valor.isEmpty()) {
            cargarTabla(controlador.listarTodos());
            return;
        }
        cargarTabla(controlador.buscarPorCriteria(criterio, valor));
    }
    
    /**
     * Registra un nuevo libro en la base de datos.
     * Valida los campos y muestra mensajes de error o éxito.
     */
    private void registrarLibro() {
        try {
            long isbn     = Long.parseLong(txtIsbn.getText().trim());
            String nom   = txtNombre.getText().trim();
            int cant     = Integer.parseInt(txtCantidad.getText().trim());
            String cat   = ((String) cmbCategoria.getSelectedItem()).toUpperCase().replace(" ", "_").replace("Á","A").replace("É","E").replace("Í","I").replace("Ó","O").replace("Ú","U");
            String ed    = ((String) cmbEditorial.getSelectedItem()).toUpperCase();
            String aNom  = txtAutorNombre.getText().trim();
            String aNac  = txtAutorNacionalidad.getText().trim();
            String aSeu  = txtAutorSeudonimo.getText().trim();
            LocalDate aFecha = LocalDate.parse(txtAutorFecha.getText().trim(), DateTimeFormatter.ISO_LOCAL_DATE);

            int resultado = controlador.registrarLibro(isbn, nom, cant, cat, ed, aNom, aNac, aSeu, aFecha);
            if (resultado > 0) {
                JOptionPane.showMessageDialog(this, "Libro registrado. ID: " + resultado);
                limpiarRegistro();
                cargarTabla(controlador.listarTodos());
            } else if (resultado == -2) {
                JOptionPane.showMessageDialog(this, "Complete todos los campos.", "Aviso", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar libro. Verifique que el ISBN no exista.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ISBN y Cantidad deben ser números.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Actualiza la cantidad disponible de un libro según su ISBN.
     * Muestra mensajes de confirmación o error.
     */
    private void actualizarCantidad() {
        try {
            long isbn = Long.parseLong(txtIsbnEditar.getText().trim());
            int cantidad = Integer.parseInt(txtNuevaCantidad.getText().trim());
            if (cantidad < 0) { JOptionPane.showMessageDialog(this, "Cantidad no puede ser negativa."); return; }
            boolean ok = controlador.actualizarCantidad(isbn, cantidad);
            if (ok) {
                JOptionPane.showMessageDialog(this, "Cantidad actualizada.");
                cargarTabla(controlador.listarTodos());
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró el libro con ISBN: " + isbn, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese ISBN y cantidad válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Elimina un libro de la base de datos.
     * Solicita confirmación y valida que no tenga préstamos activos.
     */
    private void eliminarLibro() {
        String isbnStr = txtIsbnEditar.getText().trim();
        try {
            long isbn = Long.parseLong(isbnStr);
            Libro libro = controlador.buscarPorIsbn(isbn);
            if (libro == null) { JOptionPane.showMessageDialog(this, "Libro no encontrado."); return; }

            int confirm = JOptionPane.showConfirmDialog(this,
                "¿Eliminar libro '" + libro.getNombre() + "' (ISBN " + isbn + ")?",
                "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (confirm == JOptionPane.YES_OPTION) {
                boolean ok = controlador.eliminarLibro(isbn);
                if (ok) {
                    JOptionPane.showMessageDialog(this, "Libro eliminado.");
                    cargarTabla(controlador.listarTodos());
                    txtIsbnEditar.setText(""); txtNuevaCantidad.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar. El libro puede tener préstamos activos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un ISBN válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Carga la lista de libros en la tabla.
     *
     * @param libros Lista de libros a mostrar.
     */
    private void cargarTabla(List<Libro> libros) {
        modeloTabla.setRowCount(0);
        for (Libro l : libros) {
            modeloTabla.addRow(new Object[]{
                l.getIsbn(),
                l.getNombre(),
                l.getAutor() != null ? l.getAutor().getNombreCompleto() : "-",
                l.getCategoria() != null ? l.getCategoria().getDescripcion() : "-",
                l.getEditorial() != null ? l.getEditorial().getNombre() : "-",
                l.getCantidad(),
                l.isActivo() ? "Sí" : "No"
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
        gbc.gridx = 0; gbc.gridy = row;
        panel.add(new JLabel(label), gbc);
        JTextField tf = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = row;
        panel.add(tf, gbc);
        return tf;
    }
    
    /**
     * Limpia los campos del formulario de registro de libros.
     */
    private void limpiarRegistro() {
        txtIsbn.setText(""); txtNombre.setText(""); txtCantidad.setText("");
        txtAutorNombre.setText(""); txtAutorNacionalidad.setText("");
        txtAutorSeudonimo.setText(""); txtAutorFecha.setText("");
    }
}
