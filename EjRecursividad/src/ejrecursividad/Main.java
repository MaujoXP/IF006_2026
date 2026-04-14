/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejrecursividad;

/**
 *
 * @author Gaby
 */
public class Main {

    public static void main(String[] args) {
        Recursividad p = new Recursividad();
        System.out.println("Valor de la suma es" + p.sumaI(10));
        System.out.println("Valor de la factorial es" + p.factorialR(5));
        System.out.println("El texto invertido es:" + p.invertirTexto("Algoritmos", 9));
        p.fibonacci(1, 1, 0);
    }

}
