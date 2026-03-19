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
        double monto = 0;
        int consumo = medidor.calcularConsumoMensual();
        for(TarifaElectricidad ta: TarifaElectricidad.values()) {
            if(consumo  <= 0) {
                break;
            }
            
            int consumoEnBloque = Math.min(consumo, ta.getLimite());
            monto += consumoEnBloque * ta.getPrecio();
            consumo -= consumoEnBloque;
        }
        
        return monto;
    }
}
