package Cola;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author Mauricio León B C5G444
 */
public class PruebaCola {

    public static void main(String[] args) {
        Cola c = new Cola(11);

        c.agregarElemento(18);
        c.agregarElemento(25);
        c.agregarElemento(33);
        c.agregarElemento(51);

        System.out.println(c);
    }

}
