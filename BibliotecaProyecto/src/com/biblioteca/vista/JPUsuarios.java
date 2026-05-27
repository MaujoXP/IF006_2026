package com.biblioteca.vista;

import com.biblioteca.controlador.UsuarioControlador;
import com.biblioteca.modelo.Usuario;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Panel de gestión de usuarios del sistema.
 * 
 * @author Mauricio León - C5G444
 * @version 1.0
 */
public class JPUsuarios extends JPanel {

    private UsuarioControlador controlador;
    private Usuario usuarioActual;

    // Campos registro
    private JTextField txtCedula, txtNombre, txtApellido, txtDireccion, txtTelefono;
    private JTextField txtUsuario, txtContrasenia;
    private JComboBox<String> cmbRol;

    // Campos edición
    private JTextField txtBuscarUsuario;
    private JTextField txtEditNombre, txtEditApellido, txtEditDireccion, txtEditTelefono;
    private JTextField txtEditUsuario, txtEditContrasenia;
    private JCheckBox chkActivo;
    private JLabel lblEstado;
    private String usuarioOriginalEnEdicion;

    /**
     * Crea el panel de gestión de usuarios.
     *
     * @param usuarioActual Usuario autenticado en sesión (para referencia
     * futura).
     */
    public JPUsuarios(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
        this.controlador = new UsuarioControlador();
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        initComponents();
    }

    /**
     * Inicializa las pestañas del panel.
     */
    private void initComponents() {
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Registrar Usuario", crearPanelRegistro());
        tabs.addTab("Editar / Activar / Desactivar", crearPanelEdicion());
        add(tabs, BorderLayout.CENTER);
    }

    /**
     * Construye el panel de registro de nuevos usuarios.
     */
    private JPanel crearPanelRegistro() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new TitledBorder("Nuevo Usuario"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 8, 4, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        int row = 0;

        JLabel lblSec1 = new JLabel("-- Datos Personales --");
        lblSec1.setFont(lblSec1.getFont().deriveFont(Font.BOLD));
        gbc.gridx = 0;
        gbc.gridy = row++;
        gbc.gridwidth = 2;
        panel.add(lblSec1, gbc);
        gbc.gridwidth = 1;

        txtCedula = addField(panel, gbc, row++, "Cédula (mín. 9 dígitos):");
        txtNombre = addField(panel, gbc, row++, "Nombre (mín. 2 caracteres):");
        txtApellido = addField(panel, gbc, row++, "Apellido (mín. 2 caracteres):");
        txtDireccion = addField(panel, gbc, row++, "Dirección:");
        txtTelefono = addField(panel, gbc, row++, "Teléfono (mín. 8 dígitos):");

        JLabel lblSec2 = new JLabel("-- Datos de Acceso --");
        lblSec2.setFont(lblSec2.getFont().deriveFont(Font.BOLD));
        gbc.gridx = 0;
        gbc.gridy = row++;
        gbc.gridwidth = 2;
        panel.add(lblSec2, gbc);
        gbc.gridwidth = 1;

        txtUsuario = addField(panel, gbc, row++, "Usuario (mín. 3 caracteres, sin espacios):");
        txtContrasenia = addField(panel, gbc, row++, "Contraseña (mín. 4 caracteres):");

        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel("Rol:"), gbc);
        cmbRol = new JComboBox<>(new String[]{"ADMIN (1)", "CLIENTE (2)"});
        gbc.gridx = 1;
        gbc.gridy = row++;
        panel.add(cmbRol, gbc);

        JButton btnRegistrar = new JButton("Registrar Usuario");
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        panel.add(btnRegistrar, gbc);

        btnRegistrar.addActionListener(e -> registrarUsuario());
        return panel;
    }

    /**
     * Construye el panel de edición, activación/desactivación y eliminación de
     * usuarios.
     */
    private JPanel crearPanelEdicion() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));

        JPanel panelBuscar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBuscar.setBorder(new TitledBorder("Buscar Usuario"));
        txtBuscarUsuario = new JTextField(20);
        JButton btnBuscar = new JButton("Buscar");
        panelBuscar.add(new JLabel("Nombre de usuario:"));
        panelBuscar.add(txtBuscarUsuario);
        panelBuscar.add(btnBuscar);

        JPanel panelEditar = new JPanel(new GridBagLayout());
        panelEditar.setBorder(new TitledBorder("Datos del Usuario"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 8, 4, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        int row = 0;
        txtEditNombre = addField(panelEditar, gbc, row++, "Nombre:");
        txtEditApellido = addField(panelEditar, gbc, row++, "Apellido:");
        txtEditDireccion = addField(panelEditar, gbc, row++, "Dirección:");
        txtEditTelefono = addField(panelEditar, gbc, row++, "Teléfono:");
        txtEditUsuario = addField(panelEditar, gbc, row++, "Usuario:");
        txtEditContrasenia = addField(panelEditar, gbc, row++, "Contraseña (nueva, dejar vacío para no cambiar):");

        gbc.gridx = 0;
        gbc.gridy = row;
        panelEditar.add(new JLabel("Estado:"), gbc);
        chkActivo = new JCheckBox("Activo");
        gbc.gridx = 1;
        gbc.gridy = row++;
        panelEditar.add(chkActivo, gbc);

        lblEstado = new JLabel("");
        lblEstado.setForeground(Color.BLUE);
        gbc.gridx = 0;
        gbc.gridy = row++;
        gbc.gridwidth = 2;
        panelEditar.add(lblEstado, gbc);

        JPanel panelBotones = new JPanel(new FlowLayout());
        JButton btnGuardar = new JButton("Guardar Cambios");
        JButton btnToggle = new JButton("Activar / Desactivar");
        JButton btnEliminar = new JButton("Eliminar Usuario");
        panelBotones.add(btnGuardar);
        panelBotones.add(btnToggle);
        panelBotones.add(btnEliminar);

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        panelEditar.add(panelBotones, gbc);

        setEdicionHabilitado(false);

        btnBuscar.addActionListener(e -> buscarUsuarioParaEditar());
        txtBuscarUsuario.addActionListener(e -> buscarUsuarioParaEditar());
        btnGuardar.addActionListener(e -> guardarCambios());
        btnToggle.addActionListener(e -> toggleActivo());
        btnEliminar.addActionListener(e -> eliminarUsuario());

        panel.add(panelBuscar, BorderLayout.NORTH);
        panel.add(panelEditar, BorderLayout.CENTER);
        return panel;
    }

    /**
     * Recoge los datos del formulario, los valida a través del controlador y
     * muestra el resultado al usuario.
     */
    private void registrarUsuario() {
        String cedula = txtCedula.getText().trim();
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String dir = txtDireccion.getText().trim();
        String tel = txtTelefono.getText().trim();
        String usuario = txtUsuario.getText().trim();
        String contra = txtContrasenia.getText().trim();
        int idRol = cmbRol.getSelectedIndex() + 1;

        int resultado = controlador.registrarUsuario(cedula, nombre, apellido, dir, tel, usuario, contra, idRol);
        switch (resultado) {
            case -2:
                JOptionPane.showMessageDialog(this,
                        "Campos vacíos o incompletos.", "Aviso", JOptionPane.WARNING_MESSAGE);
                break;
            case -3:
                JOptionPane.showMessageDialog(this,
                        "Cédula inválida.\nDebe contener solo dígitos: 9 para cédulas costarricenses, hasta 20 para cédulas extranjeras.",
                        "Error de validación", JOptionPane.WARNING_MESSAGE);
                break;
            case -4:
                JOptionPane.showMessageDialog(this,
                        "Nombre inválido.\nDebe tener al menos 2 letras y no contener números.",
                        "Error de validación", JOptionPane.WARNING_MESSAGE);
                break;
            case -5:
                JOptionPane.showMessageDialog(this,
                        "Apellido inválido.\nDebe tener al menos 2 letras y no contener números.",
                        "Error de validación", JOptionPane.WARNING_MESSAGE);
                break;
            case -6:
                JOptionPane.showMessageDialog(this,
                        "Teléfono inválido.\nDebe tener entre 8 y 15 dígitos.",
                        "Error de validación", JOptionPane.WARNING_MESSAGE);
                break;
            case -7:
                JOptionPane.showMessageDialog(this,
                        "Nombre de usuario inválido.\nDebe tener al menos 3 caracteres y no puede contener espacios.",
                        "Error de validación", JOptionPane.WARNING_MESSAGE);
                break;
            case -8:
                JOptionPane.showMessageDialog(this,
                        "Contraseña inválida.\nDebe tener al menos 4 caracteres.",
                        "Error de validación", JOptionPane.WARNING_MESSAGE);
                break;
            case -1:
                JOptionPane.showMessageDialog(this,
                        "Error al registrar usuario.\nVerifique que la cédula y el nombre de usuario no estén ya registrados.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                break;
            default:
                JOptionPane.showMessageDialog(this, "Usuario registrado exitosamente. ID: " + resultado);
                limpiarRegistro();
        }
    }

    /**
     * Busca un usuario por nombre de login y carga sus datos en el formulario
     * de edición.
     */
    private void buscarUsuarioParaEditar() {
        String nombre = txtBuscarUsuario.getText().trim();
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el nombre de usuario.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Usuario u = controlador.buscarUsuario(nombre);
        if (u == null) {
            JOptionPane.showMessageDialog(this, "Usuario no encontrado.", "Aviso", JOptionPane.WARNING_MESSAGE);
            setEdicionHabilitado(false);
            return;
        }
        usuarioOriginalEnEdicion = u.getUsuario();
        txtEditNombre.setText(u.getNombre());
        txtEditApellido.setText(u.getApellido());
        txtEditDireccion.setText(u.getDireccion());
        txtEditTelefono.setText(u.getTelefono());
        txtEditUsuario.setText(u.getUsuario());
        txtEditContrasenia.setText("");
        chkActivo.setSelected(u.isActivo());
        lblEstado.setText("Cédula: " + u.getCedula() + "  |  Estado: " + (u.isActivo() ? "Activo" : "Inactivo"));
        setEdicionHabilitado(true);
    }

    /**
     * Guarda los cambios del formulario de edición en la base de datos.
     */
    private void guardarCambios() {
        if (usuarioOriginalEnEdicion == null) {
            return;
        }
        Usuario u = controlador.buscarUsuario(usuarioOriginalEnEdicion);
        if (u == null) {
            return;
        }

        u.setNombre(txtEditNombre.getText().trim());
        u.setApellido(txtEditApellido.getText().trim());
        u.setDireccion(txtEditDireccion.getText().trim());
        u.setTelefono(txtEditTelefono.getText().trim());
        u.setUsuario(txtEditUsuario.getText().trim());
        u.setActivo(chkActivo.isSelected());
        String nuevaContra = txtEditContrasenia.getText().trim();
        if (!nuevaContra.isEmpty()) {
            u.setContraseniaPlano(nuevaContra);
        }

        boolean ok = controlador.actualizarUsuario(u, usuarioOriginalEnEdicion);
        if (ok) {
            JOptionPane.showMessageDialog(this, "Usuario actualizado correctamente.");
            usuarioOriginalEnEdicion = u.getUsuario();
            lblEstado.setText("Cédula: " + u.getCedula() + "  |  Estado: " + (u.isActivo() ? "Activo" : "Inactivo"));
        } else {
            JOptionPane.showMessageDialog(this, "Error al actualizar usuario.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Alterna el estado activo/inactivo del usuario cargado en edición.
     */
    private void toggleActivo() {
        if (usuarioOriginalEnEdicion == null) {
            return;
        }
        boolean nuevoEstado = !chkActivo.isSelected();
        boolean ok = controlador.toggleActivo(usuarioOriginalEnEdicion, nuevoEstado);
        if (ok) {
            chkActivo.setSelected(nuevoEstado);
            lblEstado.setText("Estado actualizado: " + (nuevoEstado ? "Activo" : "Inactivo"));
            JOptionPane.showMessageDialog(this, "Estado actualizado a: " + (nuevoEstado ? "Activo" : "Inactivo"));
        } else {
            JOptionPane.showMessageDialog(this, "Error al cambiar estado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Solicita confirmación y elimina el usuario cargado en edición.
     */
    private void eliminarUsuario() {
        if (usuarioOriginalEnEdicion == null) {
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Está seguro de eliminar al usuario '" + usuarioOriginalEnEdicion + "'?",
                "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            Usuario u = controlador.buscarUsuario(usuarioOriginalEnEdicion);
            if (u != null && controlador.eliminarUsuario(u.getCedula())) {
                JOptionPane.showMessageDialog(this, "Usuario eliminado.");
                limpiarEdicion();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar usuario.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Agrega un par label+textfield a un panel con GridBagLayout.
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
        panel.add(new JLabel(label), gbc);
        JTextField tf = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = row;
        panel.add(tf, gbc);
        return tf;
    }

    /**
     * Habilita o deshabilita los campos del formulario de edición.
     *
     * @param enabled {@code true} para habilitar; {@code false} para
     * deshabilitar.
     */
    private void setEdicionHabilitado(boolean enabled) {
        txtEditNombre.setEnabled(enabled);
        txtEditApellido.setEnabled(enabled);
        txtEditDireccion.setEnabled(enabled);
        txtEditTelefono.setEnabled(enabled);
        txtEditUsuario.setEnabled(enabled);
        txtEditContrasenia.setEnabled(enabled);
        chkActivo.setEnabled(enabled);
    }

    /**
     * Limpia todos los campos del formulario de registro.
     */
    private void limpiarRegistro() {
        txtCedula.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtUsuario.setText("");
        txtContrasenia.setText("");
    }

    /**
     * Limpia el formulario de edición y deshabilita los campos.
     */
    private void limpiarEdicion() {
        txtEditNombre.setText("");
        txtEditApellido.setText("");
        txtEditDireccion.setText("");
        txtEditTelefono.setText("");
        txtEditUsuario.setText("");
        txtEditContrasenia.setText("");
        chkActivo.setSelected(false);
        lblEstado.setText("");
        usuarioOriginalEnEdicion = null;
        setEdicionHabilitado(false);
    }
}
