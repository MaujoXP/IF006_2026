/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factorial;

import java.util.ArrayList;

/**
 *
 * @author Gaby
 */
public class Util {
    public int[][] obtenerRangos(int numero, int cantidad) {
        int rangos[][] = new int[cantidad][2];
        
        int rango = numero / cantidad;
        
        rangos[0][0] = 1;
        rangos[0][1] = rango;
        
        for(int i = 1; i < cantidad; i++) {
            rangos[i][0] = rangos[i - 1][0] + rango;
            rangos[i][1] = rangos[i - 1][1] + rango;
        }
        
        return rangos;
    }
    
    public String verEstados(ArrayList<FactorialHilo> lista) {
        String salida = "";
        int contador = 1;
        for (FactorialHilo hilo : lista) {
            System.out.println("\nEstado " + (contador++) + ": " + hilo.getState());
        }
        
        return salida;
    }
}
