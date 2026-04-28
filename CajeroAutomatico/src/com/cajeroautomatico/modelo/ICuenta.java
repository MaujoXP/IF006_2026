package com.cajeroautomatico.modelo;

/**
 *
 * @author Mauricio León Bermúdez C5G444
 */
public interface ICuenta {
    public double consultarSaldo();
    public String depositar(double monto);
    public String retiro(double monto);
    public double calcularInteresMensual();
}
