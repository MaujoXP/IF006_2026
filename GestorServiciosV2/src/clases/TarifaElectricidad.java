/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package clases;

/**
 *
 * @author Gaby
 */
public enum TarifaElectricidad {
    BLOQUE_1(140, 56), //Hasta los 140 kw valen 56
    BLOQUE_2(55, 63), //Los siguientes 55 kw a 63
    BLOQUE_3(55, 73), //Los siguientes 55 kw a 73
    BLOQUE_4(120, 85), //Los siguientes 120 kw a 85
    BLOQUE_5(Integer.MAX_VALUE, 99); //El resto de kw a 99
    
    private final int limite;
    
    private final double precio;
    
    TarifaElectricidad(int limite, double precio) {
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
