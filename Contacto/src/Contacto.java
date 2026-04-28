/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Gaby
 */
public class Contacto {

    private String iD;
    private String nombre;
    private String direccion;
    private String telefono;
    private String correo;
    private String fechaNacimiento;
    private int tipo;//amigo, familia, conocido
    private int estado;/*0.Activo,
                       1.Inactivo, 
                       2.Bloqueado,
                       3.Eliminado */
    public Contacto(String iDnuevo,
            String nombre,
            String direccion,
            String telefono,
            String correo,
            String fNacimiento) {
        this.iD = iDnuevo;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.fechaNacimiento = fNacimiento;
        this.tipo = 0;
        this.estado = 0;
    }

    public Contacto(String iDnuevo,
            String nombre,
            String direccion,
            String telefono,
            String correo,
            String fNacimiento, int tipo, int estado) {
        this.iD = iDnuevo;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.fechaNacimiento = fNacimiento;
        this.tipo = tipo;
        this.estado = estado;
    }

    public String getiD() {
        return iD;
    }

    public void setiD(String iD) {
        this.iD = iD;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String toString() {
        String sal = " Informacion  Registro Contacto \n";
        sal += "\n Identificacion .......:" + this.iD;
        sal += "\n nombre................:" + this.nombre;
        sal += "\n direccion.............:" + this.direccion;
        sal += "\n telefono..............:" + this.telefono;
        sal += "\n correo................:" + this.correo;
        sal += "\n Fecha de Nacimiento...:" + this.fechaNacimiento;
        sal += "\n Tipo..................:" + this.tipo;
        sal += "\n Estado................:" + this.estado;

        return sal;
    }

    public String toDetalle() {
        String sal = "";
        sal += "\t " + this.iD;
        sal += "\t " + this.nombre;
        sal += "\t " + this.direccion;
        sal += "\t " + this.telefono;
        sal += "\t " + this.correo;
        sal += "\t " + this.fechaNacimiento;
        sal += "\t " + this.tipo;
        sal += "\t " + this.estado;
        sal += "\n ";
        return sal;
    }

    public String toDetalle2() {
        String sal = "";
        sal += "[" + this.iD + "]";
        sal += "[" + this.nombre + "]";
        sal += "[" + this.telefono + "]";
        sal += "[" + this.tipo + "]";
        sal += "[" + this.estado + "]\n";
        return sal;
    }

    public String toDetalleListaTelefonos() {
        String sal = "";
        sal += "\t " + this.iD;
        sal += "\t " + this.nombre;
        sal += "\t\t\t" + this.telefono;
        sal += "\n ";
        return sal;
    }

    public String toDetalleListaCorreos() {
        String sal = "";
        sal += "\t " + this.iD;
        sal += "\t " + this.nombre;
        sal += "\t " + this.correo;
        sal += "\n ";
        return sal;
    }
}
