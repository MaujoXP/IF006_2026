/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package generica;

import java.util.ArrayList;

/**
 * Lista generica permite diversos objetos
 * @version 1.0.0
 * @author Mauricio
 */
public class ListaGenerica<T extends IFiltro> {
    protected ArrayList<T> lista = new ArrayList<>();
    
    public void agregar(T objeto) {
        lista.add(objeto);
    }
    
    public T buscarPorId(int id) {
        T objetoEncontrado = null;
        for(T objeto : lista) {
            if(objeto.getId() == id) {
                objetoEncontrado = objeto;
                break;
            }
        }
        
        return objetoEncontrado;
    }
    
    public int obtenerConsecutivo() {
        return lista.size() + 1;
    }
    
    @Override
    public String toString() {
        String salida = "";
        for(T objeto : lista) {
            salida += objeto + "\n\n";
        }
        return salida;
    }
}
