/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Gaby
 */
public class Carro extends Vehiculo implements Arrancar {

    public Carro(String color, int anioModelo, int cantRuedas) {
        super(color, anioModelo, cantRuedas);
    }
    
    @Override
    public void avanzar() {
        System.out.println("El Carro Avanza");
    }
    
    @Override
    public void arrancar() {
        System.out.println("El Conductor Gira la Llave de Ignición");
    }
    
}
