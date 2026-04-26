/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factorial;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 *
 * @author Gaby
 */
public class FactorialHilo extends Thread {

    private int inicio;
    private int fin;
    private BigInteger resultado = BigInteger.ONE;

    public FactorialHilo(int inicio, int fin) {
        this.inicio = inicio;
        this.fin = fin;
    }

    @Override
    public void run() {
        for (int i = inicio; i <= fin; i++) {
            resultado = resultado.multiply(BigInteger.valueOf(i));
        }

        System.out.println("Calculado rango [" + inicio + "-" + fin + "]");
    }

    public BigInteger getResultado() {
        return resultado;
    }

    public static void main(String[] args) throws InterruptedException {
        int numero = 50;
        int cantidad = 5;

        Util util = new Util();
        int rangos[][] = util.obtenerRangos(numero, cantidad);

        ArrayList<FactorialHilo> listaHilos = new ArrayList<>();

        for (int i = 0; i < cantidad; i++) {
            FactorialHilo hilo = new FactorialHilo(rangos[i][0], rangos[i][1]);
            listaHilos.add(hilo);
        }

        System.out.println("\nEstado de los hilos");
        int contador = 1;
        for (FactorialHilo hilo : listaHilos) {
            System.out.println("Estado " + (contador++) + ": " + hilo.getState());
        }

        System.out.println("\nIniciando hilos");
        contador = 1;
        for (FactorialHilo hilo : listaHilos) {
            hilo.start();
        }

        System.out.println("Estado de los hilos");
        util.verEstados(listaHilos);

        System.out.println("\nEsperando hilos");
        contador = 1;
        for (FactorialHilo hilo : listaHilos) {
            hilo.join();
        }

        System.out.println("Multiplicacion");
        BigInteger resultadoFinal = BigInteger.ONE;
        for (FactorialHilo hilo : listaHilos) {
            resultadoFinal = resultadoFinal.multiply(hilo.getResultado());
        }

        System.out.println("Factorial de " + numero + " es: " + resultadoFinal);

//        FactorialHilo h1 = new FactorialHilo(1, 10);
//        FactorialHilo h2 = new FactorialHilo(11, 20);
//        FactorialHilo h3 = new FactorialHilo(21, 30);
//        
//        h1.start();
//        h2.start();
//        h3.start();
//        
//        h1.join();
//        h2.join();
//        h3.join();
//        
//        BigInteger resultadoFinal = 
//                h1.getResultado().multiply(h2.getResultado()).multiply(h3.getResultado());
//        
        //System.out.println("Factorial de "+numero+" es: "+resultadoFinal);
//        Util util = new Util();
//        
//        int rangos[][] = util.obtenerRangos(numero, 5);
//        
//        for(int i = 0; i<rangos.length; i++){
//            System.out.println("Rango["+(i+1)+"]: ("+rangos[i][0]+","+rangos[i][1]+")");
//        }
    }
}
