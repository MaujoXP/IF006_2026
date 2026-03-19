/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import clases.Medidor;
import clases.Servicio;
import clases.ServicioAgua;
import clases.ServicioElectricidad;
import clases.Vivienda;

/**
 *
 * @author Mauricio León Bermúdez C5G444
 */
public class Main {
    public static void main(String args[]) {
        Medidor medidorAgua = new Medidor(1, "A2540", "m3", 37, 78);
        Medidor medidorLuz = new Medidor(1, "E36525", "kw", 215, 400);
        
        Servicio agua = new ServicioAgua(medidorAgua, 1, "SA365", "AYA");
        Servicio electricidad = new ServicioElectricidad(medidorLuz, 2, "SE6985", "ICE");
        
        Vivienda v = new Vivienda(1, "Casimiro Buenavista", "2503", "Limón", "Santa Eduviges");
        v.agregarServicio(electricidad);
        v.agregarServicio(agua);
        v.mostrarResumen();
        
    }
}
