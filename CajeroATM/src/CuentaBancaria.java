/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Gaby
 */
public abstract class CuentaBancaria implements IOperacionCuenta {
    protected String cuenta;
    protected double saldo;
    
    public CuentaBancaria(String cuenta, double saldo) {
        this.cuenta = cuenta;
        this.saldo = saldo;
    }

    public String getCuenta() {
        return cuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
    @Override
    public void depositar(int monto) {
        saldo += monto;
    }
}
