/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

/**
 *
 * @author Gaby
 */
public class ServicioElectricidad extends Servicio {
    private double monto;
    private Medidor medidor;

    public ServicioElectricidad() {
    }

    
    public ServicioElectricidad(Medidor medidor, int idServicio, String codigo, String nombre) {
        super(idServicio, codigo, nombre);
        this.monto = monto;
        this.medidor = medidor;
    }
    
    @Override
    public double calcularMonto() {
        double precio;
        int consumo = medidor.calcularConsumoMensual();
        
        for(int i = 0; i <= consumo; i++) {
            if(i <= 140) {
                precio = 56;
            } else if(i <= 195) {
                precio = 63;
            } else if(i <= 250) {
                precio = 73;
            } else if(i <= 370) {
                precio = 85;
            } else {
                precio = 99;
            }
            
            monto += precio;
        }
        return monto;
    }
}
