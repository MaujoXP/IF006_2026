/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Gaby
 */
public class CuentaCorriente extends CuentaBancaria {

    private static final double COMISION = 0.03;
    private static final double SOBREGIRO = 50000;
    
    public CuentaCorriente(String cuenta, double saldo) {
        super(cuenta, saldo);
    }

    @Override
    public boolean retirar(int monto) {
        double total = monto + (monto * COMISION);
        double saldoConSobregiro = saldo + SOBREGIRO;
        if(total <= saldoConSobregiro) {
            saldo -= total;
            return true;
        }
        return false;
    }

    @Override
    public double consultarSaldo() {
        return saldo;
    }
    
}
