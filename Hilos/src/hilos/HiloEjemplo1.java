/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hilos;

/**
 *
 * @author Gaby
 */
public class HiloEjemplo1 extends Thread {

    @Override
    public void run() {
        for(int i = 0; i < 5; i++) {
            System.out.println("Ejecutando hilo: " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.getLogger(HiloEjemplo1.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
    }
    
    public static void main(String[] args) {
        HiloEjemplo1 hilo = new HiloEjemplo1();
        System.out.println("Hilo: " + hilo.getState());
        hilo.start();
        System.out.println("Hilo: " + hilo.getState());
        System.out.println("Fin del main");
        System.out.println("Hilo: " + hilo.getState());
    }
    
}
