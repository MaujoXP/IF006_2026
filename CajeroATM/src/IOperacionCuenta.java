/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Gaby
 */
public interface IOperacionCuenta {
    public void depositar(int monto);
    public boolean retirar(int monto);
    public double consultarSaldo();
}
