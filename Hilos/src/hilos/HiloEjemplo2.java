/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hilos;

/**
 *
 * @author Gaby
 */
public class HiloEjemplo2 extends Thread {
    private String nombre;
    
    public HiloEjemplo2(String nombre) {
        this.nombre = nombre;
    }
    
    @Override 
    public void run() {
        for(int i = 0; i < 5; i++) {
            System.out.println(nombre + " -> " + i);
            try {
                Thread.sleep(2000);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) {
        HiloEjemplo2 hilo1 = new HiloEjemplo2("Hilo 1");
        HiloEjemplo2 hilo2 = new HiloEjemplo2("Hilo 2");
        HiloEjemplo2 hilo3 = new HiloEjemplo2("Hilo 3");
        hilo1.start();
        hilo2.start();
        hilo3.start();
        System.out.println("Main ejecutandose");
        
    }
}
