/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Gaby
 */
public class PruebaMoverCajas {
    public static void main(String args[]) {
        JuegoMoverCajas juego1 = new JuegoMoverCajas(8, 8);
        System.out.println(juego1);
        juego1.setTrabajador(1, 2);
        System.out.println(juego1);
    }
}
