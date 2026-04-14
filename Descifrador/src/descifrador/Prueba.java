/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package descifrador;

/**
 *
 * @author Gaby
 */
public class Prueba {

    public static void main(String[] args) {
        Descifrador descifrador = new Descifrador();
        System.out.println(descifrador);
        descifrador.rellenarTabla('K');
        System.out.println(descifrador);
        descifrador.rellenanarFila('A', 0);
        System.out.println(descifrador);
        descifrador.rellenanarFila('F', 0, 5);
        System.out.println(descifrador);
        descifrador.rellenanarFila('G', 0, 15, 19);
        System.out.println(descifrador);
        descifrador.rellenanarFila('H', 1, 2, 10, 2);
        System.out.println(descifrador);
        descifrador.rellenanarFilaPalabra(5, "Dios te ama");
        System.out.println(descifrador);
        descifrador.rellenanarFilaPalabra(6, "Dios te odia", 2);
        System.out.println(descifrador);
        descifrador.rellenarDP('T', 0, 5);
        descifrador.rellenarDS('P');
        System.out.println(descifrador);
        System.out.println(descifrador.extraerFila(2, 0, 20, 1));
        System.out.println(descifrador.extraerFila(4, 8, 16, 2));
    }
}
