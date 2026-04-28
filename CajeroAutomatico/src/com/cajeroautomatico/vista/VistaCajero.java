/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cajeroautomatico.vista;
import javax.swing.JOptionPane;

/**
 *
 * @author Mauricio Leon Bermudez C5G444
 */
public class VistaCajero {

    public VistaCajero() {
    }

    public int menu() {
        String msg = ("Menu Cajero Automatico"
                + "\n1. Creacion de Cuenta Ahorro"
                + "\n2. Creacion de Cuenta Corriente"
                + "\n3. Deposito en Cuenta"
                + "\n4. Retiro en Cuenta"
                + "\n5. Calcular Intereses Cuenta Ahorro"
                + "\n6. Consultar Saldo Cuenta"
                + "\n7. Salir");
        int opcion = Integer.parseInt(JOptionPane.showInputDialog(msg));

        return opcion;
    }

    public void mostrarInformacion(String info) {
        JOptionPane.showMessageDialog(null, info);
    }

    public int solicitarInt(String dato) {
        int entero = Integer.parseInt(JOptionPane.showInputDialog(dato));
        return entero;
    }

    public String solicitarString(String dato) {
        String msg = JOptionPane.showInputDialog("Ingrese " + dato + ": ");
        return msg;
    }

    public double solicitarDouble() {
        double monto = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el monto: "));
        return monto;
    }
}
