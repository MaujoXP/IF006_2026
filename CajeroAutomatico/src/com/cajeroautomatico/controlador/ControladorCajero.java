/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cajeroautomatico.controlador;

import com.cajeroautomatico.modelo.Cuenta;
import com.cajeroautomatico.modelo.CuentaAhorro;
import com.cajeroautomatico.modelo.CuentaCorriente;
import com.cajeroautomatico.vista.VistaCajero;
import java.util.ArrayList;

/**
 *
 * @author Mauricio Leon Bermudez C5G444
 */
public class ControladorCajero {

    private int numCuenta = 0;
    private ArrayList<Cuenta> cuentas = new ArrayList<>();

    public void iniciarCajero() {
        VistaCajero vista = new VistaCajero();
        String cedula;
        String nombre;
        double montoInicial;
        double monto;
        int idCuenta;
        Cuenta cuentaEncontrada;
        int opcion = vista.menu();
        switch (opcion) {
            case 1:
                cedula = vista.solicitarString("cedula");
                nombre = vista.solicitarString("nombre completo");
                montoInicial = vista.solicitarDouble();
                CuentaAhorro cuentaAhorro = new CuentaAhorro(numCuenta++, cedula, nombre, montoInicial, 3);
                vista.mostrarInformacion("Cuenta creada");
                cuentas.add(cuentaAhorro);
                iniciarCajero();
                break;
            case 2:
                cedula = vista.solicitarString("cedula");
                nombre = vista.solicitarString("nombre completo");
                montoInicial = vista.solicitarDouble();
                CuentaCorriente cuentaCorriente = new CuentaCorriente(numCuenta++, cedula, nombre, montoInicial);
                vista.mostrarInformacion("Cuenta creada");
                cuentas.add(cuentaCorriente);
                iniciarCajero();
                break;
            case 3:
                idCuenta = vista.solicitarInt("id de cuenta");
                cuentaEncontrada = null;
                for (Cuenta cuenta : cuentas) {
                    if (cuenta.getIdCuenta() == idCuenta) {
                        cuentaEncontrada = cuenta;
                    }
                }

                if (cuentaEncontrada != null) {
                    monto = vista.solicitarDouble();
                    String resultado = cuentaEncontrada.depositar(monto);
                    vista.mostrarInformacion(resultado);
                } else {
                    vista.mostrarInformacion("Cuenta no encontrada");
                }
                iniciarCajero();
                break;
            case 4:
                idCuenta = vista.solicitarInt("id de cuenta");
                cuentaEncontrada = null;
                for (Cuenta cuenta : cuentas) {
                    if (cuenta.getIdCuenta() == idCuenta) {
                        cuentaEncontrada = cuenta;
                    }
                }

                if (cuentaEncontrada != null) {
                    monto = vista.solicitarDouble();
                    String resultado = cuentaEncontrada.retiro(monto);
                    vista.mostrarInformacion(resultado);
                } else {
                    vista.mostrarInformacion("Cuenta no encontrada");
                }
                iniciarCajero();
                break;
            case 5:
                idCuenta = vista.solicitarInt("id de cuenta");
                cuentaEncontrada = null;
                for (Cuenta cuenta : cuentas) {
                    if (cuenta.getIdCuenta() == idCuenta && cuenta.getTipoCuenta().equals("AHORRO")) {
                        cuentaEncontrada = cuenta;
                    }
                }

                if (cuentaEncontrada != null) {
                    double interesMensual = cuentaEncontrada.calcularInteresMensual();
                    String resultado = "Intereses actuales acumulados: " + interesMensual;
                    vista.mostrarInformacion(resultado);
                } else {
                    vista.mostrarInformacion("Cuenta no encontrada");
                }
                iniciarCajero();
                break;
            case 6:
                idCuenta = vista.solicitarInt("id de cuenta");
                cuentaEncontrada = null;
                for (Cuenta cuenta : cuentas) {
                    if (cuenta.getIdCuenta() == idCuenta) {
                        cuentaEncontrada = cuenta;
                    }
                }

                if (cuentaEncontrada != null) {
                    double saldo = cuentaEncontrada.consultarSaldo();
                    String resultado = "Saldo: " + saldo;
                    vista.mostrarInformacion(resultado);
                } else {
                    vista.mostrarInformacion("Cuenta no encontrada");
                }
                iniciarCajero();
                break;
            case 7:
                break;
            default:
                iniciarCajero();
                break;
        }
    }
}
