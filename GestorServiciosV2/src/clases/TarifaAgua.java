/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package clases;

/**
 *
 * @author Gaby
 */
public enum TarifaAgua {
    BLOQUE_1(15, 309), //Hasta los 15 m3 valen 409
    BLOQUE_2(10, 822), //Los siguientes 10 m3 a 822
    BLOQUE_3(Integer.MAX_VALUE, 1100); //El resto de m3
    
    private final int limite;
    
    private final double precio;
    
    TarifaAgua(int limite, double precio) {
        this.limite = limite;
        this.precio = precio;
    }
    
    public int getLimite() {
        return limite;
    }
    
    public double getPrecio() {
        return precio;
    }
}
