/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

/**
 *
 * @author Gaby
 */
public class ServicioInternet extends Servicio {
    private int monto;

    public ServicioInternet() {
    }

    public ServicioInternet(int monto) {
        this.monto = monto;
    }

    public ServicioInternet(int monto, int idServicio, String codigo, String nombre) {
        super(idServicio, codigo, nombre);
        this.monto = monto;
    }
    
    @Override
    public double calcularMonto() {
        return monto;
    }
}
