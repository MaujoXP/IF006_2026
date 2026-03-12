/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Gaby
 */
public abstract class Vehiculo {
    private String color;
    private int anioModelo;
    private int cantRuedas;

    public Vehiculo(String color, int anioModelo, int cantRuedas) {
        this.color = color;
        this.anioModelo = anioModelo;
        this.cantRuedas = cantRuedas;
    }
    
    public abstract void avanzar();
    
    public void frenar() {
        System.out.println("Vehiculo Frena");
    }
}
