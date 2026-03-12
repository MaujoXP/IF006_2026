/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Gaby
 */
public class Bicicleta extends Vehiculo implements Arrancar{

    public Bicicleta(String color, int anioModelo, int cantRuedas) {
        super(color, anioModelo, cantRuedas);
    }
    
    @Overridre
    public void avanzar() {
        System.out.println("La Bicicleta Avanza");
    }
    
    @Override
    public void frenar() {
        System.out.println("La Bicicleta Frena");
    }
    
    @Override
    public void arrancar() {
        System.out.println("La Bicicleta comienza a Pedalear");
    }
}
