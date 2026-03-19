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
        double monto = 0;
        int consumo = medidor.calcularConsumoMensual();
        for(TarifaAgua ta: TarifaAgua.values()) {
            if(consumo  <= 0) {
                break;
            }
            
            int consumoEnBloque = Math.min(consumo, ta.getLimite());
            monto += consumoEnBloque * ta.getPrecio();
            consumo -= consumoEnBloque;
        }
        
        return monto;
    }

    public Medidor getMedidor() {
        return medidor;
    }
}
