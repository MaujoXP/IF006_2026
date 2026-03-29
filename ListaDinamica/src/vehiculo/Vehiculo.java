/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vehiculo;

import generica.IFiltro;

/**
 *
 * @author Gaby
 */
public class Vehiculo implements IFiltro{
    private int id;
    private String placa;
    private String tipoVehiculo;
    private int anno;

    public Vehiculo() {
    }

    public Vehiculo(int id, String placa, String tipoVehiculo, int anno) {
        this.id = id;
        this.placa = placa;
        this.tipoVehiculo = tipoVehiculo;
        this.anno = anno;
    }

    @Override
    public int getId() {
        return id;
    }

    public String getPlaca() {
        return placa;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public int getAnno() {
        return anno;
    }

    @Override
    public String toString() {
        String salida = "";
        salida += "Id:" + id;
        salida += "Placa: " + placa;
        salida += "\nTipo vehiculo: " + tipoVehiculo;
        salida += "\nAño: " + anno;
        
        return salida;
    }
    
    
}
