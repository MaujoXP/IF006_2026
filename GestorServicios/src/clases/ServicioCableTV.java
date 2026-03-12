/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

/**
 * Define el servicio para televisión por cable
 * @author Gaby
 */
public class ServicioCableTV extends Servicio {
    private double monto;
    
    public ServicioCableTV() {
        
    }

    public ServicioCableTV(double monto) {
        this.monto = monto;
    }

    public ServicioCableTV(int idServicio, String codigo, String nombre) {
        super(idServicio, codigo, nombre);
    }
    
    @Override
    public double calcularMonto() {
        return monto;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
}
