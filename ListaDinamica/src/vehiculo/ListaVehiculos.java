/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vehiculo;

import generica.ListaGenerica;
import java.util.ArrayList;

/**
 *
 * @author Gaby
 */
public class ListaVehiculos extends ListaGenerica<Vehiculo>{
    private ArrayList<Vehiculo> lista = new ArrayList<>();
    
    public void agregar(Vehiculo vehiculo) {
        lista.add(vehiculo);
    }
    
    public Vehiculo buscarPorId(int id) {
        Vehiculo vehiculoEncontrada = null;
        for(Vehiculo vehiculo : lista) {
            if(vehiculo.getId() == id) {
                vehiculoEncontrada = vehiculo;
                break;
            }
        }
        return vehiculoEncontrada;
    }
    
    public int obtenerConsecutivoId() {
        return lista.size() + 1;
    }

    @Override
    public String toString() {
        String salida = "";
        for(Vehiculo vehiculo : lista) {
            salida += vehiculo + "\n\n";
        }
        return salida;
    }
}
