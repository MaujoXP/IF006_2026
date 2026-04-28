package Pila;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Mauricio León B C5G444
 */
public class PruebaPila {

    public static void main(String[] args) {
        Pila p = new Pila(10);

        p.ingresarElemento(8);
        p.ingresarElemento(3);
        p.ingresarElemento(56);
        p.ingresarElemento(16);
        p.ingresarElemento(34);
        p.ingresarElemento(55);
        p.ingresarElemento(21);
        p.ingresarElemento(8);
        p.ingresarElemento(12);
        p.ingresarElemento(42);
        p.ingresarElemento(65);
        p.ingresarElemento(63);
        p.ingresarElemento(88);
        p.ingresarElemento(23);
        p.ingresarElemento(98);

        System.out.println(p);

        System.out.println(p.eliminarElemento());
        System.out.println(p.eliminarElemento());

        p.ingresarElemento(11);
        p.ingresarElemento(63);
        p.ingresarElemento(43);
        p.ingresarElemento(13);
        p.ingresarElemento(24);
        p.ingresarElemento(44);
        p.ingresarElemento(52);
        p.ingresarElemento(34);
        p.ingresarElemento(77);

        System.out.println(p.eliminarElemento());
        System.out.println(p.eliminarElemento());
        System.out.println(p.eliminarElemento());
        System.out.println(p.eliminarElemento());

        System.out.println(p);
    }
}
