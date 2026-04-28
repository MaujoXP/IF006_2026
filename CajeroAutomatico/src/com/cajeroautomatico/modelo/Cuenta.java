/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cajeroautomatico.modelo;

/**
 *
 * @author Mauricio Leon Bermudez C5G444
 */
public abstract class Cuenta implements ICuenta {
    private int idCuenta;
    private String tipoCuenta;
    private String cedulaCliente;
    private String nombreCliente;
    private double saldo;

    public Cuenta(int idCuenta, String tipoCuenta, String cedulaCliente, String nombreCliente, double saldo) {
        this.idCuenta = idCuenta;
        this.tipoCuenta = tipoCuenta;
        this.cedulaCliente = cedulaCliente;
        this.nombreCliente = nombreCliente;
        this.saldo = saldo;
    }

    public int getIdCuenta() {
        return idCuenta;
    }
    
    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public String getCedulaCliente() {
        return cedulaCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }
    
    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public void setCedulaCliente(String cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "Cuenta\n" 
                + "\nTipoCuenta: " + tipoCuenta 
                + "\nCedulaCliente: " + cedulaCliente 
                + "\nNombreCliente: " + nombreCliente 
                + "\nSaldo: " + saldo + "crc\n";
    }
    
    @Override
    public abstract String retiro(double monto);
    
    @Override
    public abstract String depositar(double monto);
    
    @Override
    public abstract double consultarSaldo();
    
    
}
