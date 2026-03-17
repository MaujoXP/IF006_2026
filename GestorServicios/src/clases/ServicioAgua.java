/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

/**
 *
 * @author Gaby
 */
public class ServicioAgua extends Servicio {
    private double monto;
    private Medidor medidor;

    public ServicioAgua() {
    }

    public ServicioAgua(Medidor medidor, int idServicio, String codigo, String nombre) {
        super(idServicio, codigo, nombre);
        this.monto = monto;
        this.medidor = medidor;
    }
    
    @Override
    public double calcularMonto() {
        double precio;
        int consumo = medidor.calcularConsumoMensual();
        
        for(int i = 0; i <= consumo; i++) {
            if(i <= 15) {
                precio = 409;
            } else if(i <= 25) {
                precio = 822;
            } else {
                precio = 1100;
            }
            
            monto += precio;
        }
        return monto;
    }

    public Medidor getMedidor() {
        return medidor;
    }
}
