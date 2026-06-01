/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ordenamiento;

/**
 *
 * @author Gaby
 */
public class Main {
    public static void main(String[] args) {
        int[] x = {8, 3, 5, 6, 12, 4, 7, 33, 24, 64};
        VectorX v = new VectorX(x);
        System.out.println(v.toString());
        v.ordenarSLI();
        System.out.println(v);
        VectorX v2 = new VectorX(v.generarVector(10000));
        System.out.println(v2.toString3());
        v2.ordenarSLI();
        System.out.println(v2.toString3());
    }
}
