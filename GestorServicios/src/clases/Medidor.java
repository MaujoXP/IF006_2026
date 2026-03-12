/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

/**
 * Define el modelo Medidor
 * @author Gaby
 */
public class Medidor {
    private int idMedidor;
    private String codigo;
    private String unidadMedida;
    private int lecturaAnterior;
    private int lecturaActual;

    
    public Medidor() {
        
    }
    
    public Medidor(int idMedidor, String codigo, String unidadMedida, int lecturaAnterior, int lecturaActual) {
        this.idMedidor = idMedidor;
        this.codigo = codigo;
        this.unidadMedida = unidadMedida;
        this.lecturaAnterior = lecturaAnterior;
        this.lecturaActual = lecturaActual;
    }
    
    public int calcularConsumoMensual() {
        return lecturaActual - lecturaAnterior;
    }

    public int getIdMedidor() {
        return idMedidor;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public int getLecturaAnterior() {
        return lecturaAnterior;
    }

    public int getLecturaActual() {
        return lecturaActual;
    }

    public void setIdMedidor(int idMedidor) {
        this.idMedidor = idMedidor;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public void setLecturaAnterior(int lecturaAnterior) {
        this.lecturaAnterior = lecturaAnterior;
    }

    public void setLecturaActual(int lecturaActual) {
        this.lecturaActual = lecturaActual;
    }

    @Override
    public String toString() {
        return "Medidor{" + "idMedidor=" + idMedidor + ", codigo=" + codigo + ", unidadMedida=" + unidadMedida + ", lecturaAnterior=" + lecturaAnterior + ", lecturaActual=" + lecturaActual + '}';
    }
}
