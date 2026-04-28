/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cajeroautomatico.modelo;

/**
 *
 * @author Mauricio Leon Bermudez C5G444
 */
public class CuentaCorriente extends Cuenta {

    public CuentaCorriente(int idCuenta, String nombreCliente, String cedulaCliente, double saldo) {
        super(idCuenta, "CORRIENTE", cedulaCliente, nombreCliente, saldo);
    }
    
    @Override
    public String retiro(double monto) {
        if(monto > getSaldo()) {
            return "Error, retiro superior al saldo actual";
        } else {
            setSaldo(getSaldo() - monto);
            return "Dinero retirado con éxito";
        }
    }
    
    @Override
    public String depositar(double monto) {
        if(monto < 0) {
            return "Error, deposito inferior a 0";
        } else {
            setSaldo(getSaldo() + monto);
            return "Dinero depositado con éxito";
        }
    }
    
    @Override
    public double consultarSaldo() {
        return getSaldo();
    }
    
    @Override
    public double calcularInteresMensual() {
        return 0;
    }
}
