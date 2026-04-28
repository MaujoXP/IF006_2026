/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cajeroautomatico.modelo;

/**
 *
 * @author Mauricio Leon Bermudez C5G444
 */
public class CuentaAhorro extends Cuenta {
    public double interesMensual;
    public static double COMISION_RETIRO = 0.05;

    public CuentaAhorro(int idCuenta, String cedulaCliente, String nombreCliente, double saldo, double interesMensual) {
        super(idCuenta, "AHORRO", cedulaCliente, nombreCliente, saldo);
        this.interesMensual = interesMensual;
    }
    
    public double calcularInteresMensual() {
        return (getInteresMensual() / 100) * getSaldo();
    }

    public double getInteresMensual() {
        return interesMensual;
    }

    public void setInteresAnual(int interesAnual) {
        this.interesMensual = interesAnual;
    }
    
    public void realizarCorteMensual() {
        if(getSaldo() > 0) {
            setSaldo(getSaldo() + calcularInteresMensual());
        }
    }
    
    public double calcularComisionRetiro(double monto) {
        return monto * COMISION_RETIRO;
    }
    
    @Override
    public String retiro(double monto) {
        if(monto > getSaldo()) {
            return "Error, retiro superior al saldo actual";
        } else {
            setSaldo(getSaldo() - monto - calcularComisionRetiro(monto));
            return "Dinero retirado con éxito, comision por retiro: " + calcularComisionRetiro(monto);
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
}
