/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Gaby
 */
public class CuentaAhorro extends CuentaBancaria {

    public CuentaAhorro(String cuenta, double saldo) {
        super(cuenta, saldo);
    }
    
    @Override
    public boolean retirar(int monto) {
        if(monto <= saldo) {
            saldo -= monto;
            return true;
        }
        return false;
    }

    @Override
    public double consultarSaldo() {
        return saldo;
    }
    
}
