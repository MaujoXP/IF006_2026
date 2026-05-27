package Buses;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Hp
 */
/*Clase Chofer*/
/**
 */
public class Chofer {
    //atributos o caracteristicas

    private String alias;
    private String licencia;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String direccion;
    private String fecha_de_nacimiento;
    private int añosExperiencia;
    private String telefono;

    //metodos constructores
    public Chofer() {
        this.alias = "PERICA";
        this.licencia = "701960017";
        this.nombre = "Don Jose";
        this.apellido1 = "Porras";
        this.apellido2 = "Nuñez";
        this.direccion = "Colina";
        this.añosExperiencia = 1;
        this.telefono = "88888888";
        this.fecha_de_nacimiento = "04/01/64";
    }

    public Chofer(String licencia, String apellido, String apellido2,
            String nombre, String alias) {
        this.alias = alias;
        this.licencia = licencia;
        this.nombre = nombre;
        this.apellido1 = apellido;
        this.apellido2 = apellido2;
        this.direccion = "sin reportar";
        this.añosExperiencia = 0;
        this.telefono = "88888888";
        this.fecha_de_nacimiento = "??/??/??";
    }

    public Chofer(String nombre, String apellido1, String apellido2, String licencia, String fecha,
            String direccion, int años, String telefono, String alias) {
        this.licencia = licencia;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.direccion = direccion;
        this.añosExperiencia = años;
        this.telefono = telefono;
        this.fecha_de_nacimiento = fecha;
        this.alias = alias;
    }

    //metodos set y get
    public Chofer(Chofer nuevo) {
        this.licencia = nuevo.licencia;
        this.nombre = nuevo.nombre;
        this.apellido1 = nuevo.apellido1;
        this.apellido2 = nuevo.apellido2;
        this.direccion = nuevo.direccion;
        this.añosExperiencia = nuevo.añosExperiencia;
        this.telefono = nuevo.telefono;
        this.fecha_de_nacimiento = nuevo.fecha_de_nacimiento;
        this.alias = nuevo.alias;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    public String getLicencia() {
        return licencia;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return this.alias;
    }

    public void setApellido(String apellido) {
        this.apellido1 = apellido;
    }

    public String getApellido1() {
        return this.apellido1;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getApellido2() {
        return this.apellido2;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setFechaNacimiento(String fecha) {
        this.fecha_de_nacimiento = fecha;
    }

    public String getFecha() {
        return this.fecha_de_nacimiento;
    }

    public void setAñosExperiencia(int años) {
        this.añosExperiencia = años;
    }

    public int getAñosExperiencia() {
        return this.añosExperiencia;
    }

    public String registroChofer() {
        String salida = "";
        salida += getAlias() + ",";
        salida += getLicencia() + ",";
        salida += getNombre() + ",";
        salida += getApellido1() + ",";
        salida += getApellido2() + ",";
        salida += getDireccion() + ",";
        salida += getFecha() + ",";
        salida += getAñosExperiencia() + ",";
        salida += getTelefono();
        return salida;
    }

    public Chofer registroCampos(String registro) {
        String campos[] = registro.split(",");
        return new Chofer(campos[2], campos[3], campos[4], campos[1], campos[6], campos[5], Integer.parseInt(campos[7]), campos[8], campos[0]);
    }

    //metodo toString
    @Override
    public String toString() {
        String sal = "";
        sal += "Nombre: " + this.nombre + " " + this.apellido1 + " " + apellido2
                + "Alias: " + this.alias;
        return sal + "\n";
    }

    public String toString2() {
        String sal = "";
        sal += " " + this.licencia + "\t\t" + this.apellido1 + "\t\t" + apellido2 + "\t\t" + this.nombre + "\t"
                + this.alias;
        return sal + "\n";
    }

}//fin de la clase chofer
